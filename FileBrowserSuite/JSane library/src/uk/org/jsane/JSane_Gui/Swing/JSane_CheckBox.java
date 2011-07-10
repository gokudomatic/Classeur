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

package uk.org.jsane.JSane_Gui.Swing;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JCheckBox;

import uk.org.jsane.JSane_Base.JSane_Base_Type_Bool;
import uk.org.jsane.JSane_Gui.Interfaces.JSane_Widget_Wrapper_Interface;

/**
 * @author Andi McLean
 *  
 */
public class JSane_CheckBox extends JCheckBox implements
	JSane_Widget_Wrapper_Interface
{

	/**
	 *  
	 */
	public JSane_CheckBox()
	{
		super();

	}

	/**
	 * @param text
	 */
	public JSane_CheckBox( String text )
	{
		super( text );

	}

	/**
	 * @param text
	 * @param selected
	 */
	public JSane_CheckBox( String text , boolean selected )
	{
		super( text , selected );

	}

	/**
	 * @param a
	 */
	public JSane_CheckBox( Action a )
	{
		super( a );

	}

	/**
	 * @param icon
	 */
	public JSane_CheckBox( Icon icon )
	{
		super( icon );

	}

	/**
	 * @param icon
	 * @param selected
	 */
	public JSane_CheckBox( Icon icon , boolean selected )
	{
		super( icon , selected );

	}

	/**
	 * @param text
	 * @param icon
	 */
	public JSane_CheckBox( String text , Icon icon )
	{
		super( text , icon );

	}

	/**
	 * @param text
	 * @param icon
	 * @param selected
	 */
	public JSane_CheckBox( String text , Icon icon , boolean selected )
	{
		super( text , icon , selected );

	}

	private JSane_Base_Type_Bool _bool = null;

	/*
	 * Overridden method
	 */
	public void setWidgetValue( Object value )
	{
		_bool = null;
		if ( value instanceof JSane_Base_Type_Bool )
		{
			_bool = (JSane_Base_Type_Bool) value;
			setSelected( _bool.getBool() );
		}
	}

	/*
	 * Overridden method
	 */
	public Object getWidgetValue()
	{
		if ( _bool == null )
			return null;

		_bool.setBool( isSelected() );
		return _bool;
	}

	/*
	 * Overridden method
	 */
	public void setWidgetOptions( Object value )
	{
	}

	/* (non-Javadoc)
	 * @see uk.org.jsane.JSane_Gui.Interfaces.JSane_Widget_Wrapper_Interface#hasValueChanged()
	 */
	public boolean hasValueChanged()
	{
		if (_bool != null) {
			return (_bool.getBool()  != isSelected());
		}
		return false;
	}

}