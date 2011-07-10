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

import javax.swing.JTextField;
import javax.swing.text.Document;

import uk.org.jsane.JSane_Gui.Interfaces.JSane_Widget_Wrapper_Interface;

/**
 * @author Andi McLean
 *  
 */
public class JSane_Text extends JTextField implements
	JSane_Widget_Wrapper_Interface
{

	private Object _origValue;

	/**
	 *  
	 */
	public JSane_Text()
	{
		super();

	}

	/**
	 * @param columns
	 */
	public JSane_Text( int columns )
	{
		super( columns );

	}

	/**
	 * @param text
	 */
	public JSane_Text( String text )
	{
		super( text );

	}

	/**
	 * @param text
	 * @param columns
	 */
	public JSane_Text( String text , int columns )
	{
		super( text , columns );

	}

	/**
	 * @param doc
	 * @param text
	 * @param columns
	 */
	public JSane_Text( Document doc , String text , int columns )
	{
		super( doc , text , columns );

	}

	/*
	 * Overridden method
	 */
	public void setWidgetValue( Object value )
	{
		if ( value != null )
		{
			setText( value.toString() );
			_origValue = value;
		}
	}

	/*
	 * Overridden method
	 */
	public Object getWidgetValue()
	{
		return getText();
	}

	/*
	 Overridden method
	 */
	public void setWidgetOptions( Object value )
	{
	}

	/* (non-Javadoc)
	 * @see uk.org.jsane.JSane_Gui.Interfaces.JSane_Widget_Wrapper_Interface#hasValueChanged()
	 */
	public boolean hasValueChanged()
	{
		return !getText().equals(_origValue);
	}

}