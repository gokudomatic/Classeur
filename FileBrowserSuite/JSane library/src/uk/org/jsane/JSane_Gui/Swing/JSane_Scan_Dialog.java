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

package uk.org.jsane.JSane_Gui.Swing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import uk.org.jsane.JSane_Base.JSane_Base_Device;
import uk.org.jsane.JSane_Base.JSane_Base_Frame;
import uk.org.jsane.JSane_Base.JSane_Base_Option_Type_Descriptor;
import uk.org.jsane.JSane_Exceptions.JSane_Exception;
import uk.org.jsane.JSane_Gui.Interfaces.JSane_Widget_Wrapper_Interface;
import uk.org.jsane.JSane_Gui.Swing.JSane_Swing_Connection_Dialog.ConnectionDetails;
import uk.org.jsane.JSane_Gui.Swing.JSane_Swing_Helper.Panda_Position;
import uk.org.jsane.JSane_Net.JSane_Net_Connection;

/**
 * @author Andi McLean
 *  
 */
public class JSane_Scan_Dialog extends JDialog
{
	private JButton _scan;

	private JButton _cancel;

	private JSane_Base_Device _device;

	private JSane_Base_Frame _frame = null;

	private Vector _components = new Vector();

	private static HashMap _options = new HashMap();

	private static String m_hostname = "";

	private static int m_port;

	private JSane_Scan_Dialog( JSane_Base_Device device , Vector options )
	{
		super( (Frame) null , true );
		_device = device;

		try
		{
			JTabbedPane panel = _createOptions( options );

			Panda_Position pos = new Panda_Position();

			getContentPane().setLayout( new GridBagLayout() );

			getContentPane().add(
				panel ,
				JSane_Swing_Helper.getNewGridBagConstraints(
					GridBagConstraints.BOTH , pos , 1 , 1 , 1 , 1 ) );

			pos.nextRow();

			getContentPane().add(
				_getButtons() ,
				JSane_Swing_Helper.getNewGridBagConstraints(
					GridBagConstraints.HORIZONTAL , pos , 1 , 0 , 1 , 1 ) );
		}
		catch (JSane_Exception e)
		{
		}
		pack();

		setTitle( "Scan" );
	}

	public Dimension getPreferredSize()
	{
		Dimension size = super.getPreferredSize();

		size.height = 300;

		return size;
	}

	private JPanel _getButtons()
	{
		JPanel panel = new JPanel();

		panel.setLayout( new GridBagLayout() );

		Panda_Position pos = new Panda_Position();

		_scan = new JButton( "Scan" );
		_scan.addActionListener( new ActionListener()
		{

			public void actionPerformed( ActionEvent e )
			{

				try
				{
					int number = _device.getNumberOptions();

					for ( int loop = 0; loop < number; ++loop )
					{
						JSane_Widget_Wrapper_Interface component = (JSane_Widget_Wrapper_Interface) _components
							.elementAt( loop );

						if ( component != null )
						{
							JSane_Base_Option_Type_Descriptor option = _device
								.getOption( loop );
							if ( option != null && component.hasValueChanged())
							{
								try
								{
									option.setValue( component.getWidgetValue() );
								}
								catch (JSane_Exception e1)
								{

								}
							}
						}
					}

					_frame = _device.getFrame();
					show( false );
				}
				catch (HeadlessException e1)
				{
					JOptionPane.showMessageDialog(
						null , e1.getMessage() , "" , JOptionPane.ERROR_MESSAGE );
				}
				catch (JSane_Exception e1)
				{
					JOptionPane.showMessageDialog(
						null , e1.getMessage() , "" , JOptionPane.ERROR_MESSAGE );
				}

			}
		} );

		_cancel = new JButton( "Cancel" );
		_cancel.addActionListener( new ActionListener()
		{

			public void actionPerformed( ActionEvent e )
			{
				_frame = null;
				show( false );
			}
		} );

		panel.add( new JLabel( "" ) , JSane_Swing_Helper.getNewGridBagConstraints(
			GridBagConstraints.HORIZONTAL , pos , 1 , 0 , 1 , 1 ) );

		panel.add( _scan , JSane_Swing_Helper.getNewGridBagConstraints(
			GridBagConstraints.NONE , pos , 0 , 0 , 1 , 1 ) );

		panel.add( _cancel , JSane_Swing_Helper.getNewGridBagConstraints(
			GridBagConstraints.NONE , pos , 0 , 0 , 1 , 1 ) );

		return panel;
	}

