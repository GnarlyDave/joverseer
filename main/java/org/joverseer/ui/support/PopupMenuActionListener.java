package org.joverseer.ui.support;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public abstract class PopupMenuActionListener implements ActionListener {
    public abstract JPopupMenu getPopupMenu();

    public void actionPerformed(ActionEvent e) {
        JPopupMenu pm = getPopupMenu();
        JComponent cmp = (JComponent)e.getSource();
        pm.show(cmp, 0, cmp.getHeight());
    }
}
