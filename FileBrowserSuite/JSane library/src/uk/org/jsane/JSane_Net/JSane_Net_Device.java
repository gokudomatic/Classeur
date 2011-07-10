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

package uk.org.jsane.JSane_Net;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import uk.org.jsane.JSane_Base.JSane_Base_Connection;
import uk.org.jsane.JSane_Base.JSane_Base_Device;
import uk.org.jsane.JSane_Base.JSane_Base_Frame;
import uk.org.jsane.JSane_Base.JSane_Base_Option_Type_Descriptor;
import uk.org.jsane.JSane_Base.JSane_Base_Parameters;
import uk.org.jsane.JSane_Base.JSane_Base_Type;
import uk.org.jsane.JSane_Base.JSane_Base_Type_Word;
import uk.org.jsane.JSane_Exceptions.JSane_Exception;
import uk.org.jsane.JSane_Exceptions.JSane_Exception_IoError;

/**
 * Represents a device that JSane can commicate with.
 * @author Andi McLean
 */
public class JSane_Net_Device extends JSane_Base_Device implements
	JSane_Net_Transport
{
	private JSane_Net_Type_String _name = new JSane_Net_Type_String();

	private JSane_Net_Type_String _vendor = new JSane_Net_Type_String();

	private JSane_Net_Type_String _model = new JSane_Net_Type_String();

	private JSane_Net_Type_String _type = new JSane_Net_Type_String();

	protected JSane_Net_Connection _connection;

	private JSane_Net_Type_Word _handle;

	private boolean _isOpen;

	private Vector _options = null;

	private HashMap _map = null;

	/**
	 * Constructor.
	 * @param connection The connection used to talk to saned
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public JSane_Net_Device( JSane_Net_Connection connection )
	{
		super();
		_connection = connection;
		_handle = null;
		_isOpen = false;
	}

	/*
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public void open() throws JSane_Exception
	{
		if ( _isOpen )
		{
			return;
		}
		_open();
	}

	/*
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public void close() throws JSane_Exception
	{
		if ( _isOpen )
		{
			try
			{
				_connection.net_close( _handle );
				_isOpen = false;
				_handle = null;
			}
			catch (IOException e)
			{
				throw new JSane_Exception_IoError( e.getMessage() );
			}
		}
	}

	/*
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public JSane_Base_Option_Type_Descriptor getOption( int pos )
		throws JSane_Exception
	{
		try
		{
			if ( _options == null )
			{
				_getOptions();
			}
			if ( _options != null )
			{
				if ( pos >= 0 && pos < _options.size() )
				{
					return (JSane_Base_Option_Type_Descriptor) _options.elementAt( pos );
				}
			}
			return null;
		}
		catch (JSane_Exception e)
		{
			throw new JSane_Exception_IoError( e.getMessage() );
		}
	}

	/*
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public int getNumberOptions() throws JSane_Exception
	{
		if ( _options == null )
		{
			_getOptions();
		}
		if ( _options != null )
		{
			return _options.size();
		}
		return 0;
	}

	/*
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public JSane_Base_Option_Type_Descriptor getOption( String name )
		throws JSane_Exception
	{
		if ( _map == null )
		{
			_getOptions();
		}
		if ( _options != null )
		{
			name = name.toUpperCase();
			return (JSane_Net_Option_Type_Descriptor) _map.get( name );
		}
		return null;
	}

	/**
	 * Get the handle of the device.
	 * Could be undefined if device has not yet been open, or has been closed.
	 * @return Device handle if it's been opened.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	protected JSane_Net_Type_Word _getHandle()
	{
		return _handle;
	}

	/*
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	private void _getOptions() throws JSane_Exception
	{
		try
		{
			_options = _connection.net_get_option_descriptors( this );
			_makeHashMap();
		}
		catch (IOException e)
		{
			throw new JSane_Exception_IoError( e.getMessage() );
		}
	}

	/*
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	private void _makeHashMap() throws JSane_Exception
	{
		_map = new HashMap();

		for ( int loop = 0; loop < getNumberOptions(); ++loop )
		{
			JSane_Base_Option_Type_Descriptor opt = getOption( loop );
			String name = opt.getName().toString().toUpperCase();

			_map.put( name , opt );
		}
	}

	/*
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	private void _open() throws JSane_Exception
	{
		try
		{
			JSane_Net_Connection.Net_open_reply reply = _connection.net_open( _name );

			if ( reply.status != JSane_Net_Connection.SANE_STATUS_GOOD )
			{
				throw JSane_Exception.getException( reply.status );
			}
			_handle = reply.handle;
			_isOpen = true;
		}
		catch (IOException e)
		{
			throw new JSane_Exception_IoError( e.getMessage() );
		}
	}

	/* 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public void _sendElement( JSane_Wire wire ) throws IOException
	{
		_name._sendElement( wire );
		_vendor._sendElement( wire );
		_model._sendElement( wire );
		_type._sendElement( wire );
	}

	/*
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public void _getElement( JSane_Wire wire ) throws IOException
	{
		_name._getElement( wire );
		_vendor._getElement( wire );
		_model._getElement( wire );
		_type._getElement( wire );
	}

	/* 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public JSane_Net_Transport _getNewElement( JSane_Wire wire )
		throws IOException
	{
		JSane_Net_Device dev = new JSane_Net_Device( _connection );
		dev._getElement( wire );
		return dev;
	}

	/*
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public String toString()
	{
		return _vendor + " " + _model;
	}

	/*
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public JSane_Base_Parameters getParameters() throws JSane_Exception
	{
		try
		{
			JSane_Net_Connection.Get_Parameters_Reply reply = _connection
				.net_get_parameters( _handle );
			if ( reply.status != JSane_Net_Connection.SANE_STATUS_GOOD )
			{
				throw JSane_Exception.getException( reply.status );
			}
			return reply.params;
		}
		catch (IOException e)
		{
			throw new JSane_Exception_IoError( e.getMessage() );
		}
	}

	/*
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public JSane_Base_Frame getFrame() throws JSane_Exception
	{
		JSane_Base_Frame frame = new JSane_Net_Frame( this );

		return frame;
	}

	/** 
	 * 
	 *Retrive the name of this device.
	 *@return the name of the device.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public String getName()
	{
		return _name.toString();
	}

	public JSane_Base_Connection.Start_Reply start( JSane_Base_Type_Word handle )
		throws JSane_Exception
	{
		try
		{
			return _connection.net_start( handle );
		}
		catch (IOException e)
		{
			throw new JSane_Exception_IoError( e.getMessage() );
		}
	}

	public String getHostname()
	{
		return _connection.getHostname();
	}

	public JSane_Net_Connection.Control_option_reply net_control_option(
		int option , int action , JSane_Base_Type value ) throws JSane_Exception
	{
		try
		{
			return _connection.net_control_option( _handle , option , action , value );
		}
		catch (IOException e)
		{
			throw new JSane_Exception_IoError( e.getMessage() );
		}
	}

	/**
	 * @param word
	 * @return
	 * @throws IOException
	 */
	public void cancel(  ) throws IOException
	{
		 _connection.net_cancel(this._getHandle()); 
	}
}