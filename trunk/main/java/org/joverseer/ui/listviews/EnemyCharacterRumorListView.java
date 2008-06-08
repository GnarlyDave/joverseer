package org.joverseer.ui.listviews;

import javax.swing.JComponent;
import javax.swing.JTable;

import org.joverseer.support.Container;
import org.joverseer.ui.domain.EnemyCharacterRumorWrapper;
import org.joverseer.ui.listviews.commands.GenericCopyToClipboardCommand;
import org.joverseer.ui.listviews.commands.ListViewDescriptionPopupCommand;
import org.joverseer.ui.listviews.commands.PopupMenuCommand;

/**
 * List view for Enemy Character Rumors
 * 
 * @author Marios Skounakis
 */
public class EnemyCharacterRumorListView extends BaseItemListView {


    public EnemyCharacterRumorListView() {
        super(EnemyCharacterRumorTableModel.class);
        
    }

    protected int[] columnWidths() {
        return new int[]{120, 64, 64, 240};
    }
    
    
    protected JComponent createControlImpl() {
        JComponent comp = super.createControlImpl();
        table.setDefaultRenderer(Boolean.class, new JTable().getDefaultRenderer(Boolean.class));
        return comp;
    }

    protected void setItems() {
        Container thieves = EnemyCharacterRumorWrapper.getAgentWrappers();
        tableModel.setRows(thieves.getItems());
        tableModel.fireTableDataChanged();
    }
    
    protected JComponent[] getButtons() {
    	return new JComponent[]{
    			new PopupMenuCommand().getButton(new Object[]{
    					new GenericCopyToClipboardCommand(table),
    					//new ListViewDescriptionPopupCommand("enemyCharacterRumorListView")
    					})};
	}
}
