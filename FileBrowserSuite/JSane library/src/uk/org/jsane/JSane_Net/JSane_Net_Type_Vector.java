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

import uk.org.jsane.JSane_Base.JSane_Base_Type;
import uk.org.jsane.JSane_Base.JSane_Base_Vector_Type;

/**
 * @author panda
 *
 */
public class JSane_Net_Type_Vector extends JSane_Base_Vector_Type implements JSane_Net_Transport
{
	/**
	 * @param baseType
	 * @param size
	 */
	public JSane_Net_Type_Vector( JSane_Base_Type type , int size )
	{
		super(type , size);
	}

	/* (non-Javadoc)
	 * @see uk.org.jsane.JSane_Net.JSane_Net_Transport#_sendElement(uk.org.jsane.JSane_Net.JSane_Wire)
	 */
	public void _sendElement( JSane_Wire wire ) throws IOException
	{
		for(int loop = 0; loop < _size; ++loop)
		{
			if (loop < _values.size())
			{
				((JSane_Net_Transport) _values.elementAt(loop))._sendElement(wire);
			}
			else
			{
				((JSane_Net_Transport) _type)._sendElement(wire);
			}
		}
	}

	/* (non-Javadoc)
	 * @see uk.org.jsane.JSane_Net.JSane_Net_Transport#_getElement(uk.org.jsane.JSane_Net.JSane_Wire)
	 */
	public void _getElement( JSane_Wire wire ) throws IOException
	{
		_values.clear();
		JSane_Wire.showInput = true;
		for(int loop = 0; loop < _size; ++loop)
		{
			_values.add(((JSane_Net_Transport) _type)._getNewElement(wire));
		}
		JSane_Wire.showInput = false;
	}

	/* (non-Javadoc)
	 * @see uk.org.jsane.JSane_Net.JSane_Net_Transport#_getNewElement(uk.org.jsane.JSane_Net.JSane_Wire)
	 */
	public JSane_Net_Transport _getNewElement( JSane_Wire wire ) throws IOException
	{
		return null;
	}

}
