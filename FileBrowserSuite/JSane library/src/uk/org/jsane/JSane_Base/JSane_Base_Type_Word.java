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
 * @author Andi McLean Wrapper for an integer
 */
public class JSane_Base_Type_Word extends JSane_Base_Type
{
	protected int _value = 0;

	public JSane_Base_Type_Word()
	{
		super();
	}

	public JSane_Base_Type_Word( int value )
	{
		super();
		_value = value;
	}

	public String toString()
	{
		return Integer.toString( _value );
	}

	public int getValue()
	{
		return _value;
	}

	public void setValue( int value )
	{
		_value = value;
	}

	public int getType()
	{
		return SANE_TYPE_INT;
	}

	public int getSize()
	{
		return 4;
	}

	public void setValue( String val )
	{
		_value = Integer.parseInt( val );
	}

	/**
	 * @param quant
	 */
	/*
	 * Date : Dec 29, 2004 Author : panda Date : Author Reason Dec 29, 2004 panda
	 * First added Updates comments
	 */
	public void minus( JSane_Base_Type_Word quant )
	{
		_value -= quant._value;
	}

	/**
	 * @param quant
	 */
	/*
	 * Date : Dec 29, 2004 Author : panda Date : Author Reason Dec 29, 2004 panda
	 * First added Updates comments
	 */
	public void add( JSane_Base_Type_Word quant )
	{
		_value += quant._value;
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
		if ( other instanceof JSane_Base_Type_Word )
		{
			return ((JSane_Base_Type_Word) other)._value == _value;
		}
		return false;
	}

	/**
	 * @return
	 */
	/*
	 * Date		: Jan 7, 2005 
	 * Author	: panda 
	 * Date		:	Author		Reason
	 * Jan 7, 2005		panda		First added Updates comments
	 */
	public Number getNumber()
	{
		return new Integer( _value );
	}

	/**
	 * @param value
	 */
	/*
	 * Date		: Jan 7, 2005 
	 * Author	: panda 
	 * Date		:	Author		Reason
	 * Jan 7, 2005		panda		First added Updates comments
	 */
	public void setNumber( Object value )
	{
		setValue( value.toString() );
	}
}