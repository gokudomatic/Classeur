/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.morena;

import SK.gnome.morena.MorenaSource;
import org.demo.scannerservice.Scanner;

/**
 *
 * @author ECO
 */
public class MorenaScanner implements Scanner {

    private String name;
    
    public MorenaScanner(MorenaSource src) {
        name=src.toString();
    }

    @Override
    public String toString() {
        return name;
    }
    
}
