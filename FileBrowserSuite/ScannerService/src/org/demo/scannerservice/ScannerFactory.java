/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.demo.scannerservice;

import org.openide.util.Lookup;

/**
 *
 * @author edwin
 */
public class ScannerFactory {

    public static ScannerDevice getManager(){
        
        return Lookup.getDefault().lookup(ScannerDevice.class);
    }
    
}
