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

import uk.org.jsane.JSane_Gui.Interfaces.JSane_Widget_Wrapper_Interface;
import uk.org.jsane.JSane_Gui.Swing.JSane_Swing_Widget_Factory;

/**
 * @author panda
 *
 */
public class JSane_Base_Vector_Type extends JSane_Base_Type
{

	protected JSane_Base_Type _type;
	protected int _size;
	protected Vector _values = new Vector();

	/**
	 * @param size
	 * @param type
	 * 
	 */
	public JSane_Base_Vector_Type(JSane_Base_Type type, int size)
	{
		super();
		_type = type;
		_size = size / _type.getSize();
	}

	/* (non-Javadoc)
	 * @see uk.org.jsane.JSane_Base.JSane_Base_Type#getSize()
	 */
	public int getSize()
	{
		return _size * _type.getSize();
	}

	/* (non-Javadoc)
	 * @see uk.org.jsane.JSane_Base.JSane_Base_Type#getType()
	 */
	public int getType()
	{
		return _type.getType();
	}

	/* (non-Javadoc)
	 * @see uk.org.jsane.JSane_Base.JSane_Base_Type#setValue(java.lang.String)
	 */
	public void setValue( String val )
	{
	}

	public void setValue( String [] val )
	{
		for(int loop = 0; loop < val.length; ++loop)
		{
			((JSane_Base_Type) _values.elementAt(loop)).setValue(val[loop]);
		}
	}
	
	/* (non-Javadoc)
	 * @see uk.org.jsane.JSane_Base.JSane_Base_Type#getWidget(uk.org.jsane.JSane_Gui.Swing.JSane_Swing_Widget_Factory, uk.org.jsane.JSane_Base.JSane_Base_Constraint)
	 */
	public JSane_Widget_Wrapper_Interface getWidget(
			JSane_Swing_Widget_Factory factory ,
			JSane_Base_Constraint constraint )
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see uk.org.jsane.JSane_Base.JSane_Base_Type#getNumberItems()
	 */
	public int getNumberItems()
	{
		return _size;
	}

}
