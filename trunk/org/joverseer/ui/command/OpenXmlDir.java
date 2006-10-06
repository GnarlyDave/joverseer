package org.joverseer.ui.command;

import org.springframework.richclient.command.ActionCommand;
import org.springframework.richclient.filechooser.FileChooserUtils;
import org.springframework.richclient.filechooser.DefaultFileFilter;
import org.springframework.richclient.application.Application;
import org.joverseer.support.readers.xml.TurnXmlReader;
import org.joverseer.support.GameHolder;

import javax.swing.*;
import java.io.FileFilter;
import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by IntelliJ IDEA.
 * User: mskounak
 * Date: 22 ��� 2006
 * Time: 11:10:47 ��
 * To change this template use File | Settings | File Templates.
 */
public class OpenXmlDir extends ActionCommand {
    

    public OpenXmlDir() {
        super("openXmlDirCommand");
    }

    protected void doExecuteCommand() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fileChooser.showOpenDialog(Application.instance().getActiveWindow().getControl()) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            File[] files = file.listFiles();
            GameHolder gh = (GameHolder)Application.instance().getApplicationContext().getBean("gameHolder");
            for (File f : files) {
                if (f.getAbsolutePath().endsWith(".xml")) {
                    try {
                        TurnXmlReader r = new TurnXmlReader();
                        r.readFile(f.getAbsolutePath());
                        r.updateGame(gh.getGame());
                    }
                    catch (Exception exc) {
                        // do nothing
                        // todo fix
                    }
                }
            }
        }
    }
}
