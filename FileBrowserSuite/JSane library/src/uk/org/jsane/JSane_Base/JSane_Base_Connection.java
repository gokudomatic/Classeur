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

package uk.org.jsane.JSane_Base;


import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import uk.org.jsane.JSane_Exceptions.JSane_Exception;
import uk.org.jsane.JSane_Exceptions.JSane_Exception_AccessDenied;

/**
 * Used to make a connection to sane.
 * Anybody using these classes should create an instance of a dervied class of this type,
 * before any other calls could be made.
 * @author Andi McLean
 * 
 */
public abstract class JSane_Base_Connection
{
	
	/** Return codes Everything is ok*/
	public static final  int SANE_STATUS_GOOD = 0;
	
	/** Return code operation is not supported */
	public static final int SANE_STATUS_UNSUPPORTED = 1;
	
	/** Return code operation was cancelled */
	public static final int SANE_STATUS_CANCELLED = 2;
	
	/** Return code device is busy; try again later */
	public static final int SANE_STATUS_DEVICE_BUSY = 3;
	
	/** Return code data is invalid (includes no dev at open) */	
	public static final int SANE_STATUS_INVAL = 4;	
	
	/** Return code no more data available (end-of-file) */	
	public static final int SANE_STATUS_EOF = 5;		
	
	/** Return code document feeder jammed */
	public static final int SANE_STATUS_JAMMED = 6;		
	
	/** Return code document feeder out of documents */
	public static final int SANE_STATUS_NO_DOCS = 7;	
	
	/** Return code scanner cover is open */
	public static final int SANE_STATUS_COVER_OPEN = 8;
	
	/** Return code error during device I/O */	
	public static final int SANE_STATUS_IO_ERROR = 9;	
	
	/** Return code out of memory */
	public static final int SANE_STATUS_NO_MEM = 10;
	
	/** Return code Access denied */		
	public static final int SANE_STATUS_ACCESS_DENIED = 11;
		
	/* used to hold the devices returned by saned */
	public HashMap _devicesMap = null;

	/**
	 * Get the number of devices that saned knows about.
	 * 
	 * @return The number of devices.
	 * @throws 	JSane_Exception_AccessDenied,
	 * 				JSane_Exception_Canceled
	 * 				JSane_Exception_CoverOpen
	 * 				JSane_Exception_DeviceBusy
	 * 				JSane_Exception_Eof
	 * 				JSane_Exception_Invalid
	 * 				JSane_Exception_IoError
	 * 				JSane_Exception_Jammed
	 * 				JSane_Exception_No_Docs
	 * 				JSane_Exception_OutOfMemory
	 * 				JSane_Exception_Unsupported
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */	
	public abstract int getNumberDevices()  throws JSane_Exception  , IOException;
	
	/**
	 * Gets a device saned knows about.
	 * @param pos the index of the device as returned by getDevices
	 * @return  the device
	 * @throws 	JSane_Exception_AccessDenied,
	 * 				JSane_Exception_Canceled
	 * 				JSane_Exception_CoverOpen
	 * 				JSane_Exception_DeviceBusy
	 * 				JSane_Exception_Eof
	 * 				JSane_Exception_Invalid
	 * 				JSane_Exception_IoError
	 * 				JSane_Exception_Jammed
	 * 				JSane_Exception_No_Docs
	 * 				JSane_Exception_OutOfMemory
	 * 				JSane_Exception_Unsupported
	 */
	/* 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */	
	public abstract JSane_Base_Device getDevice(int pos) throws JSane_Exception ,  IOException;
	
	/**
	 * Gets a device given it's name.
	 * @param device The name of the device to open. Not case sensitive.
	 * @return the requested device.
	 * @throws 	JSane_Exception_AccessDenied,
	 * 				JSane_Exception_Canceled
	 * 				JSane_Exception_CoverOpen
	 * 				JSane_Exception_DeviceBusy
	 * 				JSane_Exception_Eof
	 * 				JSane_Exception_Invalid
	 * 				JSane_Exception_IoError
	 * 				JSane_Exception_Jammed
	 * 				JSane_Exception_No_Docs
	 * 				JSane_Exception_OutOfMemory
	 * 				JSane_Exception_Unsupported
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public abstract JSane_Base_Device getDevice(String device)  throws JSane_Exception  , IOException;

	/**
	 * 
	 * @author weirdpanda
	 * 
	 * status - the status of the call.
	 * info 
	 * value - the new value of the option as adjusted by the backend.
	 * resource the resource that need to be authorised (Not currently used)
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */	
	public class Control_option_reply
	{
		public int status = 0;
		public JSane_Base_Type_Word info = new JSane_Base_Type_Word();
		public JSane_Base_Type value = null;
		public JSane_Base_Type_String resource = new JSane_Base_Type_String();
	}

	/**
	 * 
	 * @author weirdpanda
	 *
	 * the returned data from a call to init
	 * status - the status of the call.
	 * devices - a vector containg the devices that saned knows about.
	 */
	public class Get_devices_reply
	{
		public int status = 0;
		public Vector devices = null;
	}
	
	/**
	 * 
	 * @author weirdpanda
	 *
	 * The data returned from a call to get_parameters
	 */
	/*
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */	
	public class Get_Parameters_Reply
	{
		public int status = 0;
		public JSane_Base_Parameters params = new JSane_Base_Parameters();
	}

	/**
	 * 
	 * @author weirdpanda
	 *
	 * The reply from a call to net_start
	 *
	 * status - the status of the call.
	 * port - the port that saned is listening on to send the scan data.
	 * byte_order - the byte order of the host that is scanning the document.
	 * resource - the resource that needs to be authorised. (Not currently used)
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */	
	public class Start_Reply
	{
		public int status = 0;
		public JSane_Base_Type_Word port = new JSane_Base_Type_Word();
		public JSane_Base_Type_Word byte_order = new JSane_Base_Type_Word();
		public JSane_Base_Type_String resource = new JSane_Base_Type_String();
	}
	
	public abstract void exit() throws IOException;	
}