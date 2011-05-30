
package org.demo.scannerservice;

import java.util.Collection;

/**
 *
 * @author edwin
 */
public interface ScannerDevice {

    public void addListener(ScannerListener listener);
    public void removeListener(ScannerListener listener);
    
    public void acquire();

    public void setConfiguration(Parameters cfg);
    
    public Parameters getConfiguration();
    
    public String getDescription();
    
    public Collection<Scanner> getListDevices();
    
}
