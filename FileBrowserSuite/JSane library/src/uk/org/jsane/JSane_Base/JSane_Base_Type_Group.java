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
 * Wrapper for group  type for an option
 * (Note this is a bare minimum class at the moment. If you wish to develop a gui you may need
 * to add some more flesh to this class.
 * Only title and type are valid in the option desciptor for this type.
 */
public abstract class JSane_Base_Type_Group extends JSane_Base_Type
{

	/**
	 * 
	 */
	public JSane_Base_Type_Group()
	{
		super();
	}

	public int getType()
	{
		return SANE_TYPE_GROUP;
	}

	public JSane_Base_Type getValue()
	{
		return null;
	}

	public int getSize()
	{
		return 4;
	}

	/*
	 * Overridden method
	 */
	public JSane_Widget_Wrapper_Interface getWidget(
		JSane_Swing_Widget_Factory factory , JSane_Base_Constraint constraint )
	{
		return factory.getWidget( this , constraint );
	}
}