	/**
	 * @param options
	 * @return
	 * @throws JSane_Exception
	 */
	/*
	 * Date : Dec 30, 2004 Author : panda Date : Author Reason Dec 30, 2004 panda
	 * First added Updates comments
	 */
	private JTabbedPane _createOptions( Vector options ) throws JSane_Exception
	{
		JSane_Swing_Widget_Factory factory = new JSane_Swing_Widget_Factory();

		JTabbedPane panel = new JTabbedPane();

		int number = _device.getNumberOptions();

		JPanel groupPanel = _getNewGroupPanel();

		Panda_Position groupPos = new Panda_Position();

		Panda_Position pos = new Panda_Position();

		String title = "";

		int count = 0;

		for ( int loop = 0; loop < number; ++loop )
		{
			
			JSane_Base_Option_Type_Descriptor option = _device.getOption( loop );

			JSane_Widget_Wrapper_Interface component = option.getWidget( factory );

			_components.add(  component );

			if ( component != null  )
			{

				component.setWidgetOptions( option.getConstraint() );
				if ( options == null )
				{
					component.setWidgetValue( option.getValue() );
				}
				else
				{
					component.setWidgetValue( options.elementAt( loop ) );
				}

				if ( "FILLER".compareToIgnoreCase( option.getName().getString() ) != 0
					&& !"".equals( option.getName().getString() ) )
				{
					groupPanel.add(
						new JLabel( option.getTitle().getString() ) , JSane_Swing_Helper
							.getNewGridBagConstraints(
								GridBagConstraints.HORIZONTAL , groupPos , 1 , 0 , 1 , 1 ) );
					groupPanel.add( (Component) component , JSane_Swing_Helper
						.getNewGridBagConstraints(
							GridBagConstraints.HORIZONTAL , groupPos , 1 , 0 , 1 , 1 ) );
					groupPos.nextRow();
					count++;
				}
			}
			else
			{
				if ( count > 0 )
				{
					groupPanel.add( new JLabel() , JSane_Swing_Helper
						.getNewGridBagConstraints(
							GridBagConstraints.BOTH , groupPos , 1 , 1 , 1 , 1 ) );
					JScrollPane scroll = new JScrollPane();
					scroll.setViewportView( groupPanel );

					panel.addTab( title , scroll );
				}

				count = 0;

				groupPos = new Panda_Position();
				title = option.getTitle().getString();

				groupPanel = this._getNewGroupPanel();
			}

		}
		return panel;
	}

	/**
	 * @return
	 */
	/*
	 * Date		: Jan 7, 2005 
	 * Author	: panda 
	 * Date		:	Author		Reason
	 * Jan 7, 2005		panda		First added Updates comments
	 */
	private JPanel _getNewGroupPanel()
	{
		JPanel panel = new JPanel();

		panel.setLayout( new GridBagLayout() );
		panel.setBorder( BorderFactory.createEmptyBorder( 10 , 10 , 10 , 10 ) );

		return panel;
	}

	/**
	 * @return
	 * @throws JSane_Exception
	 */
	/*
	 * Date		: Jan 6, 2005 
	 * Author	: panda 
	 * Date		:	Author		Reason
	 * Jan 6, 2005		panda		First added Updates comments
	 */
	private Vector getOptions() throws JSane_Exception
	{
		Vector vec = new Vector();

		int number = _device.getNumberOptions();

		for ( int loop = 0; loop < number; ++loop )
		{
			JSane_Widget_Wrapper_Interface component = (JSane_Widget_Wrapper_Interface) _components
				.elementAt( loop );

			if ( component != null )
			{
				JSane_Base_Option_Type_Descriptor option = _device.getOption( loop );
				if ( option != null )
				{
					vec.add( component.getWidgetValue() );
				}
			}
			else
			{
				vec.add( "" );
			}
		}

		return vec;
	}

	public static JSane_Base_Frame getScan( JSane_Base_Device device )
		throws JSane_Exception
	{
		if ( device != null )
		{
			device.open();
			JSane_Scan_Dialog dialog = new JSane_Scan_Dialog(
				device , (Vector) _options.get( device.getName() ) );

			dialog.show();

			if ( dialog._frame != null )
			{
				_options.put( device.getName() , dialog.getOptions() );
			}

			device.close();
			return dialog._frame;
		}
		return null;
	}

	public static JSane_Base_Frame getScan( String hostname , int port )
		throws JSane_Exception , UnknownHostException , IOException
	{
		JSane_Base_Device device = null;

		if (hostname == null ) {
			if (m_hostname.length() == 0) {
				ConnectionDetails details = JSane_Swing_Device_Dialog.getConnection(m_hostname , port);
				m_hostname = details.getHostname();
				m_port = details.getPort();
			}
			hostname = m_hostname;
			port = m_port;
		}
		
		JSane_Net_Connection connection = new JSane_Net_Connection( hostname , port );

		device = JSane_Swing_Device_Dialog.getDevice( connection );

		JSane_Base_Frame frame = getScan( device );

		connection.exit();

		return frame;
	}
}