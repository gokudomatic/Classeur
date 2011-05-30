/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.demo.scannerservice;

import java.util.Collection;
import org.openide.util.Lookup;

/**
 *
 * @author edwin
 */
public class ScannerFactory {

    public static ScannerManager getManager(){
        
        return Lookup.getDefault().lookup(ScannerManager.class);
    }
    
    public static Collection<ScannerManager> getManagerList(){
        return (Collection<ScannerManager>) Lookup.getDefault().lookupAll(ScannerManager.class);
    }
    
}
