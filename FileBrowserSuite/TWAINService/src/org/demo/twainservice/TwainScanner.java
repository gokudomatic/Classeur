/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.twainservice;

import java.util.List;
import org.demo.scannerservice.Scanner;
import uk.co.mmscomputing.device.twain.TwainIdentity;

/**
 *
 * @author ECO
 */
public class TwainScanner implements Scanner {
    
    private TwainIdentity src;

    public TwainScanner(TwainIdentity identity) {
        this.src = identity;
    }

    @Override
    public String toString() {
        return src.getProductName();
    }


    @Override
    public List<String> getSupportedModes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Double> getSupportedResolutions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Integer> getSupportedBitDepth() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
