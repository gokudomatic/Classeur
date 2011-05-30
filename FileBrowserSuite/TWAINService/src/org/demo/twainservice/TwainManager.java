/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.twainservice;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.EventListenerList;
import org.demo.scannerservice.Parameters;
import org.demo.scannerservice.ScannerDevice;
import org.demo.scannerservice.ScannerListener;
import uk.co.mmscomputing.device.scanner.Scanner;
import uk.co.mmscomputing.device.scanner.ScannerIOException;
import uk.co.mmscomputing.device.scanner.ScannerIOMetadata;
import uk.co.mmscomputing.device.scanner.ScannerIOMetadata.Type;

/**
 *
 * @author edwin
 */
public class TwainDevice implements ScannerDevice, uk.co.mmscomputing.device.scanner.ScannerListener {

    private static Scanner scanner = null;
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
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                if (scanner == null) {
                    scanner = Scanner.getDevice();
                    scanner.addListener(TwainDevice.this);
                }
                try {
                    scanner.select();
                    scanner.acquire();
                } catch (ScannerIOException ex) {
                    Logger.getLogger(TwainDevice.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        t.start();
    }

    protected void fireScanPerformed(BufferedImage img) {
        final ScannerListener[] list = listeners.getListeners(ScannerListener.class);
        for (ScannerListener l : list) {
            l.imageAcquired(img);
        }
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
    public void update(Type type, ScannerIOMetadata metadata) {
        try {
            if (type.equals(ScannerIOMetadata.ACQUIRED)) {
                BufferedImage image = metadata.getImage();
                fireScanPerformed(image);
            } else if (type.equals(ScannerIOMetadata.STATECHANGE)) {
                System.out.println(metadata.getStateStr());
            } else if (type.equals(ScannerIOMetadata.EXCEPTION)) {
                metadata.getException().printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "mmsComputing jTwain";
    }

    @Override
    public String toString() {
        return getDescription();
    }

    @Override
    public Collection<org.demo.scannerservice.Scanner> getListDevices() {
        Collection<org.demo.scannerservice.Scanner> result = new ArrayList<org.demo.scannerservice.Scanner>();
        try {
            String[] deviceNames = Scanner.getDevice().getDeviceNames();

            for (String deviceName : deviceNames) {
                result.add(new TwainScanner(deviceName));
            }

        } catch (ScannerIOException ex) {
            Logger.getLogger(TwainDevice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
