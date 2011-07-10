/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.morena;

import SK.gnome.morena.MorenaSource;
import SK.gnome.sane.SaneSource;
import SK.gnome.twain.TwainException;
import SK.gnome.twain.TwainSource;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.demo.scannerservice.Scanner;

/**
 *
 * @author ECO
 */
public class MorenaScanner implements Scanner {

    private MorenaSource src;
    private String name;
    private double[] supportedResolutions=null;
    private int[] supportedBitDepth=null;
    
    public MorenaScanner(MorenaSource src) {
        this.src=src;
        name=src.toString();
        
        if(src instanceof TwainSource){
            TwainSource twainsrc=(TwainSource) src;
            try {
                supportedResolutions=twainsrc.getSupportedXResolution();
                supportedBitDepth=twainsrc.getSupportedBitDepth();
            } catch (TwainException ex) {
                Logger.getLogger(MorenaScanner.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else if(src instanceof SaneSource){
            SaneSource sanesrc=(SaneSource) src;
            
        }
        
    }

    @Override
    public String toString() {
        return name;
    }

    public double[] getSupportedResolutions() {
        return supportedResolutions;
    }

    public int[] getSupportedBitDepth() {
        return supportedBitDepth;
    }

    public List<String> getSupportedModes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
