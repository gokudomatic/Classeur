/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.demo.filebrowser;

import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author edwin
 */
public class FileNode extends AbstractNode {

    public FileNode(Children children, Lookup lookup) {
        super(children, lookup);
    }

    public static class FolderNodeChildren extends ChildFactory<FileNode> {

        @Override
        protected Node[] createNodes(Node n) {
            if (n.getLookup().lookup(DataFolder.class) != null) {
                return new Node[] {new FileNode(n)};
            } else {
                Feed feed = getFeed(n);
                if (feed != null) {
                    try {
                        return new Node[] {new OneFeedNode(n, feed.getSyndFeed())};
                    } catch (IOException ioe) {
                        Exceptions.printStackTrace(ioe);
                    }
                }
            }
            // best effort
            return new Node[] {new FilterNode(n)};
        }
        
        
    }
}
