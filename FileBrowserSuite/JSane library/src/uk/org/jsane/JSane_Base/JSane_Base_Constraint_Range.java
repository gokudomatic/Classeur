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
 * Used by Sane_Options, Means the option has a range of constraints.
 * @author Andi McLean
 *
 */
public class JSane_Base_Constraint_Range extends JSane_Base_Constraint
{
	protected JSane_Base_Type_Word _min = new JSane_Base_Type_Word();
	protected JSane_Base_Type_Word _max = new JSane_Base_Type_Word();
	protected JSane_Base_Type_Word _quant = new JSane_Base_Type_Word();

	protected int _type = 0;
	/**
	 * Constructor
	 */
	public JSane_Base_Constraint_Range()
	{
		super();
	}

	/**
	 * Constructor with the given type.
	 * @param type can be JSane_Net_Type.SANE_TYPE_FIXED or JSane_Net_Type.SANE_TYPE_Word
	 */
	public JSane_Base_Constraint_Range(int type)
	{
		super();
		_type = type;
	}

	/**
	 * Inherited method from Object. To convert this object into a string
	 * used in debugging.
	 * @return String repersenting this object.
	 * 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public String toString()
	{
		return "Range - min = " + _min + " max = " + _max + "quant = " + _quant;
	}

	/**
	 * 
	 * @return The minimum value, could be a Fixed point decimal or an int
	 */
	public JSane_Base_Type_Word getMin()
	{
		return _min;
	}

	/**
	 * @return The maximum value, could be a fixed point decimal or an int.
	 */
	public JSane_Base_Type_Word getMax()
	{
		return _max;
	}

	/**
	 * @return The quant value, could be a fixed point decimal or an int.
	 */
	public JSane_Base_Type_Word getQuant()
	{
		return _quant;
	}

	/*
		Overridden method
	*/
	public JSane_Widget_Wrapper_Interface getWidget( JSane_Widget_Factory factory )
	{
		return factory.getWidget(this);
	}
}
