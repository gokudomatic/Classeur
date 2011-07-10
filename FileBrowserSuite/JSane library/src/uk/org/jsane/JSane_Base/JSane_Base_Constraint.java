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
 * @author Andi McLean
 *
 * Base Class for JSane_Base_Constraint_* types
 * Defines the enum values that repersent the object to the sane api's
 */

public abstract class JSane_Base_Constraint
{
	/** Constraint type None */
	public static final int SANE_CONSTRAINT_NONE = 0;
	/** Value has to be with in a certain range (Fixed or Int)*/
	public static final int SANE_CONSTRAINT_RANGE = 1;
	/** Value can be one from a list of integers */
	public static final int SANE_CONSTRAINT_WORD_LIST = 2;
	/** Value can be one from a list of strings */
	public static final int SANE_CONSTRAINT_STRING_LIST = 3;
	
	/**
	 * 
	 */
	public JSane_Base_Constraint()
	{
		super();
	}
	
	public abstract JSane_Widget_Wrapper_Interface getWidget(JSane_Widget_Factory factory);  
}
