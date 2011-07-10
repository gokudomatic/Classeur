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

import uk.org.jsane.JSane_Gui.Interfaces.JSane_Widget_Factory;
import uk.org.jsane.JSane_Gui.Interfaces.JSane_Widget_Wrapper_Interface;


/**
 * Used by Sane_Options, Means the option has no constraints.
 * @author weirdpanda
 */
public class JSane_Base_Constraint_None extends JSane_Base_Constraint
{

	public JSane_Base_Constraint_None()
	{
		super();
	}
	
	/**
	 * Over ridden method of Object to display this object as a string
	 * used in debugging. 
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public String toString()
	{
		return "";
	}

	/*
		Overridden method
	*/
	public JSane_Widget_Wrapper_Interface getWidget( JSane_Widget_Factory factory )
	{
		return factory.getWidget(this);
	}
}
