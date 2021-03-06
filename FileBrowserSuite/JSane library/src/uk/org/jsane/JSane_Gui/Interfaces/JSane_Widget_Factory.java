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

package uk.org.jsane.JSane_Gui.Interfaces;

import uk.org.jsane.JSane_Base.JSane_Base_Constraint;
import uk.org.jsane.JSane_Base.JSane_Base_Constraint_None;
import uk.org.jsane.JSane_Base.JSane_Base_Constraint_Range;
import uk.org.jsane.JSane_Base.JSane_Base_Constraint_String_List;
import uk.org.jsane.JSane_Base.JSane_Base_Constraint_Word_List;

/**
 * @author Andi McLean
 *
 */
public interface JSane_Widget_Factory
{
	public abstract JSane_Widget_Wrapper_Interface getWidget(JSane_Base_Constraint constraint); 
	public abstract JSane_Widget_Wrapper_Interface getWidget(JSane_Base_Constraint_None constraint); 
	public abstract JSane_Widget_Wrapper_Interface getWidget(JSane_Base_Constraint_Range constraint); 
	public abstract JSane_Widget_Wrapper_Interface getWidget(JSane_Base_Constraint_String_List constraint);
	public abstract JSane_Widget_Wrapper_Interface getWidget(JSane_Base_Constraint_Word_List constraint); 
}
