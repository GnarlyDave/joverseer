package org.joverseer.ui.support;

import javax.swing.JScrollPane;

import org.springframework.richclient.application.Application;

public class UIUtils {
	public static String renderEnum(Object enumValue) {
		String str = enumValue.getClass().getSimpleName() + "." + enumValue.toString();
		return Application.instance().getApplicationContext().getMessage(str, new Object[]{}, null);
		
	}
	
	public static void fixScrollPaneMouseScroll(JScrollPane scp) {
		scp.getVerticalScrollBar().setUnitIncrement(25);
        scp.getHorizontalScrollBar().setUnitIncrement(25);
	}
}
