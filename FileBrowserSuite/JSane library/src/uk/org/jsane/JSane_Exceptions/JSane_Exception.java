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

package uk.org.jsane.JSane_Exceptions;

import uk.org.jsane.JSane_Base.JSane_Base_Connection;

/**
 * @author Andi McLean
 *
 */
public class JSane_Exception extends Exception
{
	/**
	 * @param message
	 */
	public JSane_Exception(String message)
	{
		super(message);
	}
	
	public static JSane_Exception getException(int status)
	{
		switch (status)
		{
			case JSane_Base_Connection.SANE_STATUS_GOOD:
				return null;
			case JSane_Base_Connection.SANE_STATUS_UNSUPPORTED:
				return new JSane_Exception_Unsupported();
			case JSane_Base_Connection.SANE_STATUS_CANCELLED:
				return new JSane_Exception_Canceled();
			case JSane_Base_Connection.SANE_STATUS_DEVICE_BUSY:
				return new JSane_Exception_DeviceBusy();
			case JSane_Base_Connection.SANE_STATUS_INVAL:
				return new JSane_Exception_Invalid();
			case JSane_Base_Connection.SANE_STATUS_EOF:
				return new JSane_Exception_Eof();
			case JSane_Base_Connection.SANE_STATUS_JAMMED:
				return new JSane_Exception_Jammed();
			case JSane_Base_Connection.SANE_STATUS_NO_DOCS:
				return new JSane_Exception_NoDocs();
			case JSane_Base_Connection.SANE_STATUS_COVER_OPEN:
				return new JSane_Exception_CoverOpen();
			case JSane_Base_Connection.SANE_STATUS_IO_ERROR:
				return new JSane_Exception_IoError();
			case JSane_Base_Connection.SANE_STATUS_NO_MEM:
				return new JSane_Exception_OutOfMemory();
			case JSane_Base_Connection.SANE_STATUS_ACCESS_DENIED:
				return new JSane_Exception_AccessDenied();
		}
		return new JSane_Exception_Unknown(status);
	}
}
