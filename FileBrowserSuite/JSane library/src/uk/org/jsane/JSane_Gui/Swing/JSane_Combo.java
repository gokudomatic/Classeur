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

import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import uk.org.jsane.JSane_Base.JSane_Base_Constraint_String_List;
import uk.org.jsane.JSane_Base.JSane_Base_Constraint_Word_List;
import uk.org.jsane.JSane_Gui.Interfaces.JSane_Widget_Wrapper_Interface;

/**
 * @author Andi McLean
 *  
 */
public class JSane_Combo extends JComboBox implements
	JSane_Widget_Wrapper_Interface
{

	private Object _origValue;

	/**
	 *  
	 */
	public JSane_Combo()
	{
		super();

	}

	/**
	 * @param items
	 */
	public JSane_Combo( Object[] items )
	{
		super( items );

	}

	/**
	 * @param items
	 */
	public JSane_Combo( Vector items )
	{
		super( items );

	}

	/**
	 * @param aModel
	 */
	public JSane_Combo( ComboBoxModel aModel )
	{
		super( aModel );

	}

	/*
	 * Overridden method
	 */
	public void setWidgetValue( Object value )
	{
		_origValue = value;
		setSelectedItem( value );
	}

	/*
	 * Overridden method
	 */
	public Object getWidgetValue()
	{
		return getSelectedItem();
	}

	/*
	 * Overridden method
	 */
	public void setWidgetOptions( Object value )
	{
		if ( value instanceof JSane_Base_Constraint_String_List )
		{
			JSane_Base_Constraint_String_List list = (JSane_Base_Constraint_String_List) value;
			setModel( new DefaultComboBoxModel( list.getList() ) );
			setSelectedItem( list.elementAt( 0 ) );
			return;
		}
		if ( value instanceof JSane_Base_Constraint_Word_List )
		{
			JSane_Base_Constraint_Word_List list = (JSane_Base_Constraint_Word_List) value;
			setModel( new DefaultComboBoxModel( list.getList() ) );
			setSelectedItem( list.elementAt( 0 ) );
			return;
		}
	}

	/* (non-Javadoc)
	 * @see uk.org.jsane.JSane_Gui.Interfaces.JSane_Widget_Wrapper_Interface#hasValueChanged()
	 */
	public boolean hasValueChanged()
	{
		Object currentValue = getSelectedItem();
		if (currentValue != null)
		{
			return !currentValue.equals(_origValue);
		}
		return false;
	}
}