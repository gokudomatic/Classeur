/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.fileservice;

import org.demo.core.CentralLookup;
import org.openide.modules.ModuleInstall;
import org.openide.util.lookup.Lookups;

/**
 * Manages a module's lifecycle. Remember that an installer is optional and
 * often not needed at all.
 */
public class Installer extends ModuleInstall {

    @Override
    public void restored() {
        ExtensionMap exts= new ExtensionMap();
        Lookups.singleton(exts);
        CentralLookup.getDefault().add(exts);
    }
}
