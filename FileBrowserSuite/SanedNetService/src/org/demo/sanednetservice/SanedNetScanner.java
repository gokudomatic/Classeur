/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.sanednetservice;

import org.demo.scannerservice.Scanner;
import uk.org.jsane.JSane_Base.JSane_Base_Device;

/**
 *
 * @author edwin
 */
public class SanedNetScanner implements Scanner{

    private final String name;
    
    @Override
    public String toString() {
        return name;
    }

    SanedNetScanner(JSane_Base_Device device) {
        name=device.getName();
    }
    
}
