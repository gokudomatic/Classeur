/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.demo.fileservice;

import java.io.File;
import java.net.URI;

/**
 *
 * @author ECO
 */
public class BrowserFile extends File {

    public BrowserFile(URI uri) {
        super(uri);
    }

    public BrowserFile(java.io.File parent, String child) {
        super(parent, child);
    }

    public BrowserFile(String parent, String child) {
        super(parent, child);
    }

    public BrowserFile(String pathname) {
        super(pathname);
    }

    public BrowserFile(File source){
        super(source.toURI());
    }
    
}
