/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.folderviewer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.demo.fileservice.Folder;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author edwin
 */
public class FolderChildFactory extends ChildFactory<Folder> {

    private List<Folder> src;

    public FolderChildFactory(List<Folder> data) {
        src = data;
    }

    FolderChildFactory(Folder file) {
        src = new ArrayList<Folder>();
        src.add(file);
    }

    @Override
    protected boolean createKeys(List<Folder> toPopulate) {
        for (Folder file : src) {
            toPopulate.add(file);
        }
        return true;
    }

    @Override
    protected Node createNodeForKey(Folder key) {

        File[] children = key.listFiles();

        List<Folder> subfolders = new ArrayList<Folder>();
        for (File subitem : children) {
            if (subitem.isDirectory()) {
                subfolders.add(new Folder(subitem));
            }
        }
        Node node;
        if (subfolders.size() > 0) {
            node = new AbstractNode(Children.create(new FolderChildFactory(subfolders), true),Lookups.singleton(key));
        } else {
            node=new AbstractNode(Children.LEAF,Lookups.singleton(key));
        }

        node.setDisplayName(key.getName());
        node.setShortDescription(key.getAbsolutePath());
        
        return node;
    }
}
