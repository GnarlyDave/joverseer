package org.joverseer.ui.viewers;

import org.springframework.richclient.form.AbstractForm;
import org.springframework.richclient.form.FormModelHelper;
import org.springframework.richclient.form.binding.BindingFactory;
import org.springframework.richclient.layout.GridBagLayoutBuilder;
import org.springframework.richclient.application.Application;
import org.springframework.richclient.table.BeanTableModel;
import org.springframework.richclient.image.ImageSource;
import org.springframework.richclient.command.ActionCommand;
import org.springframework.richclient.command.CommandGroup;
import org.springframework.binding.form.FormModel;
import org.joverseer.domain.Character;
import org.joverseer.domain.SpellProficiency;
import org.joverseer.domain.Order;
import org.joverseer.metadata.GameMetadata;
import org.joverseer.metadata.domain.Artifact;
import org.joverseer.ui.listviews.ArtifactTableModel;
import org.joverseer.ui.listviews.ItemTableModel;
import org.joverseer.ui.support.TableUtils;
import org.joverseer.ui.support.JOverseerEvent;
import org.joverseer.ui.support.PopupMenuActionListener;
import org.joverseer.ui.domain.mapItems.AbstractMapItem;
import org.joverseer.ui.domain.mapItems.CharacterRangeMapItem;
import org.joverseer.ui.LifecycleEventsEnum;
import org.joverseer.ui.map.MapPanel;
import org.joverseer.game.Game;
import org.joverseer.support.GameHolder;
import org.joverseer.support.Container;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: mskounak
 * Date: 19 ��� 2006
 * Time: 11:45:02 ��
 * To change this template use File | Settings | File Templates.
 */
public class CharacterViewer extends AbstractForm {
    public static final String FORM_PAGE = "CharacterViewer";

    JTextField characterName;
    JTextField statsTextBox;
    JTextField nationTextBox;

    JTable artifactsTable;
    JTable spellsTable;

    JButton btnMenu;

    JComponent order1comp;
    JComponent order2comp;
    OrderViewer order1;
    OrderViewer order2;

    boolean showArtifacts = false;
    boolean showSpells = false;
    boolean showOrders = false;

    ActionCommand showArtifactsCommand = new ShowArtifactsCommand();
    ActionCommand showSpellsCommand = new ShowSpellsCommand();
    ActionCommand showOrdersCommand = new ShowOrdersCommand();
    ActionCommand showCharacterRangeOnMapCommand = new ShowCharacterRangeOnMapCommand();


    public CharacterViewer(FormModel formModel) {
        super(formModel, FORM_PAGE);
    }

    public void setFormObject(Object object) {
        boolean showStartingInfo = false;
        Character startingChar = null;
        if (object != getFormObject()) {
            showArtifacts = false;
            showOrders = false;
            showSpells = false;
        }
        super.setFormObject(object);
        if (object == null) return;
        if (statsTextBox != null) {
            characterName.setCaretPosition(0);

            Character c = (Character)object;
            String txt = getStatLine(c);

            if (txt.equals("")) {
                // character is enemy
                // retrieve starting info
                Game game = ((GameHolder)Application.instance().getApplicationContext().getBean("gameHolder")).getGame();
                GameMetadata gm = game.getMetadata();
                Container startChars = gm.getCharacters();
                if (startChars != null) {
                    startingChar = (Character)startChars.findFirstByProperty("id", c.getId());
                    showStartingInfo = true;
                    if (startingChar != null) {
                        txt = getStatLine(startingChar) + "(start info)";
                    }
                }
            }
            statsTextBox.setText(txt);
            statsTextBox.setCaretPosition(0);

            Game game = ((GameHolder)Application.instance().getApplicationContext().getBean("gameHolder")).getGame();
            if (game == null) return;
            GameMetadata gm = game.getMetadata();
            nationTextBox.setText(gm.getNationByNum(c.getNationNo()).getShortName());

            ArrayList artis = new ArrayList();
            if (showArtifacts) {
                ArrayList<Integer> artifacts = (!showStartingInfo ? c.getArtifacts() : startingChar != null ? startingChar.getArtifacts() : null);
                if (artifacts != null) {
                    for (Integer no : artifacts) {
                        Artifact arti = (Artifact)gm.getArtifacts().findFirstByProperty("no", no);
                        if (arti == null) {
                            arti = new Artifact();
                            arti.setNo(no);
                            arti.setName("---");
                        }
                        artis.add(arti);
                    }
                }
            }
            ((BeanTableModel)artifactsTable.getModel()).setRows(artis);
            artifactsTable.setPreferredSize(new Dimension(artifactsTable.getWidth(), 16 * artis.size()));

            ArrayList spells = new ArrayList();
            if (showSpells) {
                spells.addAll(c.getSpells());
            }
            ((BeanTableModel)spellsTable.getModel()).setRows(spells);
            spellsTable.setPreferredSize(new Dimension(spellsTable.getWidth(), 16 * spells.size()));

            order1.setFormObject(c.getOrders()[0]);
            order2.setFormObject(c.getOrders()[1]);
            if (showOrders) {
                order1comp.setVisible(true);
                order2comp.setVisible(true);
            } else {
                order1comp.setVisible(false);
                order2comp.setVisible(false);
            }
        }
        
    }

    private String getStatLine(Character c) {
        String txt = "";
        txt += getStatText("C", c.getCommand(), c.getCommandTotal());
        txt += getStatText("A", c.getAgent(), c.getAgentTotal());
        txt += getStatText("E", c.getEmmisary(), c.getEmmisaryTotal());
        txt += getStatText("M", c.getMage(), c.getMageTotal());
        txt += getStatText("S", c.getStealth(), c.getStealthTotal());
        txt += getStatText("Cr", c.getChallenge(), c.getChallenge());
        txt += getStatText("H", c.getHealth(), c.getHealth());
        return txt;
    }

