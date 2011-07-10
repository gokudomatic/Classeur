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
 * @author Andi McLean Wrapper for String
 */
public class JSane_Base_Type_String extends JSane_Base_Type
{
	protected String _str;

	/**
	 *  
	 */
	public JSane_Base_Type_String()
	{
		super();
		_str = "";
	}

	public JSane_Base_Type_String( String str )
	{
		super();
		_str = str;
	}

	public String toString()
	{
		return _str;
	}

	public boolean isEmpty()
	{
		return ("".equals( _str ));
	}

	/**
	 * @return the string.
	 */
	public String getString()
	{
		return _str;
	}

	public int getType()
	{
		return SANE_TYPE_STRING;
	}

	public int getSize()
	{
		return 240;
	}

	public void setValue( String val )
	{
		_str = val;
	}

	/*
	 * Overridden method
	 */
	public JSane_Widget_Wrapper_Interface getWidget(
		JSane_Swing_Widget_Factory factory , JSane_Base_Constraint constraint )
	{
		return factory.getWidget( this , constraint );
	}

	public boolean equals( Object other )
	{
		if ( other instanceof JSane_Base_Type_String )
		{
			return _str.equals( ((JSane_Base_Type_String) other)._str );
		}
		return false;
	}
}