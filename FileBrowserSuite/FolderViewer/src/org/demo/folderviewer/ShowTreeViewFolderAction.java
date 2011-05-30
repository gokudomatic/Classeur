/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.folderviewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;

@ActionID(category = "View",
id = "org.demo.folderviewer.ShowTreeViewFolderAction")
@ActionRegistration(iconBase = "org/demo/folderviewer/icons/pane-tree.png",displayName = "#CTL_ShowTreeViewFolderAction")
@ActionReferences({
    @ActionReference(path = "Toolbars/FolderViewToolbar", position = 500)
})
public final class ShowTreeViewFolderAction implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        FolderViewerTopComponent folderView=FolderViewerTopComponent.findInstance();
        
        folderView.setTreeViewMode();
    }
}
