/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.folderviewer;

import java.awt.Image;
import org.openide.filesystems.FileObject;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author edwin
 */
public class FolderNode extends AbstractNode {

    public FolderNode(Children children, Lookup lookup) {
        super(children, lookup);
    }

    public FolderNode(Children children) {
        super(children);
    }

    public static FolderNode getRootNode(FileObject root){
        FolderNode node=new FolderNode(Children.create(new FolderChildFactory(root.getFolders(false)),true),Lookups.singleton(root));
        node.setDisplayName(root.getName());
        return node;
    }
    
    @Override
    public Image getOpenedIcon(int type) {
        return ImageUtilities.loadImage("org/demo/folderviewer/icons/folder_open_16.png", true);
    }

    @Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage("org/demo/folderviewer/icons/folder_close_16.png", true);
    }
}
