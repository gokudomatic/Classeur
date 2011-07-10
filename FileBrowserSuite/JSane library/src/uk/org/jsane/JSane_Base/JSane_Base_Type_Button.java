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
 * @author weirdpanda
 * Wrapper for an option button type
 * Currently this class contains just enough code to compile it does not do
 * anything useful. This class will be needed if a Gui frontend is being developed.
 */
public abstract class JSane_Base_Type_Button extends JSane_Base_Type
{
	public JSane_Base_Type_Button()
	{
		super();
	}

	public void setValue( String val ) // Button does not have a value.
	{
	}

	public JSane_Base_Type getValue()
	{
		return null;
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