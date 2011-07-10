/*
 * Created on Nov 18, 2006
 *
 * @ Andi McLean 2005
 */
package uk.org.jsane.JSane_Gui.Swing;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uk.org.jsane.JSane_Base.JSane_Base_Device;
import uk.org.jsane.JSane_Gui.Swing.JSane_Swing_Helper.Panda_Position;

public class JSane_Swing_Connection_Dialog extends JDialog
{

	public static class ConnectionDetails
	{

		private String m_hostname;
		private int m_port;
		
		public ConnectionDetails( String name , int port )
		{
			m_hostname = name;
			m_port = port;
		}
		
		public String getHostname()
		{
			return m_hostname;
		}
		
		public int getPort()
		{
			return m_port;
		}

	}

	private JTextField m_port;;
	private JTextField m_hostname;
	private JButton m_ok;

	public JSane_Swing_Connection_Dialog( String hostname , int port )
	{
		setModal( true );

		m_hostname = new JTextField(hostname);
		m_port = new JTextField("" + port);
		
		Panda_Position pos = new Panda_Position();

		getContentPane().setLayout( new GridBagLayout() );

		getContentPane().add(
			_makeMainArea(  ) ,
			JSane_Swing_Helper.getNewGridBagConstraints(
				GridBagConstraints.BOTH , pos , 1 , 1 , 1 , 1 ) );

		pos.nextRow();

		getContentPane().add(
			_getButtons() ,
			JSane_Swing_Helper.getNewGridBagConstraints(
				GridBagConstraints.HORIZONTAL , pos , 1 , 0 , 1 , 1 ) );

		pack();
	}

	private Component _makeMainArea()
	{
		JPanel panel = new JPanel();
		
		panel.setLayout( new GridBagLayout() );

		Panda_Position pos = new Panda_Position();

		panel.add( new JLabel( "Hostname" ) , JSane_Swing_Helper.getNewGridBagConstraints(
				GridBagConstraints.NONE , pos , 0 , 0 , 1 , 1 ) );

		panel.add( m_hostname , JSane_Swing_Helper.getNewGridBagConstraints(
				GridBagConstraints.HORIZONTAL , pos , 1 , 0 , 1 , 1 ) );
		pos.nextRow();
		
		panel.add( new JLabel( "Port" ) , JSane_Swing_Helper.getNewGridBagConstraints(
				GridBagConstraints.NONE , pos , 0 , 0 , 1 , 1 ) );

		panel.add( m_port , JSane_Swing_Helper.getNewGridBagConstraints(
				GridBagConstraints.HORIZONTAL , pos , 1 , 0 , 1 , 1 ) );
		pos.nextRow();
		
		return panel;
	}

	private JPanel _getButtons()
	{
		JPanel panel = new JPanel();

		panel.setLayout( new GridBagLayout() );

		Panda_Position pos = new Panda_Position();

		m_ok = new JButton( "OK" );
		m_ok.addActionListener( new ActionListener()
		{

			public void actionPerformed( ActionEvent e )
			{
				hide();
			}
		} );


		panel.add( new JLabel( "" ) , JSane_Swing_Helper.getNewGridBagConstraints(
			GridBagConstraints.HORIZONTAL , pos , 1 , 0 , 1 , 1 ) );

		panel.add( m_ok , JSane_Swing_Helper.getNewGridBagConstraints(
			GridBagConstraints.NONE , pos , 0 , 0 , 1 , 1 ) );

		return panel;
	}
	public ConnectionDetails getConnectionDetails()
	{
		return new ConnectionDetails(m_hostname.getText() , Integer.parseInt( m_port.getText() ));
	}

}
