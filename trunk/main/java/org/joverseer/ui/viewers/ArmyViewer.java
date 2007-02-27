package org.joverseer.ui.viewers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import org.joverseer.domain.Army;
import org.joverseer.domain.ArmyElement;
import org.joverseer.domain.Character;
import org.joverseer.game.Game;
import org.joverseer.game.Turn;
import org.joverseer.game.TurnElementsEnum;
import org.joverseer.metadata.GameMetadata;
import org.joverseer.support.Container;
import org.joverseer.support.GameHolder;
import org.joverseer.tools.CombatUtils;
import org.joverseer.ui.LifecycleEventsEnum;
import org.joverseer.ui.domain.mapItems.AbstractMapItem;
import org.joverseer.ui.domain.mapItems.ArmyRangeMapItem;
import org.joverseer.ui.map.MapPanel;
import org.joverseer.ui.support.ColorPicker;
import org.joverseer.ui.support.JOverseerEvent;
import org.joverseer.ui.support.PopupMenuActionListener;
import org.springframework.binding.form.FormModel;
import org.springframework.richclient.application.Application;
import org.springframework.richclient.command.ActionCommand;
import org.springframework.richclient.command.CommandGroup;
import org.springframework.richclient.dialog.MessageDialog;
import org.springframework.richclient.form.AbstractForm;
import org.springframework.richclient.image.ImageSource;
import org.springframework.richclient.layout.GridBagLayoutBuilder;


public class ArmyViewer extends ObjectViewer {

    public static final String FORM_PAGE = "ArmyViewer";

    boolean showColor = true;
    
    JTextField commanderName;
    JTextField nation;
    JTextField armySize;
    JTextField armyType;
    JTextField extraInfo;
    JTextField food;
    JTextField travellingWith;

    ActionCommand showArmyMovementRangeAction = new ShowArmyMovementRangeAction();
    ActionCommand toggleFedAction = new ToggleFedAction();
    ActionCommand deleteArmyCommand = new DeleteArmyCommand();

    public ArmyViewer(FormModel formModel) {
        super(formModel, FORM_PAGE);
    }
    
    public boolean appliesTo(Object obj) {
        return Army.class.isInstance(obj);
    }

    protected JComponent createFormControl() {
        GridBagLayoutBuilder glb = new GridBagLayoutBuilder();
        glb.setDefaultInsets(new Insets(0, 0, 0, 5));

        glb.append(commanderName = new JTextField());
        commanderName.setPreferredSize(new Dimension(160, 12));
        glb.append(nation = new JTextField());
        nation.setPreferredSize(new Dimension(30, 12));

        // button to show range of army on map
        ImageSource imgSource = (ImageSource) Application.instance().getApplicationContext().getBean("imageSource");
        JButton btnMenu = new JButton();
        Icon ico = new ImageIcon(imgSource.getImage("menu.icon"));
        btnMenu.setPreferredSize(new Dimension(16, 16));
        btnMenu.setIcon(ico);
        glb.append(btnMenu);
        btnMenu.addActionListener(new PopupMenuActionListener() {

            public JPopupMenu getPopupMenu() {
                return createArmyPopupContextMenu();
            }
        });

        glb.nextLine();
        glb.append(armySize = new JTextField());
        armySize.setPreferredSize(new Dimension(100, 12));
        glb.append(armyType = new JTextField());
        armyType.setPreferredSize(new Dimension(50, 12));
        glb.nextLine();
        glb.append(extraInfo = new JTextField());
        extraInfo.setPreferredSize(new Dimension(100, 12));
        glb.nextLine();
        glb.append(food = new JTextField());
        food.setPreferredSize(new Dimension(100, 12));
        glb.nextLine();
        glb.append(travellingWith = new JTextField(), 2, 1);
        travellingWith.setPreferredSize(new Dimension(150, 12));

        
        commanderName.setBorder(null);
        commanderName
                .setFont(new Font(commanderName.getFont().getName(), Font.BOLD, commanderName.getFont().getSize()));
        nation.setBorder(null);
        armySize.setBorder(null);
        armyType.setBorder(null);
        extraInfo.setBorder(null);
        food.setBorder(null);
        travellingWith.setBorder(null);

        JPanel panel = glb.getPanel();
        panel.setBackground(Color.white);
        return panel;
    }

