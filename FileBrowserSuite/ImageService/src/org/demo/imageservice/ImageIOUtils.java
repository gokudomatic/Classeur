/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.demo.imageservice;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;

/**
 *
 * @author ECO
 */
public class ImageIOUtils {

    static BufferedImage getImage(FileObject fo){
        BufferedImage result = null;
        try {
            result = (BufferedImage) ImageIO.read(FileUtil.toFile(fo));
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return result;
    }
    
}
