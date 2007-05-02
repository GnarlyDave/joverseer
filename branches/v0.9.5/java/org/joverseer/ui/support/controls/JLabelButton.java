package org.joverseer.ui.support.controls;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JLabel;


public class JLabelButton extends JLabel {
    ArrayList<ActionListener> actionListeners = new ArrayList<ActionListener>();
    
    public JLabelButton() {
        super();
        init();
    }

    public JLabelButton(Icon image, int horizontalAlignment) {
        super(image, horizontalAlignment);
        init();
    }

    public JLabelButton(Icon image) {
        super(image);
        init();
    }

    public JLabelButton(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
        init();
    }

    public JLabelButton(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
        init();
    }

    public JLabelButton(String text) {
        super(text);
        init();
    }

    private void init() {
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                for (ActionListener a : actionListeners) {
                    a.actionPerformed(new ActionEvent(arg0.getSource(), 0, ""));
                }
            }
        });
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
    public void addActionListener(ActionListener a) {
        actionListeners.add(a);
    }
    
}
