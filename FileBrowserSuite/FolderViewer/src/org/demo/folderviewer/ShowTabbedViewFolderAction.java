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
id = "org.demo.folderviewer.ShowTabbedViewFolderAction")
@ActionRegistration(iconBase = "org/demo/folderviewer/icons/pane-blog.png",
displayName = "#CTL_ShowTabbedViewFolderAction")
@ActionReferences({
    @ActionReference(path = "Toolbars/FolderViewToolbar", position = 500)
})
public final class ShowTabbedViewFolderAction implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
    }
}
