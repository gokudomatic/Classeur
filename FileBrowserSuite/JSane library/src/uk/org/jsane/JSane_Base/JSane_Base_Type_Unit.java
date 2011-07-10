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

import uk.org.jsane.JSane_Gui.Interfaces.JSane_Widget_Wrapper_Interface;
import uk.org.jsane.JSane_Gui.Swing.JSane_Swing_Widget_Factory;

/**
 * @author Andi McLean
 */
public class JSane_Base_Type_Unit extends JSane_Base_Type
{
	/**
	 * @param unit
	 */
	public JSane_Base_Type_Unit( int unit )
	{
		_unit = unit;
	}

	public static final int SANE_UNIT_NONE = 0;

	public static final int SANE_UNIT_PIXEL = 1;

	public static final int SANE_UNIT_BIT = 2;

	public static final int SANE_UNIT_MM = 3;

	public static final int SANE_UNIT_DPI = 4;

	public static final int SANE_UNIT_PERCENT = 5;

	public static final int SANE_UNIT_MICROSECOND = 6;

	protected int _unit = 0;

	public JSane_Base_Type_Unit()
	{
		super();
	}

	public int getType()
	{
		return SANE_TYPE_UNDEFINED;
	}

	public int getSize()
	{
		return 4;
	}

	public void setValue( String val )
	{
		// Can not set a unit
	}

	/*
	 * Overridden method
	 */
	public JSane_Widget_Wrapper_Interface getWidget(
		JSane_Swing_Widget_Factory factory , JSane_Base_Constraint constraint )
	{
		return null;
	}

	public boolean equals( Object other )
	{
		if ( other instanceof JSane_Base_Type_Unit )
		{
			return ((JSane_Base_Type_Unit) other)._unit == _unit;
		}
		return false;
	}
}