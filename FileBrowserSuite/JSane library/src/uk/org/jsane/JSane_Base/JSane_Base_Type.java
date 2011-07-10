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
 * Base class for the type wrappers.
 */
public abstract class JSane_Base_Type
{
	public static final int SANE_TYPE_UNDEFINED = -1;
	public static final int SANE_TYPE_BOOL = 0;
	public static final int SANE_TYPE_INT = 1;
	public static final int SANE_TYPE_FIXED = 2;
	public static final int SANE_TYPE_STRING = 3;
	public static final int SANE_TYPE_BUTTON = 4;
	public static final int SANE_TYPE_GROUP =5 ;
	
	public JSane_Base_Type()
	{
		super();
	}

	public abstract int getSize();
	public abstract int getType();

	/**
	 * @param val
	 */
	public abstract void setValue(String val);

	/**
	 * @param factory
	 * @param constraint
	 * @return
	 */
	/*
	 * Date		: Dec 29, 2004 
	 * Author	: panda 
	 * Date		:	Author		Reason
	 * Dec 29, 2004		panda		First added Updates comments
	 */
	public abstract JSane_Widget_Wrapper_Interface getWidget(
		JSane_Swing_Widget_Factory factory , JSane_Base_Constraint constraint );

	/**
	 * @return
	 */
	public int getNumberItems()
	{
		return 1;
	}
}