    private String getStatText(String prefix, int skill, int skillTotal) {
        if (skillTotal == 0 && skill == 0) return "";
        return prefix + skill + (skillTotal > skill ? "(" + skillTotal + ")" : "") + " ";
    }



    protected JComponent createFormControl() {
        getFormModel().setValidating(false);
        BindingFactory bf = getBindingFactory();
        GridBagLayoutBuilder glb = new GridBagLayoutBuilder();
        glb.setDefaultInsets(new Insets(0, 0, 0, 5));

        JComponent c;

        glb.append(c = new JTextField());
        characterName = (JTextField)c;
        c.setBorder(null);
        c.setFont(new Font(c.getFont().getName(), Font.BOLD, c.getFont().getSize()));
        c.setPreferredSize(new Dimension(160, 12));
        bf.bindControl(c, "name");
        glb.append(c = new JTextField());
        c.setBorder(null);
        nationTextBox = (JTextField)c;
        //bf.bindControl(c, "nationNo");
        c.setPreferredSize(new Dimension(50, 12));

        ImageSource imgSource = (ImageSource) Application.instance().getApplicationContext().getBean("imageSource");

        btnMenu = new JButton();
        Icon ico = new ImageIcon(imgSource.getImage("menu.icon"));
        btnMenu.setIcon(ico);
        btnMenu.setPreferredSize(new Dimension(16,16));
        glb.append(btnMenu);
        btnMenu.addActionListener(new PopupMenuActionListener()
        {
            public JPopupMenu getPopupMenu() {
                return createCharacterPopupContextMenu();
            }
        });

        glb.nextLine();

        glb.append(statsTextBox = new JTextField(), 2, 1);
        statsTextBox.setBorder(null);
        statsTextBox.setPreferredSize(new Dimension(100, 12));
        glb.nextLine();

        glb.append(artifactsTable = new JTable(), 2, 1);
        artifactsTable.setPreferredSize(new Dimension(150, 20));
        ArtifactTableModel tableModel =
            new ArtifactTableModel(this.getMessageSource()) {
                protected String[] createColumnPropertyNames() {
                    return new String[]{"no", "name"};
                }

                protected Class[] createColumnClasses() {
                    return new Class[]{String.class, String.class};
                }
            };
        tableModel.setRowNumbers(false);
        artifactsTable.setModel(tableModel);
        // todo think about this
        TableUtils.setTableColumnWidths(artifactsTable, new int[]{30, 120});
        artifactsTable.setBorder(null);

        glb.nextLine();

        glb.append(spellsTable = new JTable(), 2, 1);
        spellsTable.setPreferredSize(new Dimension(150, 12));
        ItemTableModel spellModel = new ItemTableModel(SpellProficiency.class, this.getMessageSource()) {
            protected String[] createColumnPropertyNames() {
                return new String[]{"spellId", "name", "proficiency"};
            }

            protected Class[] createColumnClasses() {
                return new Class[]{Integer.class, String.class, String.class};
            }
        };
        spellModel.setRowNumbers(false);
        spellsTable.setModel(spellModel);
        TableUtils.setTableColumnWidths(spellsTable, new int[]{30, 90, 30});
        spellsTable.setBorder(null);
        glb.nextLine();

        order1 = new OrderViewer(FormModelHelper.createFormModel(new Order(new Character())));
        glb.append(order1comp = order1.createFormControl(), 2, 1);
        glb.nextLine();
        order2 = new OrderViewer(FormModelHelper.createFormModel(new Order(new Character())));
        glb.append(order2comp = order2.createFormControl(), 2, 1);
        glb.nextLine();

        JPanel panel = glb.getPanel();
        panel.setBackground(Color.white);
        return panel;
    }

    private void refresh() {
        setFormObject(getFormObject());
    }

    private class ShowArtifactsCommand extends ActionCommand {
        protected void doExecuteCommand() {
            showArtifacts = !showArtifacts;
            refresh();
        }
    }

    private class ShowSpellsCommand extends ActionCommand {
        protected void doExecuteCommand() {
            showSpells = !showSpells;
            refresh();
        }
    }

    private class ShowOrdersCommand extends ActionCommand {
        protected void doExecuteCommand() {
            showOrders = !showOrders;
            refresh();
        }
    }

    private class ShowCharacterRangeOnMapCommand extends ActionCommand {
        protected void doExecuteCommand() {
            CharacterRangeMapItem crmi = new CharacterRangeMapItem((Character)getFormObject());
            org.joverseer.support.Container mic = (org.joverseer.support.Container)Application.instance().getApplicationContext().getBean("mapItemContainer");
            mic.removeAll(mic.items);
            AbstractMapItem.add(crmi);

            Application.instance().getApplicationContext().publishEvent(
                    new JOverseerEvent(LifecycleEventsEnum.SelectedHexChangedEvent.toString(), MapPanel.instance().getSelectedHex(), this));
        }
    }

    private JPopupMenu createCharacterPopupContextMenu() {
        Character c = (Character)getFormObject();
        showArtifactsCommand.setEnabled(c != null && c.getArtifacts().size() > 0);
        showSpellsCommand.setEnabled(c != null && c.getSpells().size() > 0);
        CommandGroup group = Application.instance().getActiveWindow().getCommandManager().createCommandGroup(
                "armyCommandGroup",
                new Object[]{showArtifactsCommand, showSpellsCommand, showOrdersCommand, "separator", showCharacterRangeOnMapCommand});
        return group.createPopupMenu();
    }
}

