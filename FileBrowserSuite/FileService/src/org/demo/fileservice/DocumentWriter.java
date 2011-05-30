/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.fileservice;

import java.awt.image.BufferedImage;

/**
 *
 * @author edwin
 */
public interface DocumentWriter {
    
    public void write(BufferedImage source);

    public String getExtension();
    
}
