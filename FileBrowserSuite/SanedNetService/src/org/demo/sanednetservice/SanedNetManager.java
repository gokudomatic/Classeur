package org.demo.sanednetservice;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.EventListenerList;
import org.demo.scannerservice.Parameters;
import org.demo.scannerservice.Scanner;
import org.demo.scannerservice.ScannerManager;
import org.demo.scannerservice.ScannerListener;
import uk.org.jsane.JSane_Base.JSane_Base_Device;
import uk.org.jsane.JSane_Base.JSane_Base_Frame;
import uk.org.jsane.JSane_Exceptions.JSane_Exception;
import uk.org.jsane.JSane_Net.JSane_Net_Connection;

/**
 *
 * @author edwin
 */
public class SanedNetManager implements ScannerManager {

    private final EventListenerList listeners = new EventListenerList();

    @Override
    public void acquire() {

        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    JSane_Net_Connection con;
                    con = new JSane_Net_Connection("127.0.0.1",
                            6566);
                    try {
                        JSane_Base_Device device = con.getDevice(0);

                        device.open();
                        JSane_Base_Frame frame = device.getFrame();
                        BufferedImage image = frame.getImage();
                        // got the image. notify listeners
                        SanedNetManager.this.fireScanPerformed(image);

                        device.close();

                    } finally {
                        con.exit();
                    }


                } catch (JSane_Exception ex) {
                    Logger.getLogger(SanedNetManager.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(SanedNetManager.class.getName()).log(Level.SEVERE, null, ex);
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
    public void addListener(ScannerListener listener) {
        listeners.add(ScannerListener.class, listener);
    }

    @Override
    public void removeListener(ScannerListener listener) {
        listeners.remove(ScannerListener.class, listener);
    }

    @Override
    public String getDescription() {
        return "Sane-net";
    }

    @Override
    public String toString() {
        return getDescription();
    }

    @Override
    public Collection<Scanner> getListDevices() {
        Collection<Scanner> result=new ArrayList<Scanner>();
        try {
            JSane_Net_Connection con;
            con = new JSane_Net_Connection("127.0.0.1",
                    6566);
            try {
                Vector devices = con.getDevices();

                for (Object absDevice : devices) {
                    JSane_Base_Device device = (JSane_Base_Device) absDevice;
                    result.add(new SanedNetScanner(device));
                }

            } finally {
                con.exit();
            }


        } catch (JSane_Exception ex) {
            Logger.getLogger(SanedNetManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SanedNetManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
}
