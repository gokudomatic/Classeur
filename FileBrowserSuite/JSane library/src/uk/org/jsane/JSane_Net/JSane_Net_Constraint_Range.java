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

import uk.org.jsane.JSane_Base.JSane_Base_Constraint_Range;

/**
 * @author Andi McLean
 *
 *	Used by Sane_Option to indicate that the option's value can only be with in a certain range.
 * (Note This will need some extra work, if it is to be used by a Gui.
 * Not currently used).
 * 
 * date				author			reason
 * 09/Dec/03	am				Initial version.
 */
public class JSane_Net_Constraint_Range extends JSane_Base_Constraint_Range implements JSane_Net_Transport
{

	/*
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	/**
	 * Constructor.
	 * 
	 * @param type , the type of the range (Fixed,int) 
	 */
	public JSane_Net_Constraint_Range(int type)
	{
		super(type);		
	}

	/*
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public void _sendElement(JSane_Wire wire)
	{
		// do not need to send this type to saned
	}

	/* 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public void _getElement(JSane_Wire wire)  throws IOException
	{
		wire.getWord();

		if (_type == JSane_Net_Type.SANE_TYPE_FIXED)
		{
			_min = new JSane_Net_Type_Fixed(wire);
			_max = new JSane_Net_Type_Fixed(wire);
			_quant = new JSane_Net_Type_Fixed(wire);
		}
		else
		{
		_min = new JSane_Net_Type_Word(wire);
		_max = new JSane_Net_Type_Word(wire);
		_quant = new JSane_Net_Type_Word(wire);
		}
	}

	/* 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public JSane_Net_Transport _getNewElement(JSane_Wire wire)
	{
		return null;
	}
}
