/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.imageservice;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.demo.fileservice.Thumbnail;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataNode;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.nodes.CookieSet;
import org.openide.nodes.Node;
import org.openide.nodes.Children;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

public class ImagePngDataObject extends MultiDataObject implements Thumbnail {

    public ImagePngDataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
        super(pf, loader);

    }

    @Override
    protected Node createNodeDelegate() {
        return new DataNode(this, Children.LEAF, getLookup());
    }

    @Override
    public Lookup getLookup() {
        return getCookieSet().getLookup();
    }

    @Override
    public BufferedImage getThumbnail() {
        FileObject fo= getPrimaryFile();
        BufferedImage result=null;
        try {
            result = (BufferedImage) ImageIO.read(FileUtil.toFile(fo));
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return result;
    }
}
