
package org.demo.scannerservice;

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
    
}
