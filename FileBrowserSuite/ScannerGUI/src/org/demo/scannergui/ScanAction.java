/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.scannergui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "File",
id = "org.demo.scannergui.ScanAction")
@ActionRegistration(displayName = "#CTL_ScanAction")
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1300)
})
@Messages("CTL_ScanAction=Scan")
public final class ScanAction implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
    }
}
