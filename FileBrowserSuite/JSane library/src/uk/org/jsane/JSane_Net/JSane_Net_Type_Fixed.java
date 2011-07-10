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

import uk.org.jsane.JSane_Base.JSane_Base_Type_Fixed;

/**
 * @author Andi McLean
 * One of the basic types.
 * 
 * date				author			reason
 * 09/Dec/03	am				Initial version.
 */
public class JSane_Net_Type_Fixed
	extends JSane_Base_Type_Fixed
	implements JSane_Net_Transport
{

	/**
	 * Constructor
	 */
	public JSane_Net_Type_Fixed()
	{
		super();
	}

	/**
	 *  Constructor which takes an int in fixed point notation.
	 * @param val
	 */
	public JSane_Net_Type_Fixed(int val)
	{
		super(val);
	}

	public JSane_Net_Type_Fixed(JSane_Wire wire) throws IOException
	{
		super();
		_getElement(wire);
	}

	/**
	 * Constructor which takes a double as it default value.
	 * @param val
	 */
	public JSane_Net_Type_Fixed(double val)
	{
		super(val);
	}

	/* (non-Javadoc)
	 * @see uk.org.jsane.Sane_Object#sendElement(uk.org.jsane.JSane_Wire)
	 */
	public void _sendElement(JSane_Wire wire)  throws IOException
	{
		wire.sendWord(_value);
	}

	/* (non-Javadoc)
	 * @see uk.org.jsane.Sane_Object#getElement(uk.org.jsane.JSane_Wire)
	 */
	public void _getElement(JSane_Wire wire) throws IOException
	{
		_value = wire.getWord();
	}

	public JSane_Net_Transport _getNewElement(JSane_Wire wire) throws IOException
	{
		return new JSane_Net_Type_Fixed(wire.getWord());
	}
}
