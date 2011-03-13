/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.folderviewer.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import org.demo.folderviewer.FolderNode;
import org.demo.folderviewer.FolderViewerTopComponent;
import org.openide.util.NbBundle;

public final class SetRootAction extends AbstractAction {

    public SetRootAction() {
        putValue(NAME, NbBundle.getMessage(FolderNode.class, "CTL_SetRootAction"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String oldRootPath = FolderViewerTopComponent.getDefault().getRootFolder();

        if (oldRootPath == null) {
            oldRootPath = ".";
        }

        int result;
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(oldRootPath));
        chooser.setDialogTitle("Select root folder");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //    
        String newRootPath;
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            newRootPath = chooser.getSelectedFile().getAbsolutePath();
        } else {
            newRootPath = oldRootPath;
        }

        FolderViewerTopComponent.getDefault().setRootFolder(newRootPath);

    }
}
