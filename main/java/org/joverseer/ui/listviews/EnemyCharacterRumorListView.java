package org.joverseer.ui.listviews;

import java.util.ArrayList;

import javax.swing.JComponent;

import org.joverseer.support.Container;
import org.joverseer.ui.domain.EnemyCharacterRumorWrapper;
import org.joverseer.ui.listviews.commands.GenericCopyToClipboardCommand;
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

	@Override
	protected int[] columnWidths() {
		return new int[] { 120, 64, 64, 64, 64, 64, 240, 120 };
	}

<<<<<<< .mine
    protected void setItems() {
        Container thieves = EnemyCharacterRumorWrapper.getAgentWrappers(false);
        ArrayList filteredItems = new ArrayList();
        for (EnemyCharacterRumorWrapper w : (ArrayList<EnemyCharacterRumorWrapper>)thieves.getItems()) {
        	if (getActiveFilter().accept(w)) filteredItems.add(w);
        }
        tableModel.setRows(filteredItems);
        tableModel.fireTableDataChanged();
    }
    
    protected JComponent[] getButtons() {
    	return new JComponent[]{
    			new PopupMenuCommand().getButton(new Object[]{
    					new GenericCopyToClipboardCommand(table),
    					//new ListViewDescriptionPopupCommand("enemyCharacterRumorListView")
    					})};
=======
	@Override
	protected JComponent createControlImpl() {
		JComponent comp = super.createControlImpl();
		// table.setDefaultRenderer(Boolean.class, new
		// JTable().getDefaultRenderer(Boolean.class));
		return comp;
>>>>>>> .r563
	}

	@Override
	protected void setItems() {
		Container thieves = EnemyCharacterRumorWrapper.getAgentWrappers(false);
		ArrayList filteredItems = new ArrayList();
		for (EnemyCharacterRumorWrapper w : (ArrayList<EnemyCharacterRumorWrapper>) thieves.getItems()) {
			if (getActiveFilter().accept(w))
				filteredItems.add(w);
		}
		tableModel.setRows(filteredItems);
		tableModel.fireTableDataChanged();
	}

	@Override
	protected JComponent[] getButtons() {
		return new JComponent[] { new PopupMenuCommand().getButton(new Object[] { new GenericCopyToClipboardCommand(table),
		// new ListViewDescriptionPopupCommand("enemyCharacterRumorListView")
				}) };
	}

	@Override
	protected AbstractListViewFilter[][] getFilters() {
		return new AbstractListViewFilter[][] { new AbstractListViewFilter[] { new AbstractListViewFilter("") {
			@Override
			public boolean accept(Object obj) {
				return true;
			}
		}, new AbstractListViewFilter("Agents") {
			@Override
			public boolean accept(Object obj) {
				EnemyCharacterRumorWrapper w = (EnemyCharacterRumorWrapper) obj;
				return (w.getCharType().equals("agent"));
			}
		}, new AbstractListViewFilter("Emissaries") {
			@Override
			public boolean accept(Object obj) {
				EnemyCharacterRumorWrapper w = (EnemyCharacterRumorWrapper) obj;
				return (w.getCharType().equals("emmisary"));
			}
		} } };

	}

}
