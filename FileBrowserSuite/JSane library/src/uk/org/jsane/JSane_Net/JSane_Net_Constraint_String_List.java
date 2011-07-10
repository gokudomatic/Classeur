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

import uk.org.jsane.JSane_Base.JSane_Base_Constraint_String_List;

/**
 * @author Andi McLean
 */
public class JSane_Net_Constraint_String_List extends JSane_Base_Constraint_String_List implements JSane_Net_Transport
{
	/**
	 * Constructor.
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public JSane_Net_Constraint_String_List()
	{
		super();
	}
	/**
	 * Constructor that takes a wire to get it's current value.
	 * @param wire the wire to get the current value from.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public JSane_Net_Constraint_String_List(JSane_Wire wire)  throws IOException
	{
		super();
		_getElement(wire);
	}

	/*
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public void _sendElement(JSane_Wire wire)  throws IOException
	{
		// We don't send this type to saned
	}

	/* 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public void _getElement(JSane_Wire wire)  throws IOException
	{
		int size = wire.getWord();
		
		for(int loop = 0 ; loop < size; ++loop)
		{
			JSane_Net_Type_String str = new JSane_Net_Type_String(wire);
			_vec.add(str);
		}
	}

	/* 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public JSane_Net_Transport _getNewElement(JSane_Wire wire)  throws IOException
	{
		return new JSane_Net_Constraint_String_List(wire);
	}
}
