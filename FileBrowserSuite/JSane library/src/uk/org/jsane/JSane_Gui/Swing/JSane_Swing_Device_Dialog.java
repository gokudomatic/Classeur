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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uk.org.jsane.JSane_Base.JSane_Base_Device;
import uk.org.jsane.JSane_Exceptions.JSane_Exception;
import uk.org.jsane.JSane_Gui.Swing.JSane_Swing_Connection_Dialog.ConnectionDetails;
import uk.org.jsane.JSane_Gui.Swing.JSane_Swing_Helper.Panda_Position;
import uk.org.jsane.JSane_Net.JSane_Net_Connection;

/**
 * @author Andi McLean
 *  
 */
public class JSane_Swing_Device_Dialog extends JDialog
{
	private static String _deviceName = null;

	private JSane_Base_Device _device;

	private JComboBox _list;

	private JButton _ok;

	private JButton _cancel;

	/**
	 * @param devices
	 */
	public JSane_Swing_Device_Dialog( Vector devices )
	{
		setModal( true );
		_device = null;

		Panda_Position pos = new Panda_Position();

		getContentPane().setLayout( new GridBagLayout() );

		getContentPane().add(
			_makeMainArea( devices ) ,
			JSane_Swing_Helper.getNewGridBagConstraints(
				GridBagConstraints.BOTH , pos , 1 , 1 , 1 , 1 ) );

		pos.nextRow();

		getContentPane().add(
			_getButtons() ,
			JSane_Swing_Helper.getNewGridBagConstraints(
				GridBagConstraints.HORIZONTAL , pos , 1 , 0 , 1 , 1 ) );

		pack();
	}

	private Component _makeMainArea( Vector devices )
	{
		JScrollPane scroll = new JScrollPane();

		_list = new JComboBox( devices );

		scroll.setViewportView( _list );

		return scroll;
	}

	private JPanel _getButtons()
	{
		JPanel panel = new JPanel();

		panel.setLayout( new GridBagLayout() );

		Panda_Position pos = new Panda_Position();

		_ok = new JButton( "OK" );
		_ok.addActionListener( new ActionListener()
		{

			public void actionPerformed( ActionEvent e )
			{
				_device = (JSane_Base_Device) _list.getSelectedItem();
				hide();
			}
		} );

		_cancel= new JButton( "Cancel" );
		_cancel.addActionListener( new ActionListener()
		{

			public void actionPerformed( ActionEvent e )
			{
				_device=null;
				hide();
			}
		} );

		panel.add( new JLabel( "" ) , JSane_Swing_Helper.getNewGridBagConstraints(
			GridBagConstraints.HORIZONTAL , pos , 1 , 0 , 1 , 1 ) );

		panel.add( _ok , JSane_Swing_Helper.getNewGridBagConstraints(
			GridBagConstraints.NONE , pos , 0 , 0 , 1 , 1 ) );

		panel.add( _cancel , JSane_Swing_Helper.getNewGridBagConstraints(
			GridBagConstraints.NONE , pos , 0 , 0 , 1 , 1 ) );

		return panel;
	}

	/**
	 * @param devices
	 * @return
	 * @throws IOException
	 * @throws JSane_Exception
	 */
	/*
	 * Date : Dec 30, 2004 Author : panda Date : Author Reason Dec 30, 2004 panda
	 * First added Updates comments
	 */
	public static JSane_Base_Device getDevice( JSane_Net_Connection connection )
		throws JSane_Exception , IOException
	{
		if ( _deviceName != null )
		{
			return connection.getDevice( _deviceName );
		}

		Vector devices = connection.getDevices();

		if ( devices.size() == 0 )
		{
			JOptionPane.showMessageDialog( null , "No devices found");
			return null;
		}

/*		if ( devices.size() == 1 )
		{
			return (JSane_Base_Device) devices.elementAt( 0 );
		}*/
		JSane_Swing_Device_Dialog dialog = new JSane_Swing_Device_Dialog( devices );

		dialog.show(  );

		JSane_Base_Device device = dialog._device;

		if ( device != null )
		{
			_deviceName = device.getName();
		}

		return device;
	}

	public static ConnectionDetails getConnection( String hostname , int port )
	{
		JSane_Swing_Connection_Dialog dialog = new JSane_Swing_Connection_Dialog(hostname , port);
		dialog.show();
		
		return dialog.getConnectionDetails();
	}
}