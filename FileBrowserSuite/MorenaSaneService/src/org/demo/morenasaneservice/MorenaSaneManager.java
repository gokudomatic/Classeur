/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.morenasaneservice;

import SK.gnome.morena.MorenaSource;
import SK.gnome.sane.SaneConnection;
import SK.gnome.sane.SaneException;
import SK.gnome.sane.SaneSource;
import SK.gnome.twain.TwainException;
import SK.gnome.twain.TwainManager;
import SK.gnome.twain.TwainSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.EventListenerList;
import org.demo.morena.MorenaScanner;
import org.demo.scannerservice.Parameters;
import org.demo.scannerservice.Scanner;
import org.demo.scannerservice.ScannerManager;
import org.demo.scannerservice.ScannerListener;

/**
 *
 * @author ECO
 */
public class MorenaSaneManager implements ScannerManager {

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
        return "Morena Sane Manager";
    }

    @Override
    public String toString() {
        return getDescription();
    }

    @Override
    public Collection<Scanner> getListDevices() {
        Collection<Scanner> result = new ArrayList<Scanner>();
        try {

            SaneConnection conn = SaneConnection.connect("localhost");
            SaneSource[] listSources = conn.listSources();

            for (MorenaSource src : listSources) {
                result.add(new MorenaScanner(src));
            }

        } catch (SaneException ex) {
            Logger.getLogger(MorenaSaneManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MorenaSaneManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
