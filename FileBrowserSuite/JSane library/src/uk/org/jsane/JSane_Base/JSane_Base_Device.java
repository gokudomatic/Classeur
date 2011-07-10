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

import uk.org.jsane.JSane_Exceptions.JSane_Exception;

/**
 * 
 * @author Andi McLean
 */
public abstract class JSane_Base_Device
{
	/**
	 * Opens the device so it can be used, to obtain Options, Scan parameters and Start a scan.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public abstract void open() throws JSane_Exception;
	/**
	 * Closes the device.
	 *
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public abstract void close() throws JSane_Exception;
	/**
	 * Given a index of the option returns that option.
	 * @param pos The index of the option to return.
	 * @return If invalid index returns null, else returns the Option.
	 */
	/* date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public abstract JSane_Base_Option_Type_Descriptor getOption(int pos) throws JSane_Exception;
	/**
	 * Retrives the number of options a device contains.
	 * @return the number of options.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public abstract int getNumberOptions() throws JSane_Exception;
	/**
	 * Return a named option.
	 * @param name the name of the option to return
	 * @return null if option not known else the option.
	 */
	public abstract JSane_Base_Option_Type_Descriptor getOption(String name) throws JSane_Exception;
	/**
	 * Returns the parameters for the current / page to be scanned.
	 * @return Scan page parameters.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public abstract JSane_Base_Parameters getParameters() throws JSane_Exception;
	
	/**
	 * Perform a scan with current values.
	 * @return The page scanned.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public abstract JSane_Base_Frame getFrame() throws JSane_Exception;
	/** 
	 *Retrive the name of this device.
	 *@return the name of the device.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public abstract String getName();

}