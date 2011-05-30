/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.folderviewer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import org.openide.filesystems.FileObject;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author edwin
 */
public class FolderChildFactory extends ChildFactory<FileObject> {

    private List<FileObject> src;

    public FolderChildFactory(List<FileObject> data) {
        src = data;
    }

    public FolderChildFactory(Enumeration<? extends FileObject> e){
        src = Collections.list((Enumeration<FileObject>)e);
    }
    
    FolderChildFactory(FileObject file) {
        src = new ArrayList<FileObject>();
        src.add(file);
    }

    @Override
    protected boolean createKeys(List<FileObject> toPopulate) {
        toPopulate.addAll(src);
        return true;
    }

        
    @Override
    protected Node createNodeForKey(FileObject key) {

        Enumeration<? extends FileObject> subfolders=key.getFolders(false);
        Node node;
        if (subfolders.hasMoreElements()) {
            node = new FolderNode(Children.create(new FolderChildFactory(subfolders), true), Lookups.singleton(key));
        } else {
            node = new FolderNode(Children.LEAF, Lookups.singleton(key));
        }

        node.setDisplayName(key.getName());
        node.setShortDescription(key.getPath());

        return node;
    }
}
