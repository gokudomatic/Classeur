/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.morenatwainservice;

import SK.gnome.morena.MorenaSource;
import SK.gnome.twain.TwainException;
import SK.gnome.twain.TwainManager;
import SK.gnome.twain.TwainSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.EventListenerList;
import org.demo.scannerservice.Parameters;
import org.demo.scannerservice.Scanner;
import org.demo.scannerservice.ScannerDevice;
import org.demo.scannerservice.ScannerListener;

/**
 *
 * @author ECO
 */
public class MorenaTwainDevice implements ScannerDevice {

    private final EventListenerList listeners = new EventListenerList();    
    
    @Override
    public void addListener(ScannerListener listener) {
        listeners.add(ScannerListener.class, listener);
    }

    @Override
    public void removeListener(ScannerListener listener) {
        listeners.remove(ScannerListener.class, listener);
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

    @Override
    public String getDescription() {
        return "Morena Twain Manager";
    }

    @Override
    public String toString() {
        return getDescription();
    }    
    
    @Override
    public Collection<Scanner> getListDevices() {
        Collection<Scanner> result = new ArrayList<Scanner>();
        try {
            TwainSource[] listSources = TwainManager.listSources();
            
            for (TwainSource twainSource : listSources) {
                result.add(new MorenaScanner(twainSource));
            }
        } catch (TwainException ex) {
            Logger.getLogger(MorenaTwainDevice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
}
