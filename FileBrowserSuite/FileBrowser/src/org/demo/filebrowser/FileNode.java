/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.demo.filebrowser;

import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;

/**
 *
 * @author edwin
 */
public class FileNode extends FilterNode {

    public FileNode(Node original) {
        super(original, new FolderNodeChildren(original));
    }

    
    public static class RootFileNode extends FileNode {

        public RootFileNode() throws DataObjectNotFoundException {
            super(DataObject.find(
                    FileUtil.getConfigFile("RssFeeds")).getNodeDelegate());
        }

        @Override
        public String getDisplayName() {
            return super.getDisplayName();
        }
        
    }
    
    private static class FolderNodeChildren extends FilterNode.Children {
        FolderNodeChildren(Node original){
            super(original);
        }

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
