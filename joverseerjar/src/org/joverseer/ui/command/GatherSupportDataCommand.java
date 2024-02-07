package org.joverseer.ui.command;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JTextArea;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.joverseer.JOApplication;
import org.joverseer.ui.support.Messages;
import org.springframework.richclient.application.ApplicationDescriptor;
import org.springframework.richclient.command.AbstractCommand;
import org.springframework.richclient.command.ActionCommand;
import org.springframework.richclient.dialog.ApplicationDialog;

public class GatherSupportDataCommand extends ActionCommand {

	final String EOL="\r\n";
	JTextArea textArea;
	public GatherSupportDataCommand() {
		super("GatherSupportDataCommand");
	}

	@Override
	protected void doExecuteCommand() {
		ApplicationDescriptor descriptor = JOApplication.getApplicationDescriptor();

		String report = "Version:" +descriptor.getVersion() + this.EOL
				+ SystemProperties();
		this.textArea = new JTextArea();
		this.textArea.append(report);
		this.textArea.append(this.ScreenInfo());
		Logger l = Logger.getRootLogger();
		Appender a = l.getAppender("joverseerfileappender");
		
		if (a != null) {
			if (a instanceof org.apache.log4j.FileAppender) {
				this.textArea.append(((org.apache.log4j.FileAppender)a).getFile());
			}
		}
        ApplicationDialog dialog = new ApplicationDialog() {

            @Override
			protected boolean onFinish() {
                return true;
            }

            @Override
			protected Object[] getCommandGroupMembers() {
                return new AbstractCommand[] {
                        getFinishCommand()
                };
            }

			@Override
			protected JComponent createDialogContentPane() {
				return GatherSupportDataCommand.this.textArea;
			}
        };
        dialog.setTitle(Messages.getString("GatherSupportDataCommand.title"));
        dialog.showDialog();

	}
	private void reportProperty(StringBuilder sb,String prop)
	{
		sb.append(prop);
		sb.append(":");
		sb.append(System.getProperty(prop));
		sb.append(this.EOL);
	}
//TODO: maybe use https://github.com/oshi/oshi for diagnostics.
	public String SystemProperties()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("system properties"+this.EOL);
		reportProperty(sb,"java.home");
		reportProperty(sb,"java.vendor");
		reportProperty(sb,"java.vendor.url");
		reportProperty(sb,"java.version");
		reportProperty(sb,"os.arch");
		reportProperty(sb,"os.name");
		
		sb.append("Note: some versions of java incorrectly report Windows 11 as 10."+this.EOL);
		reportProperty(sb,"os.version");
		reportProperty(sb,"sun.java2d.uiScale");
		reportProperty(sb,"sun.java2d.dpiaware");
		reportProperty(sb,"sun.java2d.ddoffscreen");
		reportProperty(sb,"sun.java2d.d3d");
		reportProperty(sb,"sun.java2d.noddraw");

		return sb.toString();
	}
        
	public String ScreenInfo()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		return String.format("reported size = %.0f by %.0f\r\n",width,height);
	}

}
