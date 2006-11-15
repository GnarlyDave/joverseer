package org.joverseer.ui.command;

import org.springframework.richclient.command.ActionCommand;
import org.springframework.richclient.filechooser.FileChooserUtils;
import org.springframework.richclient.application.Application;
import org.springframework.richclient.application.event.LifecycleApplicationEvent;
import org.springframework.richclient.dialog.*;
import org.springframework.richclient.image.ImageSource;
import org.springframework.richclient.form.FormModelHelper;
import org.springframework.binding.form.FormModel;
import org.joverseer.support.readers.xml.TurnXmlReader;
import org.joverseer.support.GameHolder;
import org.joverseer.ui.events.GameChangedListener;
import org.joverseer.ui.events.SelectedHexChangedListener;
import org.joverseer.ui.events.GameChangedEvent;
import org.joverseer.ui.SimpleLifecycleAdvisor;
import org.joverseer.ui.LifecycleEventsEnum;
import org.joverseer.ui.JOverseerClientProgressMonitor;
import org.joverseer.ui.orders.OrderEditorForm;
import org.joverseer.ui.support.JOverseerEvent;
import org.joverseer.domain.Order;

import java.io.File;
import java.util.EventListener;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: mskounak
 * Date: 22 ��� 2006
 * Time: 10:41:02 ��
 * To change this template use File | Settings | File Templates.
 */
public class OpenXmlFile extends ActionCommand {

    public OpenXmlFile() {
        super("openXmlFileCommand");
    }

    protected void doExecuteCommand() {
        File file = FileChooserUtils.showFileChooser(Application.instance().getActiveWindow().getControl(), ".xml", "Select", "Xml Turn File");
        try {
            GameHolder gh = (GameHolder)Application.instance().getApplicationContext().getBean("gameHolder");
            final TurnXmlReader r = new TurnXmlReader(gh.getGame(), file.getAbsolutePath());
            FormModel formModel = FormModelHelper.createFormModel(r);
            final JOverseerClientProgressMonitor monitor = new JOverseerClientProgressMonitor(formModel);
            FormBackedDialogPage page = new FormBackedDialogPage(monitor);
            TitledPageApplicationDialog dialog = new TitledPageApplicationDialog(page) {
                protected void onAboutToShow() {
                    monitor.taskStarted("Import XML Turn.", 100);
                    r.setMonitor(monitor);
                    Thread t = new Thread(r);
                    t.start();
                }

                protected boolean onFinish() {
                    return true;
                }

                protected ActionCommand getCancelCommand() {
                    return null;
                }
            };

            //dialog.setTitle();
            dialog.showDialog();

            Application.instance().getApplicationContext().publishEvent(
                    new JOverseerEvent(LifecycleEventsEnum.GameChangedEvent.toString(), gh.getGame(), this));

        }
        catch (Exception exc) {
            //todo create message handler class
            logger.error(exc);
            MessageDialog dlg = new MessageDialog(String.format("Error importing Xml Turn file %s.", file.getAbsolutePath()), exc.getMessage());
            dlg.showDialog();
            return;
        }
        try {
            //todo create message handler class
            String msg = String.format("Xml Turn '%s' imported succesfully.", new String[]{file.getAbsolutePath()});
            Application.instance().getActiveWindow().getStatusBar().setMessage(msg);
        }
        catch (Exception exc) {
            // do nothing
        }
    }

}
