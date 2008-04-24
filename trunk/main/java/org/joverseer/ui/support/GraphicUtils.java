package org.joverseer.ui.support;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Stroke;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.JTextComponent;

import org.joverseer.domain.Order;
import org.joverseer.preferences.PreferenceRegistry;
import org.joverseer.ui.map.renderers.OrderRenderer;
import org.joverseer.ui.map.renderers.Renderer;
import org.springframework.binding.convert.ConversionContext;
import org.springframework.binding.convert.support.AbstractConverter;
import org.springframework.richclient.application.Application;
import org.springframework.richclient.form.AbstractForm;

import com.jidesoft.spring.richclient.docking.JideApplicationWindow;
import com.jidesoft.swing.JideSwingUtilities;
import com.jidesoft.tipoftheday.ResourceBundleTipOfTheDaySource;
import com.jidesoft.tipoftheday.TipOfTheDayDialog;

/**
 * Various utilities for the gui
 * 
 * @author Marios Skounakis
 */
public class GraphicUtils {

    /**
     * Returns a font with the given params
     */
    public static Font getFont(String name, int style, int size) {
        return new Font(name, style, size);
    }

    /**
     * Returns a basic stroke with the given width
     */
    public static Stroke getBasicStroke(int width) {
        return new BasicStroke(width);
    }

    /**
     * Returns a dask stroke with the given width and dash size
     */
    public static Stroke getDashStroke(int width, int dashSize) {
        return new BasicStroke(width, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 10, new float[] {dashSize,
                dashSize}, 2);
    }

    /**
     * Returns a dot stroke with the given width
     */
    public static Stroke getDotStroke(int width) {
        return new BasicStroke(width, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 10, new float[] {width, width}, 2);
    }


    /**
     * Changes strings - "Unknown (Map Icon)" - "" to "Unknown"
     */
    public static String parseName(String name) {
        if (name.equals("Unknown (Map Icon)"))
            return "Unknown";
        if (name.equals(""))
            return "Unknown";
        return name;
    }

    /**
     * Shows a view (brings it to the foreground if it is open
     * 
     * @param id
     */
    public static void showView(String id) {
        JideApplicationWindow window = (JideApplicationWindow) Application.instance().getActiveWindow();
        window.getDockingManager().showFrame(id);
    }

    /**
     * True if given order can be rendered by the active order renderer
     */
    // TODO change to use all renderers (so that we can use more than one order renders and so that we don't need to
    // specifically define the orderRenderer bean
    public static boolean canRenderOrder(Order o) {
        Renderer orderRenderer = (Renderer) Application.instance().getApplicationContext().getBean("orderRenderer");
        if (orderRenderer == null)
            return false;
        return ((OrderRenderer) orderRenderer).canRender(o);
    }

    /**
     * Shows the tip of the day dialog
     */
    public static void showTipOfTheDay() {
        ResourceBundle rb;
        ResourceBundleTipOfTheDaySource tipOfTheDaySource = new ResourceBundleTipOfTheDaySource(rb = ResourceBundle
                .getBundle("tips"));
        int count = 0;
        Enumeration e = rb.getKeys();
        while (e.hasMoreElements()) {
            count++;
            e.nextElement();
        }
        tipOfTheDaySource.setCurrentTipIndex((int) (Math.random() * count));
        URL styleSheet = TipOfTheDayDialog.class.getResource("/tips.css");
        TipOfTheDayDialog dialog = new TipOfTheDayDialog((Frame) null, tipOfTheDaySource, new AbstractAction(
                "Show Tips on startup") {

            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) e.getSource();
                    PreferenceRegistry.instance().setPreferenceValue("general.tipOfTheDay",
                            checkBox.isSelected() ? "yes" : "no");
                }
            }
        }, styleSheet);
        dialog.setShowTooltip(PreferenceRegistry.instance().getPreferenceValue("general.tipOfTheDay").equals("yes"));
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setResizable(false);
        dialog.pack();
        JideSwingUtilities.globalCenterWindow(dialog);
        dialog.setModal(true);
        dialog.setVisible(true);
    }

    /**
     * Set a cell render for a given table column indexed by column index
     */
    public static void setTableColumnRenderer(JTable table, int iColumn, TableCellRenderer renderer) {
        table.getColumnModel().getColumn(iColumn).setCellRenderer(renderer);
    }

    /**
     * Spring has the annoying habbit to use property converters for integer fields in forms that use a dot to represent
     * thousands. This method registers new converters that don't use dots
     */
    public static void registerIntegerPropertyConverters(AbstractForm f, String property) {
        f.getFormModel().registerPropertyConverter(property, new AbstractConverter() {

            protected Object doConvert(Object arg0, Class arg1, ConversionContext arg2) throws Exception {
                return (arg0 == null ? 0 : arg0.equals("") ? 0 : Integer.parseInt(arg0.toString()));
            }

            public Class[] getSourceClasses() {
                return new Class[] {String.class};
            }

            public Class[] getTargetClasses() {
                return new Class[] {Integer.class, Object.class};
            }

        }, new AbstractConverter() {

            protected Object doConvert(Object arg0, Class arg1, ConversionContext arg2) throws Exception {
                if (arg0 == null) {
                    return "";
                }
                return arg0.toString();
            }

            public Class[] getSourceClasses() {
                return new Class[] {Integer.class, Object.class};
            }

            public Class[] getTargetClasses() {
                return new Class[] {String.class};
            }

        });
    }
}
