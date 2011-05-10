/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.fileservice;

/**
 *
 * @author edwin
 */
public class Extension {

    private String extension;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Extension(String extension, String description) {
        this.extension = extension;
        this.description = description;
    }
    
    @Override
    public String toString() {
        return description+" (*."+extension+")";
    }
    
}
