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
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Vector;

import uk.org.jsane.JSane_Base.JSane_Base_Connection;
import uk.org.jsane.JSane_Base.JSane_Base_Device;
import uk.org.jsane.JSane_Base.JSane_Base_Type;
import uk.org.jsane.JSane_Base.JSane_Base_Type_String;
import uk.org.jsane.JSane_Base.JSane_Base_Type_Word;
import uk.org.jsane.JSane_Exceptions.JSane_Exception;

/**
 * 
 * @author Andi McLean
 *
 * The main class that needs to be created, before commucation with saned
 */
public class JSane_Net_Connection extends JSane_Base_Connection
{
	/** Version of the sane net protocol we know about */
	public static int V_MAJOR = 1;

	public static int V_MINOR = 0;

	public static int SANEI_NET_PROTOCOL_VERSION = 3;

	/** Procedure numbers used in the sane net protocol */
	private static int SANE_NET_INIT = 0;

	private static int SANE_NET_GET_DEVICES = 1;

	private static int SANE_NET_OPEN = 2;

	private static int SANE_NET_CLOSE = 3;

	private static int SANE_NET_GET_OPTION_DESCRIPTORS = 4;

	private static int SANE_NET_CONTROL_OPTION = 5;

	private static int SANE_NET_GET_PARAMETERS = 6;

	private static int SANE_NET_START = 7;

	private static int SANE_NET_CANCEL = 8;

	private static int SANE_NET_AUTHORIZE = 9;

	private static int SANE_NET_EXIT = 10;

	/* The current wire to saned */
	protected JSane_Wire _wire = null;

	/* A list of devices that are know to saned */
	protected Get_devices_reply _devices = null;

	private String _hostname; // Used to open a second socket to get the image data.

