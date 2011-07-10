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
import uk.org.jsane.JSane_Base.JSane_Base_Option_Type_Descriptor;
import uk.org.jsane.JSane_Base.JSane_Base_Type_Group;
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

    private void test() {
        try {
            JSane_Net_Connection connection = new JSane_Net_Connection("127.0.0.1", 6566);

            for (int deviceNum = 0;
                    deviceNum < connection.getNumberDevices();
                    ++deviceNum) {
                JSane_Base_Device device = connection.getDevice(deviceNum);
                if (device != null) {
                    System.out.println("Device = " + device);

                    if (device != null) {
                        device.open();
                        int options = device.getNumberOptions();
                        for (int loop = 0; loop < options; ++loop) {
                            JSane_Base_Option_Type_Descriptor option = device.getOption(loop);
                            System.out.println(option);

                            if (!(option.getValueType() instanceof JSane_Base_Type_Group)) {
                                System.out.println(device.getOption(loop).getValue());
                            }
                        }
                        device.close();
                    } else {
                        System.out.println("Open failed");
                    }
                }
            }
            connection.exit();
        } catch (IOException e) {
            System.out.println("Exception - " + e);

        } catch (JSane_Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Scanner> getListDevices() {
        Collection<Scanner> result = new ArrayList<Scanner>();

        try {
            JSane_Net_Connection connection = new JSane_Net_Connection("127.0.0.1",
                    6566);
            try {
                for (int deviceNum = 0;
                        deviceNum < connection.getNumberDevices();
                        ++deviceNum) {
                    JSane_Base_Device device = connection.getDevice(deviceNum);

                    if (device != null) {
                        device.open();
                        result.add(new SanedNetScanner(device));
                        device.close();
                    }
                }

            } finally {
                connection.exit();
            }


        } catch (JSane_Exception ex) {
            Logger.getLogger(SanedNetManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SanedNetManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
}
