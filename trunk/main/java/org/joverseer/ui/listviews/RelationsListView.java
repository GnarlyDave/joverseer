package org.joverseer.ui.listviews;

import java.awt.Color;
import java.awt.Component;
import java.util.Locale;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import org.joverseer.game.TurnElementsEnum;
import org.springframework.context.MessageSource;
import org.springframework.richclient.application.Application;


public class RelationsListView extends ItemListView {
    public RelationsListView() {
        super(TurnElementsEnum.NationRelation, RelationsTableModel.class);
    }
    
    
    protected JComponent createControlImpl() {
        JComponent c = super.createControlImpl();
        table.setDefaultRenderer(String.class, new RelationsTableCellRenderer());
        return c;
    }



    protected int[] columnWidths() {
        return new int[]{64, 96, 
                        32, 32, 32, 32, 32, 
                        32, 32, 32, 32, 32,
                        32, 32, 32, 32, 32,
                        32, 32, 32, 32, 32,
                        32, 32, 32, 32, 32};
    }

    protected void setItems() {
        super.setItems();
        try {
            for (int i=1; i<26; i++) {
                table.getColumnModel().getColumn(i+1).setHeaderValue(tableModel.getColumnName(i+1));
            }
        } catch (Exception exc) {};
    }
    
    
    public class RelationsTableCellRenderer extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            MessageSource colorSource = (MessageSource)Application.instance().getApplicationContext().getBean("colorSource");
            String relation = value.toString();
            Color bgColor = Color.WHITE;
            if (relation.equals("F")) {
                bgColor = Color.decode(colorSource.getMessage("relations.friendly.color", null, Locale.getDefault()));
            } else if (relation.equals("T")) {
                bgColor = Color.decode(colorSource.getMessage("relations.tolerated.color", null, Locale.getDefault()));
            } else if (relation.equals("D")) {
                bgColor = Color.decode(colorSource.getMessage("relations.disliked.color", null, Locale.getDefault()));
            } else if (relation.equals("H")) {
                bgColor = Color.decode(colorSource.getMessage("relations.hated.color", null, Locale.getDefault()));
            }
            JLabel lbl = ((JLabel)c);
            c.setBackground(bgColor);
            return lbl;
        }
        
    }
}