	/**
	 * Constructor.
	 * @param hostname  The name of the host to connect to.
	 * @param port  The port of the host to connect to.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public JSane_Net_Connection( String hostname , int port ) throws IOException ,
		UnknownHostException
	{
		super();
		_wire = new JSane_Wire_Binary( hostname , port );
		_hostname = hostname;
		net_init();
	}

	/**
	 * Get the number of devices that saned knows about.
	 * 
	 * @return The number of devices.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public int getNumberDevices() throws JSane_Exception , IOException
	{
		if ( _devices == null )
		{
			_devices = net_get_devices();
		}

		if ( _devices.status != SANE_STATUS_GOOD )
		{
			int sta = _devices.status;
			throw JSane_Exception.getException( sta );
		}

		if ( _devicesMap == null )
		{
			_devicesMap = new HashMap();
			for ( int loop = 0; loop < _devices.devices.size(); ++loop )
			{
				if ( _devices.devices.elementAt( loop ) != null )
				{
					String name = ((JSane_Base_Device) _devices.devices.elementAt( loop ))
						.getName()
						.toUpperCase();
					_devicesMap.put( name , _devices.devices.elementAt( loop ) );
				}
			}
		}
		return _devices.devices.size();
	}

	/**
	 * Gets a device saned knows about.
	 * @param pos the index of the device as returned by getDevices
	 * @return null - if pos is out of range. else the device
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public JSane_Base_Device getDevice( int pos ) throws JSane_Exception ,
		IOException
	{
		if ( pos >= 0 && pos < getNumberDevices() )
		{
			return (JSane_Net_Device) _devices.devices.elementAt( pos );
		}
		return null;
	}

	/**
	 * Gets a device given it's name.
	 * @param device The name of the device to open. Not case sensitive.
	 * @return Null if no device was found with given name else the requested device.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public JSane_Base_Device getDevice( String device ) throws JSane_Exception ,
		IOException
	{
		if ( getNumberDevices() > 0 )
		{
			return (JSane_Net_Device) _devicesMap.get( device.toUpperCase() );
		}
		return null;
	}

	/** 
	 * Calls the Init function on saned. This should be the first function called.
	 * @return status The status from the call.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	protected int net_init() throws IOException
	{
		_wire.sendWord( SANE_NET_INIT );
		_wire.sendWord( SANE_VERSION_CODE(
			V_MAJOR , V_MINOR , SANEI_NET_PROTOCOL_VERSION ) );

		_wire.sendString( System.getProperty( "user.name" ) );
		int status = _wire.getWord();

		int version = _wire.getWord();
		return status;
	}

	/**
	 * Retrive the devices from saned.
	 * @return the status and devices from saned.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	protected Get_devices_reply net_get_devices() throws IOException
	{
		Get_devices_reply reply = new Get_devices_reply();
		_wire.sendWord( SANE_NET_GET_DEVICES );
		reply.status = _wire.getWord();

		JSane_Net_Device dev = new JSane_Net_Device( this );
		reply.devices = _wire.getPtrArray( dev );

		return reply;
	}

	/**
	 * Call to the function Net_Open on saned.
	 * Used by {@link} JSane_Net_Device.open()
	 * @param device_name the name of the device to be opened.
	 * @return the data returned from saned.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	protected Net_open_reply net_open( JSane_Net_Type_String device_name )
		throws IOException
	{
		Net_open_reply reply = new Net_open_reply();

		_wire.sendWord( SANE_NET_OPEN );
		device_name._sendElement( _wire );
		reply.status = _wire.getWord();
		reply.handle._getElement( _wire );
		reply.resource._getElement( _wire );

		return reply;
	}

	/**
	 * 
	 * @author weirdpanda
	 *
	 *Copyright 2001, 2002,2003 Andi McLean
	 * 
	 * The data returned from a call to net_open function on saned.
	 * 
	 * status - the status of the call.
	 * handle - the handle associated with the device.
	 * resource - The resource that needs to be authorised. (Not yet used)
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	protected class Net_open_reply
	{
		public int status = 0;

		public JSane_Net_Type_Word handle = new JSane_Net_Type_Word();

		public JSane_Net_Type_String resource = new JSane_Net_Type_String();
	}

	/**
	 * Closes a device on saned. After calling this the handle can no longer be used.
	 * 
	 * @param handle The handle of the device as returned by {@link}net_open.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	protected void net_close( JSane_Net_Type_Word handle ) throws IOException
	{
		_wire.sendWord( SANE_NET_CLOSE );
		handle._sendElement( _wire );
		_wire.getWord();
	}

	/**
	 * Returns the desctiptors of the options from saned.
	 * @param device - The device to get the options for.
	 * @return the data returned from saned.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	protected Vector net_get_option_descriptors( JSane_Net_Device device )
		throws IOException
	{
		_wire.sendWord( SANE_NET_GET_OPTION_DESCRIPTORS );
		device._getHandle()._sendElement( _wire );
		Vector vec = _wire.getPtrArray( new JSane_Net_Option_Type_Descriptor(
			device ) );
		for ( int loop = 0; loop < vec.size(); ++loop )
		{
			if ( vec.elementAt( loop ) != null )
			{
				((JSane_Net_Option_Type_Descriptor) vec.elementAt( loop ))
					.setOptionNumber( loop );
			}
		}
		return vec;
	}

	/**
	 * Sets and gets the option values.
	 * @param handle 	the handle of the device as returned by {@link}net_open
	 * @param option	the number of the option in question. as returned by {@link} net_get_option_descriptors
	 * @param action	the action to be performed (get/set/auto)
	 * @param value	the new value if setting.
	 * @return the updated data of the option.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	protected Control_option_reply net_control_option(
		JSane_Net_Type_Word handle , int option , int action , JSane_Base_Type value )
		throws IOException
	{
		_wire.sendWord( SANE_NET_CONTROL_OPTION );
		handle._sendElement( _wire );
		_wire.sendWord( option );
		_wire.sendWord( action );
		_wire.sendWord( value.getType() );
		_wire.sendWord( value.getSize() );

		if ( value.getType() != 3 )
			_wire.sendWord( value.getNumberItems() );

		((JSane_Net_Transport) value)._sendElement( _wire );

		Control_option_reply reply = new Control_option_reply();

		reply.status = _wire.getWord();
		reply.info.setValue( _wire.getWord() );
		int type = _wire.getWord();
		int size = _wire.getWord();

		JSane_Net_Transport replyValue = (JSane_Net_Transport) JSane_Net_Type
			.getNewType( type  , size);
		if ( value.getType() != 3 )
			_wire.getWord();
		replyValue._getElement( _wire );
		reply.value = (JSane_Base_Type) replyValue;

		reply.resource = new JSane_Base_Type_String( _wire.getString() );
		return reply;
	}

	/**
	 * Get the parameters of the current scan.
	 * @param handle The handle of the device to get the parameters for.
	 * @return the data returned from saned.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	protected Get_Parameters_Reply net_get_parameters( JSane_Net_Type_Word handle )
		throws IOException
	{
		_wire.sendWord( SANE_NET_GET_PARAMETERS );
		handle._sendElement( _wire );
		Get_Parameters_Reply reply = new Get_Parameters_Reply();

		reply.status = _wire.getWord();
		reply.params = new JSane_Net_Parameters( _wire );

		return reply;
	}

	/**
	 * Start a scan on the given device.
	 * @param handle The handle of the device to start the scan on.
	 * @return the data returned from the call.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	protected Start_Reply net_start( JSane_Base_Type_Word handle )
		throws IOException
	{
		_wire.sendWord( SANE_NET_START );
		_wire.sendWord( handle );

		Start_Reply reply = new Start_Reply();

		reply.status = _wire.getWord();
		reply.port = new JSane_Net_Type_Word( _wire );
		reply.byte_order = new JSane_Net_Type_Word( _wire );
		reply.resource = new JSane_Net_Type_String( _wire );

		return reply;
	}

	/**
	 * Cancel the current scan. 
	 * @param handle the handle of the device to stop scanning.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public void net_cancel( JSane_Net_Type_Word handle ) throws IOException
	{
		_wire.sendWord( SANE_NET_CANCEL );
		handle._sendElement( _wire );
		_wire.getWord();
	}

	/**
	 * Authorise a resource
	 * 
	 * @param resource The resource name
	 * @param username The username
	 * @param password The password for the resource / username
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public void net_authorize( JSane_Net_Type_String resource ,
		JSane_Net_Type_String username , JSane_Net_Type_String password )
		throws IOException
	{
		_wire.sendWord( SANE_NET_AUTHORIZE );
		resource._sendElement( _wire );
		username._sendElement( _wire );
		password._sendElement( _wire );
	}

	/**
	 * Last method to be called before dropping connection to saned.
	 * No other functions should be called after this on saned
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	protected void net_exit() throws IOException
	{
		_wire.sendWord( SANE_NET_EXIT );
		// drop connection.
	}

	/**
	 * Get the version code as used by saned.
	 * @param major
	 * @param minor
	 * @param build
	 * @return the version number as used by the sane API
	 */
	public static int SANE_VERSION_CODE( int major , int minor , int build )
	{
		int value = (major & 0xff) << 24;
		value += (minor & 0xff) << 16;
		value += (build & 0xffff) << 0;

		return value;
	}

	/**
	 * Get the hostname of saned we're connected to.
	 * @return The name of the host we're connected to.
	 */
	public String getHostname()
	{
		return _hostname;
	}

	/**
	 * Get the wire we're using to talk to saned
	 * @return
	 */
	protected JSane_Wire _getWire()
	{
		return _wire;
	}

	public void exit() throws IOException
	{
		net_exit();
	}

	/**
	 * @return
	 * @throws IOException
	 * @throws JSane_Exception
	 */
	/*
	 * Date		: Dec 30, 2004 
	 * Author	: panda 
	 * Date		:	Author		Reason
	 * Dec 30, 2004		panda		First added Updates comments
	 */
	public Vector getDevices() throws JSane_Exception , IOException
	{
		Vector vec = new Vector();

		int num = this.getNumberDevices();

		for ( int loop = 0; loop < num; ++loop )
		{
			if ( getDevice( loop ) != null )
			{
				vec.add( this.getDevice( loop ) );
			}
		}

		return vec;
	}
}