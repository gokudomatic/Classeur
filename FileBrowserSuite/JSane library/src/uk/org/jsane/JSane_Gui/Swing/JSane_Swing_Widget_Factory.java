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

import javax.swing.SpinnerNumberModel;

import uk.org.jsane.JSane_Base.JSane_Base_Constraint;
import uk.org.jsane.JSane_Base.JSane_Base_Constraint_None;
import uk.org.jsane.JSane_Base.JSane_Base_Constraint_Range;
import uk.org.jsane.JSane_Base.JSane_Base_Constraint_String_List;
import uk.org.jsane.JSane_Base.JSane_Base_Constraint_Word_List;
import uk.org.jsane.JSane_Base.JSane_Base_Type_Bool;
import uk.org.jsane.JSane_Base.JSane_Base_Type_Button;
import uk.org.jsane.JSane_Base.JSane_Base_Type_Group;
import uk.org.jsane.JSane_Base.JSane_Base_Type_String;
import uk.org.jsane.JSane_Base.JSane_Base_Type_Word;
import uk.org.jsane.JSane_Gui.Interfaces.JSane_Widget_Factory;
import uk.org.jsane.JSane_Gui.Interfaces.JSane_Widget_Wrapper_Interface;

/**
 * @author Andi McLean
 *  
 */
public class JSane_Swing_Widget_Factory implements JSane_Widget_Factory
{

	/**
	 *  
	 */
	public JSane_Swing_Widget_Factory()
	{
		super();

	}

	/*
	 * Overridden method
	 */
	public JSane_Widget_Wrapper_Interface getWidget(
		JSane_Base_Constraint constraint )
	{
		return constraint.getWidget( this );
	}

	/*
	 * Overridden method
	 */
	public JSane_Widget_Wrapper_Interface getWidget(
		JSane_Base_Constraint_None constraint )
	{
		JSane_Widget_Wrapper_Interface widget = new JSane_Text();
		widget.setWidgetValue( constraint );

		return widget;
	}

	/*
	 * Overridden method
	 */
	public JSane_Widget_Wrapper_Interface getWidget(
		JSane_Base_Constraint_Range constraint )
	{
		JSane_Widget_Wrapper_Interface widget = new JSane_Range();
		widget.setWidgetValue( constraint );

		return widget;
	}

	/*
	 * Overridden method
	 */
	public JSane_Widget_Wrapper_Interface getWidget(
		JSane_Base_Constraint_String_List constraint )
	{
		JSane_Widget_Wrapper_Interface widget = new JSane_Combo();
		widget.setWidgetValue( constraint );

		return widget;
	}

	/*
	 * Overridden method
	 */
	public JSane_Widget_Wrapper_Interface getWidget(
		JSane_Base_Constraint_Word_List constraint )
	{
		JSane_Widget_Wrapper_Interface widget = new JSane_Combo();
		widget.setWidgetValue( constraint );

		return widget;
	}

	/**
	 * @param bool
	 * @return
	 */
	/*
	 * Date		: Dec 29, 2004 
	 * Author	: panda 
	 * Date		:	Author		Reason
	 * Dec 29, 2004		panda		First added Updates comments
	 */
	public JSane_Widget_Wrapper_Interface getWidget( JSane_Base_Type_Bool bool ,
		JSane_Base_Constraint constraint )
	{
		return new JSane_CheckBox();
	}

	/**
	 * @param string
	 * @param constraint
	 * @return
	 */
	/*
	 * Date		: Dec 29, 2004 
	 * Author	: panda 
	 * Date		:	Author		Reason
	 * Dec 29, 2004		panda		First added Updates comments
	 */
	public JSane_Widget_Wrapper_Interface getWidget(
		JSane_Base_Type_String string , JSane_Base_Constraint constraint )
	{
		return constraint.getWidget( this );
	}

	/**
	 * @param word
	 * @return
	 */
	/*
	 * Date		: Dec 29, 2004 
	 * Author	: panda 
	 * Date		:	Author		Reason
	 * Dec 29, 2004		panda		First added Updates comments
	 */
	public JSane_Widget_Wrapper_Interface getWidget( JSane_Base_Type_Word word ,
		JSane_Base_Constraint constraint )
	{
		return constraint.getWidget( this );
	}

	/**
	 * @param button
	 * @return
	 */
	/*
	 * Date		: Dec 29, 2004 
	 * Author	: panda 
	 * Date		:	Author		Reason
	 * Dec 29, 2004		panda		First added Updates comments
	 */
	public JSane_Widget_Wrapper_Interface getWidget(
		JSane_Base_Type_Button button , JSane_Base_Constraint constraint )
	{
		return null;
	}

	/**
	 * @param group
	 * @return
	 */
	/*
	 * Date		: Dec 29, 2004 
	 * Author	: panda 
	 * Date		:	Author		Reason
	 * Dec 29, 2004		panda		First added Updates comments
	 */
	public JSane_Widget_Wrapper_Interface getWidget( JSane_Base_Type_Group group ,
		JSane_Base_Constraint constraint )
	{
		return null;
	}
}