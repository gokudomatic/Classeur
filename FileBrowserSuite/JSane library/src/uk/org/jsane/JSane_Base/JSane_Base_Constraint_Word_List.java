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

import java.util.Vector;

import uk.org.jsane.JSane_Gui.Interfaces.JSane_Widget_Factory;
import uk.org.jsane.JSane_Gui.Interfaces.JSane_Widget_Wrapper_Interface;

/**
 * Used by Sane_Options, Means the option can be one of a selected list..
 * 
 * @author Andi Mclean
 */
public class JSane_Base_Constraint_Word_List extends JSane_Base_Constraint
{
	protected Vector _vec = new Vector();

	/**
	 * Constructor
	 */
	public JSane_Base_Constraint_Word_List()
	{
		super();
	}

	/**
	 * Inherited method from Object. To convert this object into a string used in
	 * debugging.
	 * 
	 * @return String repersenting this object.
	 */
	/*
	 * date author reason 09/Dec/03 am Initial version.
	 */
	public String toString()
	{
		String str = "";
		for ( int loop = 0; loop < _vec.size(); ++loop )
		{
			str += "\t" + _vec.elementAt( loop ) + "\n";
		}
		return str;
	}

	/**
	 * Gets the JSane_Base_Type_Word at element position if out of range returns
	 * null
	 * 
	 * @param position
	 *          The element number
	 * @return
	 *  
	 */
	public JSane_Base_Type_Word elementAt( int position )
	{
		if ( position > 0 && position < _vec.size() )
		{
			return (JSane_Base_Type_Word) _vec.elementAt( position );
		}
		return null;
	}

	/**
	 * returns the list of possible values.
	 * 
	 * @return
	 */
	public Vector getList()
	{
		return _vec;
	}

	public JSane_Widget_Wrapper_Interface getWidget( JSane_Widget_Factory factory )
	{
		return factory.getWidget( this );

	}
}