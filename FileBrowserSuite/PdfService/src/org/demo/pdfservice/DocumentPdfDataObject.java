/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.pdfservice;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import org.demo.fileservice.Thumbnail;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataNode;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.nodes.Node;
import org.openide.nodes.Children;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

public class DocumentPdfDataObject extends MultiDataObject implements Thumbnail {

    public DocumentPdfDataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
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
        BufferedImage result = null;
        try {
            FileObject fo = getPrimaryFile();
            File file = FileUtil.toFile(fo);

            RandomAccessFile raf = new RandomAccessFile(file, "r");
            FileChannel channel = raf.getChannel();
            ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            PDFFile pdfFile = new PDFFile(buf);

            PDFPage page = pdfFile.getPage(1);
            result = getImageFromPdfFile(page);

        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return result;
    }

    private BufferedImage getImageFromPdfFile(PDFPage page) {
        //get the width and height for the doc at the default zoom 
        Rectangle rect = new Rectangle(0, 0,
                (int) page.getWidth(),
                (int) page.getHeight());

        //generate the image
        BufferedImage result = (BufferedImage) page.getImage(
                rect.width, rect.height, //width & height
                rect, // clip rect
                null, // null for the ImageObserver
                true, // fill background with white
                true // block until drawing is done
                );

        return result;
    }
}
