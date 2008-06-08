package org.joverseer.ui.listviews;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import org.joverseer.domain.PopulationCenter;
import org.joverseer.game.TurnElementsEnum;
import org.joverseer.support.infoSources.InfoSource;
import org.joverseer.ui.listviews.commands.GenericCopyToClipboardCommand;
import org.joverseer.ui.listviews.commands.PopupMenuCommand;
import org.joverseer.ui.listviews.filters.AllegianceFilter;
import org.joverseer.ui.listviews.filters.NationFilter;
import org.joverseer.ui.listviews.renderers.PopCenterInfoSourceTableCellRenderer;
import org.joverseer.ui.support.transferHandlers.GenericExportTransferHandler;
import org.joverseer.ui.support.transferHandlers.GenericTransferable;
import org.springframework.richclient.table.ColumnToSort;
import org.springframework.richclient.table.SortableTableModel;

/**
 * List view for PopulationCenter objects
 * 
 * @author Marios Skounakis
 */
public class PopulationCenterListView extends ItemListView {

    public PopulationCenterListView() {
        super(TurnElementsEnum.PopulationCenter, PopulationCenterTableModel.class);
    }

    protected int[] columnWidths() {
        return new int[] {40, 96, 42, 64, 54, 80, 96, 68, 42, 42, 42, 42, 42, 42, 42, 42};
    }


    protected JComponent createControlImpl() {
        JComponent c = super.createControlImpl();
        table.setDefaultRenderer(InfoSource.class, new PopCenterInfoSourceTableCellRenderer(tableModel));
        return c;
    }

    protected AbstractListViewFilter[][] getFilters() {
        ArrayList filters = new ArrayList();
        filters.addAll(Arrays.asList(NationFilter.createNationFilters()));
        filters.addAll(Arrays.asList(AllegianceFilter.createAllegianceFilters()));
        return new AbstractListViewFilter[][] {(AbstractListViewFilter[]) filters
                .toArray(new AbstractListViewFilter[] {})};
    }

    protected ColumnToSort[] getDefaultSort() {
        return new ColumnToSort[] {new ColumnToSort(0, 2), new ColumnToSort(0, 1)};
    }
    
    protected JComponent[] getButtons() {
    	return new JComponent[]{
    			new PopupMenuCommand().getButton(new Object[]{
    					new GenericCopyToClipboardCommand(table)})};
	}

    /**
     * Drag and drop exports:
     * - PopulationCenter[] array of all selected items
     * - PopulationCenter the first selected item
     * - String representation of all selected items
     */
    protected void startDragAndDropAction(MouseEvent e) {
        final PopulationCenter[] selectedItems = new PopulationCenter[table.getSelectedRowCount()];
        String copyString = "";
        for (int i = 0; i < table.getSelectedRowCount(); i++) {
            int idx = ((SortableTableModel) table.getModel()).convertSortedIndexToDataIndex(table.getSelectedRows()[i]);
            PopulationCenter a = (PopulationCenter) tableModel.getRow(idx);
            selectedItems[i] = a;
            String ln = "";
            for (int j = 0; j < table.getColumnCount(); j++) {
                Object v = table.getValueAt(i, j);
                if (v == null)
                    v = "";
                ln += (ln.equals("") ? "" : "\t") + v;
            }
            copyString += (copyString.equals("") ? "" : "\n") + ln;
        }
        final String str = copyString;

        TransferHandler handler = new GenericExportTransferHandler() {

            protected Transferable createTransferable(JComponent arg0) {
                try {
                    Transferable t = new GenericTransferable(new DataFlavor[] {
                            new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + ";class=\""
                                    + PopulationCenter[].class.getName() + "\""),
                            new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + ";class="
                                    + PopulationCenter.class.getName()), DataFlavor.stringFlavor}, new Object[] {
                            selectedItems, selectedItems[0], str});
                    return t;
                } catch (Exception exc) {
                    exc.printStackTrace();
                    return null;
                }

            }
        };
        table.setTransferHandler(handler);
        handler.exportAsDrag(table, e, TransferHandler.COPY);
    }

}
