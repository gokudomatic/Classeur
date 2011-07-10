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

import uk.org.jsane.JSane_Base.JSane_Base_Type_Bool;

/**
 * 
 * @author Andi McLean
 *
 * A basic Sane type used so it can be encoded decoded over a wire.
 */
public class JSane_Net_Type_Bool extends JSane_Base_Type_Bool implements JSane_Net_Transport
{
	/**
	 * Constructor 
	 */
	public JSane_Net_Type_Bool()
	{
		super();
	}

	/**
	 * Constructor. Create taken value from saned.
	 * @param wire The wire that we use to talk to saned.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public JSane_Net_Type_Bool(JSane_Wire wire)  throws IOException
	{
		super();
		_getElement(wire);
	}

	/**
	 * Inherited  method from JSane_Net_Transport which sends the object to saned.
	 * 
	 * @param wire		The wire to send the value on.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public void _sendElement(JSane_Wire wire) throws IOException
	{
		if (_value)
		{
			wire.sendWord(1);
		}
		else
		{
			wire.sendWord(0);
		}
	}

	/*
	 * Inherited  method from JSane_Net_Transport which sends the object to saned.
	 * Set the current value from the wire.
	 * @param wire The wire to get the value from.
	 *  
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public void _getElement(JSane_Wire wire)  throws IOException
	{
		int val = wire.getWord();
		if (val == 0)
		{
			_value = false;
		}
		else
		{
			_value = true;
		}
	}
	
	/**
	 * Inherited  method from JSane_Net_Transport which sends the object to saned.
	 * Given a wire return a new instance of Sane_Bool with the value from the wire.
	 * 
	 * @param wire the wire to take the value from.
	 * @return A new instance of Sane_Bool with the value taken from the wire.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public JSane_Net_Transport _getNewElement(JSane_Wire wire) throws IOException
	{
		return new JSane_Net_Type_Bool(wire);
	}

	/**
	 * Inherited  method from JSane_Net_Transport which sends the object to saned.
	 */
	public void _setSize(int value)
	{
	}
}