    public void setFormObject(Object object) {
        super.setFormObject(object);

        Army army = (Army) object;
        commanderName.setText(army.getCommanderTitle() + " " + army.getCommanderName());

        if (getShowColor()) {
            Color c = ColorPicker.getInstance().getColor(army.getNationAllegiance().toString());
            commanderName.setForeground(c);
        }
        
        Game game = ((GameHolder) Application.instance().getApplicationContext().getBean("gameHolder")).getGame();
        if (game == null)
            return;
        GameMetadata gm = game.getMetadata();
        nation.setText(gm.getNationByNum(army.getNationNo()).getShortName());

        
        armySize.setText("Size: " + army.getSize().toString());
        armyType.setText(army.isNavy() ? "Navy" : "Army");
        if (army.getElements().size() > 0) {
            extraInfo.setText("");
            extraInfo.setVisible(true);
            for (ArmyElement element : army.getElements()) {
                extraInfo.setText(extraInfo.getText() + (extraInfo.getText().equals("") ? "" : " ")
                        + element.getDescription());
            }
            extraInfo.setText(extraInfo.getText() + " (" + CombatUtils.getNakedHeavyInfantryEquivalent(army) + "enHI)");
        } else if (army.getTroopCount() > 0) {
            extraInfo.setVisible(true);
            extraInfo.setText("~ " + army.getTroopCount() + " men");
        } else {
            extraInfo.setVisible(false);
        }
        String foodStr = "";
        if (army.getFood() != null) {
            foodStr = army.getFood().toString() + " ";
        }
        Boolean fed = army.computeFed();

        foodStr += (fed != null && fed == true ? "Fed" : "Unfed");
        food.setText(foodStr);
        // armyMorale.setText("M: 0");
        
        if (army.getCharacters().size() > 0) {
            travellingWith.setVisible(true);
            String txt = "";
            for (String cn : (ArrayList<String>)army.getCharacters()) {
                txt += (txt.equals("") ? "" : ",") + cn;
            }
            travellingWith.setText("With army: " + txt);
        } else {
            travellingWith.setVisible(false);
        }
    }

    private JPopupMenu createArmyPopupContextMenu() {
        CommandGroup group = Application.instance().getActiveWindow().getCommandManager().createCommandGroup(
                "armyCommandGroup", new Object[] {showArmyMovementRangeAction, toggleFedAction, "separator", deleteArmyCommand});
        return group.createPopupMenu();
    }

    private class ShowArmyMovementRangeAction extends ActionCommand {

        public ShowArmyMovementRangeAction() {
            super("showArmyMovementRangeAction");
        }

        protected void doExecuteCommand() {
            ArmyRangeMapItem armi = new ArmyRangeMapItem((org.joverseer.domain.Army) getFormObject());
            AbstractMapItem.add(armi);

            Application.instance().getApplicationContext().publishEvent(
                    new JOverseerEvent(LifecycleEventsEnum.RefreshMapItems.toString(), MapPanel.instance()
                            .getSelectedHex(), this));
        }
    }

    private class ToggleFedAction extends ActionCommand {

        public ToggleFedAction() {
            super("toggleFedAction");
        }

        protected void doExecuteCommand() {
            Army a = (org.joverseer.domain.Army) getFormObject();
            Boolean fed = a.computeFed();
            a.setFed(fed == null || fed != true ? true : false);
            Application.instance().getApplicationContext().publishEvent(
                    new JOverseerEvent(LifecycleEventsEnum.SelectedHexChangedEvent.toString(), MapPanel.instance()
                            .getSelectedHex(), this));
        }
    }
    
    private class DeleteArmyCommand extends ActionCommand {
        protected void doExecuteCommand() {
            Army a = (Army)getFormObject();
            Game g = ((GameHolder)Application.instance().getApplicationContext().getBean("gameHolder")).getGame();
            Turn t = g.getTurn();
            Container armies = t.getContainer(TurnElementsEnum.Army);
            armies.removeItem(a);
            Application.instance().getApplicationContext().publishEvent(
                    new JOverseerEvent(LifecycleEventsEnum.SelectedTurnChangedEvent.toString(), MapPanel.instance()
                            .getSelectedHex(), this));
        }
    }

    
    public boolean getShowColor() {
        return showColor;
    }

    
    public void setShowColor(boolean showColor) {
        this.showColor = showColor;
    }
    
    
}
