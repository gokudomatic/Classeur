/*
 *  JSane
 *
 *  Copyright 2004 - 2006 Andi McLean 
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package uk.org.jsane.JSane_Frontends;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import uk.org.jsane.JSane_Base.JSane_Base_Frame;
import uk.org.jsane.JSane_Exceptions.JSane_Exception;
import uk.org.jsane.JSane_Gui.Swing.JSane_Scan_Dialog;

/**
 * @author Andi McLean
 *  
 */
public class Gui_Scanner extends JFrame
{
	/**
	 * @author Andi McLean
	 *  
	 */
	public class PerformSaveAs_Action extends AbstractAction
	{

		public PerformSaveAs_Action()
		{
			super( "Scan" , new ImageIcon( PerformSaveAs_Action.class
				.getResource( "/uk/org/jsane/JSane_Gui/images/filesaveas.gif" ) ) );

		}

		/*
		 * Overridden method
		 */
		public void actionPerformed( ActionEvent e )
		{
			if ( _bufImage != null )
			{
				try
				{
					JFileChooser chooser = new JFileChooser();

					int returnVal = chooser.showSaveDialog(null);
					
					if ( returnVal == JFileChooser.APPROVE_OPTION )
					{

						ImageIO.write( _bufImage , "png" , new FileOutputStream( chooser.getSelectedFile() ) );
					}
				}
				catch (FileNotFoundException e1)
				{
				}
				catch (IOException e1)
				{
				}
			}
		}

	}

	private BufferedImage _bufImage;

	/**
	 * @author Andi McLean
	 *  
	 */
	public class PerformScan_Action extends AbstractAction
	{

		public PerformScan_Action()
		{
			super( "Scan" , new ImageIcon( PerformScan_Action.class
				.getResource( "/uk/org/jsane/JSane_Gui/images/scanner.gif" ) ) );
		}

		/*
		 * Overridden method
		 */
		public void actionPerformed( ActionEvent e )
		{
			try
			{
				JSane_Base_Frame frame = JSane_Scan_Dialog.getScan( null , 6566 );

				if ( frame != null )
				{
					BufferedImage image = frame.getImage( false );

					if ( image != null )
					{
						_bufImage = image;

						_image.setIcon( new ImageIcon( image ) );
					}
				}

			}
			catch (UnknownHostException e1)
			{
				JOptionPane.showMessageDialog( null ,"An error occured " + e1.getLocalizedMessage() );
				
			}
			catch (JSane_Exception e1)
			{
				JOptionPane.showMessageDialog( null ,"An error occured " + e1.getLocalizedMessage() );
			}
			catch (IOException e1)
			{
				JOptionPane.showMessageDialog( null ,"An error occured " + e1.getLocalizedMessage() );
			}
		}

	}

	public static void main( String[] args )
	{
		Gui_Scanner app = new Gui_Scanner();

		app.show( true );

	}

	private JLabel _image;

	public Gui_Scanner()
	{
		addWindowListener( new java.awt.event.WindowAdapter()
		{
			public void windowClosing( java.awt.event.WindowEvent evt )
			{
				System.exit( 0 );
			}
		} );

		_image = new JLabel( "" );
		JScrollPane scroll = new JScrollPane();

		scroll.setViewportView( _image );

		getContentPane().setLayout( new BorderLayout() );
		getContentPane().add( scroll , "Center" );
		getContentPane().add( _getToolbar() , "North" );

		pack();
	}

	private JToolBar _getToolbar()
	{
		JToolBar toolbar = new JToolBar();

		toolbar.add( new PerformScan_Action() );
		toolbar.add( new PerformSaveAs_Action() );

		return toolbar;
	}
}