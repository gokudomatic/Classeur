/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.demo.scannerservice;

import java.awt.image.BufferedImage;
import java.util.EventListener;

/**
 *
 * @author edwin
 */
public interface ScannerListener extends EventListener {

    public void statusUpdated();
    
    public void imageAcquired(BufferedImage image);
}
