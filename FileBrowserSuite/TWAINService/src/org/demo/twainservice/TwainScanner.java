/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.twainservice;

import org.demo.scannerservice.Scanner;

/**
 *
 * @author ECO
 */
public class TwainScanner implements Scanner {
    
    private String name;

    public TwainScanner(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
