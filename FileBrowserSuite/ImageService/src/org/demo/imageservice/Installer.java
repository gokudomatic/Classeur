/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.imageservice;

import org.demo.core.CentralLookup;
import org.demo.fileservice.Extension;
import org.demo.fileservice.ExtensionMap;
import org.openide.modules.ModuleInstall;

/**
 * Manages a module's lifecycle. Remember that an installer is optional and
 * often not needed at all.
 */
public class Installer extends ModuleInstall {

    @Override
    public void restored() {
        ExtensionMap exts=CentralLookup.getDefault().lookup(ExtensionMap.class);
        exts.add(new Extension(ImagePngDataObject.EXTENSION, ImagePngDataObject.DESCRIPTION));
        exts.add(new Extension(ImageJpegDataObject.EXTENSION, ImageJpegDataObject.DESCRIPTION));
    }
}
