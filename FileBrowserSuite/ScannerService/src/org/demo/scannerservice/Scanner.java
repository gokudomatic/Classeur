/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.scannerservice;

import java.util.List;

/**
 *
 * @author edwin
 */
public interface Scanner {

    public List<Double> getSupportedResolutions();

    public List<Integer> getSupportedBitDepth();
    
    public List<String> getSupportedModes();
}
