/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.morenaservice;

import SK.gnome.twain.TwainSource;
import org.demo.scannerservice.Scanner;

/**
 *
 * @author ECO
 */
public class MorenaScanner implements Scanner {

    private String name;
    
    MorenaScanner(TwainSource twainSource) {
        name=twainSource.toString();
    }

    @Override
    public String toString() {
        return name;
    }
    
}
