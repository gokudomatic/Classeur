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
 * @author Andi McLean Wrapper for the bool type
 */
public class JSane_Base_Type_Bool extends JSane_Base_Type
{
	protected boolean _value;

	/**
	 *  
	 */
	public JSane_Base_Type_Bool()
	{
		super();
	}

	public JSane_Base_Type_Bool( int value )
	{
		super();
		setBool( value == 1 );
	}

	/**
	 * @param b
	 */
	public JSane_Base_Type_Bool( boolean b )
	{
		super();
		setBool( b );
	}

	/**
	 * Returns the current value as a boolean.
	 * 
	 * @return the current value.
	 */
	/*
	 * date author reason 09/Dec/03 am Initial version.
	 */
	public boolean getBool()
	{
		return _value;
	}

	/**
	 * Sets the current value.
	 * 
	 * @param value
	 *          the new value.
	 */
	/*
	 * date author reason 09/Dec/03 am Initial version.
	 */
	public void setBool( boolean value )
	{
		_value = value;
	}

	/*
	 * date author reason 09/Dec/03 am Initial version.
	 */
	public String toString()
	{
		if ( _value )
			return "True";

		return "False";
	}

	public int getType()
	{
		return SANE_TYPE_BOOL;
	}

	public int getSize()
	{
		return 4;
	}

	public void setValue( String val )
	{
		val.toLowerCase();
		if ( "true".equals( val ) )
		{
			_value = true;
		}
		else
		{
			_value = false;
		}
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
		if ( other instanceof JSane_Base_Type_Bool )
		{
			return ((JSane_Base_Type_Bool) other)._value == _value;
		}
		return false;
	}
}