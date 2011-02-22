/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ListViewPanel.java
 *
 * Created on 14 févr. 2011, 17:46:11
 */
package org.demo.filebrowser.explorer.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.BreakIterator;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;

/**
 *
 * @author ECO
 */
public class ListViewPanel extends javax.swing.JPanel {

    private final int width=128;
    private final int labelHeight=20;
    
    /** Creates new form ListViewPanel */
    public ListViewPanel(String caption) {
        initComponents();
        label.setText(caption);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label = new javax.swing.JLabel();
        imagePane = new ImagePane();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 6, 6, 6));
        setLayout(new java.awt.BorderLayout());

        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label.setLabelFor(imagePane);
        label.setText(org.openide.util.NbBundle.getMessage(ListViewPanel.class, "ListViewPanel.label.text")); // NOI18N
        label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        label.setPreferredSize(new java.awt.Dimension(width,labelHeight));
        label.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        add(label, java.awt.BorderLayout.SOUTH);

        imagePane.setOpaque(false);
        imagePane.setPreferredSize(new java.awt.Dimension(width,32));
        add(imagePane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel imagePane;
    private javax.swing.JLabel label;
    // End of variables declaration//GEN-END:variables

    /** set the text for this component to display
     * if the value of text is null or empty string, nothing is displayed.
     * @param text a string to display
     */
    public void setText(String text) {
	FontMetrics fm = label.getFontMetrics(label.getFont());
	Container container = label.getParent();
	int containerWidth = container.getWidth();
 
	BreakIterator boundary = BreakIterator.getWordInstance();
	boundary.setText(text);
 
	StringBuffer trial = new StringBuffer();
	StringBuilder real = new StringBuilder("<html>");
 
	int start = boundary.first();
	for (int end = boundary.next(); end != BreakIterator.DONE;
		start = end, end = boundary.next()) {
		String word = text.substring(start,end);
		trial.append(word);
		int trialWidth = SwingUtilities.computeStringWidth(fm,
			trial.toString());
		if (trialWidth > containerWidth) {
			trial = new StringBuffer(word);
			real.append("<br>");
		}
		real.append(word);
	}
 
	real.append("</html>");        
        
        
        label.setText(real.toString());
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        if (label != null) {
            label.setBackground(bg);
        }
    }

    @Override
    public void setForeground(Color fg) {
        super.setForeground(fg);
        if (label != null) {
            label.setForeground(fg);
        }
    }

    @Override
    public void setOpaque(boolean isOpaque) {
        super.setOpaque(false);
        if (label != null) {
            label.setOpaque(isOpaque);
        }
    }

    /** Returns the text string that the label displays.
     * @return a String
     */
    public String getText() {
        return label.getText();
    }

    /** set the image for this component to display
     * if the value of image is null or empty string, nothing is displayed.
     * @param image the image to display
     */
    public void setImage(BufferedImage image) {
        ((ImagePane) imagePane).setImage(image);
    }

    public void setImageRescale(double width, double height) {
        ((ImagePane) imagePane).imageRescale(width, height);
    }

    /** A component for the parent class to render an image */
    private class ImagePane extends JPanel {

        /** an image for this component */
        private BufferedImage image;
        /** the scale with for this component. */
        private double scaleWidth = 1.0;
        /** the scaleHeight for this component. */
        private double scaleHeight = 1.0;

        /** Constructs a <code>ImagePane</code> */
        public ImagePane() {
        }

        /** set the image for this component to display
         * if the value of image is null or empty string, nothing is displayed.
         * @param image the image to display
         */
        public void setImage(BufferedImage image) {
            this.image = image;
        }

        /** set the image rescale
         * @param width the width to which to scale the image.
         * @param height the height to which to scale the image.
         */
        public void imageRescale(double width, double height) {
            this.scaleWidth = width;
            this.scaleHeight = height;
        }

        /** invoked to draw this component
         * @param g the Graphics context in which to paint
         */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);    // paint background

            if (image == null) {
                return;
            }

            Graphics2D g2 = (Graphics2D) g;

            double w = image.getWidth() * scaleWidth;
            double h = image.getHeight() * scaleHeight;

            Dimension dim = getSize();
            double x = 0;
            double y = 0;

            if (dim.getWidth() > w) {
                x = (dim.getWidth() - w) / 2;
            }

            if (dim.getHeight() > h) {
                y = (dim.getHeight() - h) / 2;
            }

            g2.drawImage(image, (int) x, (int) y, (int) w, (int) h, this);
        }
    }
}
