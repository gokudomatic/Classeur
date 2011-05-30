/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.morenatwainservice;

import SK.gnome.twain.TwainSource;
import org.demo.scannerservice.Scanner;

/**
 *
 * @author ECO
 */
public class MorenaTwainScanner implements Scanner {
    
    private final TwainSource src;

    public MorenaTwainScanner(TwainSource src) {
        this.src = src;
    }

    public TwainSource getSrc() {
        return src;
    }
    
    public double[] getSupportedResolutions(){
        return null;
    }
    
}
