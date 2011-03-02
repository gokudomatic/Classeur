/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.fileViewer;

import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author ECO
 */
public class JImagePreviewPanel extends JPanel {

    public JImagePreviewPanel() {
        /*this.addComponentListener(new ComponentAdapter() {
        
        @Override
        public void componentResized(ComponentEvent e) {
        super.componentResized(e);
        
        }
        
        });*/
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
    private BufferedImage image;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {

            if (g.drawImage(image, 0, 0, this)) {
                int width = image.getWidth(this);
                int height = image.getHeight(this);

                g.drawImage(image, width, 0, width + width / 2, height / 2,
                        0, 0, width, height, this);
            }
        }
    }
}
