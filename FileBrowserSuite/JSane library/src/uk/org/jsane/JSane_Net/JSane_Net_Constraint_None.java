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



import uk.org.jsane.JSane_Base.JSane_Base_Constraint_None;

/**
 * Used by Sane_Options, Means the option has no constraints.
 * @author Andi McLean
 * 
 */
public class JSane_Net_Constraint_None extends JSane_Base_Constraint_None implements JSane_Net_Transport
{
	/**
	 *  Constructor
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public JSane_Net_Constraint_None()
	{
		super();
	}

	/**
	 * Constructor from a wire.
	 * @param wire the wire to get the default value from.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public JSane_Net_Constraint_None(JSane_Wire wire)
	{
		super();
		_getElement(wire);
	}

	public void _sendElement(JSane_Wire wire)
	{
		// we do not send this to saned
	}

	public void _getElement(JSane_Wire wire)
	{
		// Constraint None does not have any extra parameters
	}

	public JSane_Net_Transport _getNewElement(JSane_Wire wire)
	{
		return new JSane_Net_Constraint_None(wire);
	}
}
