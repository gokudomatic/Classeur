/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.demo.twainservice;

import org.demo.scannerservice.Parameters;
import org.demo.scannerservice.ScannerDevice;
import org.demo.scannerservice.ScannerListener;

/**
 *
 * @author edwin
 */
public class TwainDevice implements ScannerDevice{

    @Override
    public void addListener(ScannerListener listener) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeListener(ScannerListener listener) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void acquire() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setConfiguration(Parameters cfg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Parameters getConfiguration() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
