/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.filebrowser;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import org.demo.fileservice.Folder;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author edwin
 */
public class FileNode extends AbstractNode {

    private FileObject key=null;
    
    public FileNode(Children children) {
        super(children);
    }    
    
    public FileNode(FileObject key) {
        super(Children.LEAF, Lookups.singleton(key));
        this.key=key;
    }

    private static final class CustomFileFilter implements FileFilter {

        @Override
        public boolean accept(File pathname) {
            return pathname.isFile();
        }
    };

    public static class FileNodeChildren extends ChildFactory<FileObject> {

        private List<FileObject> src = null;

        public FileNodeChildren(Folder src) {
            if (src != null) {
                final File[] listFiles = src.listFiles(new CustomFileFilter());
                if (listFiles != null) {
                    this.src=new ArrayList<FileObject>();
                    for (File file : listFiles) {
                        this.src.add(FileUtil.toFileObject(file));
                    }
                }
            }
        }

        @Override
        protected boolean createKeys(List<FileObject> toPopulate) {
            if (src != null) {
                toPopulate.addAll(src);
            }
            return true;
        }

        @Override
        protected Node createNodeForKey(FileObject key) {
            Node node = new FileNode(key);
            node.setDisplayName(key.getName());
            node.setShortDescription(key.getName());

            return node;
        }
    }
}
