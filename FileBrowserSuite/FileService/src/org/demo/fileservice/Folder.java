/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.demo.fileservice;

import java.io.File;
import java.net.URI;

/**
 *
 * @author edwin
 */
public class Folder extends File {

    public Folder(URI uri) {
        super(uri);
    }

    public Folder(File parent, String child) {
        super(parent, child);
    }

    public Folder(String parent, String child) {
        super(parent, child);
    }

    public Folder(String pathname) {
        super(pathname);
    }

    public Folder(File file) {
        super(file.toURI());
    }

}
