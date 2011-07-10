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

import uk.org.jsane.JSane_Exceptions.JSane_Exception;
import uk.org.jsane.JSane_Gui.Interfaces.JSane_Widget_Wrapper_Interface;
import uk.org.jsane.JSane_Gui.Swing.JSane_Swing_Widget_Factory;

/**
 * @author Andi McLean
 * This is the option, it details what the scanner can do.
 * Options are used to control virtualy all aspects of device operation.
 */
public abstract class JSane_Base_Option_Type_Descriptor
{
	/** The option value can be set */
	public static final int SANE_CAP_SOFT_SELECT = 1;

	/** The option value is set by performing some function on the scanner itself. (I.E. flip a switch) */
	public static final int SANE_CAP_HARD_SELECT = 2;

	/** The option value can be detected by software, used in conjunction with SANE_CAP_HARD_SELECT*/
	public static final int SANE_CAP_SOFT_DETECT = 4;

	/** The option will be emulated by the backend, as the device does not directly support this option */
	public static final int SANE_CAP_EMULATED = 8;

	/** The backend or scanner can automaticaly pick a correct value */
	public static final int SANE_CAP_AUTOMATIC = 16;

	/** The option is not currently active, usually another option should be selected first */
	public static final int SANE_CAP_INACTIVE = 32;

	/** The option is only suitable for use by experianced users. Eg a GUI should put it in an advance pane */
	public static final int SANE_CAP_ADVANCED = 64;

	protected static final int SANE_ACTION_GET_VALUE = 0;

	protected static final int SANE_ACTION_SET_VALUE = 1;

	protected static final int SANE_ACTION_SET_AUTO = 2;

	/** The unique name of the option */
	protected JSane_Base_Type_String _name = new JSane_Base_Type_String();

	/** The title of the option, a 1 or 2 word whcih can be used by a GUI */
	protected JSane_Base_Type_String _title = new JSane_Base_Type_String();

	/** A more wordy discription of the option */
	protected JSane_Base_Type_String _desc = new JSane_Base_Type_String();

	/** The units that the option is in can be<br>
	 * None<br>
	 * Pixel<br>
	 * Bit<br>
	 * MM<br>
	 * DPI<br>
	 * Percent<br>
	 * Microsecond<br>
	 */
	protected JSane_Base_Type_Unit _unit = new JSane_Base_Type_Unit();

	/** The constraits placed on the option */
	protected JSane_Base_Constraint _con = null;

	/** The current value */
	protected JSane_Base_Type _value = null;

	/** the amount of memory needed to store the values (Used by the c code) */
	protected int _size;

	/** The type of the current value. */
	protected int _type;

	/** The capabilites of the option */
	protected int _cap = 0;

	/** The option number */
	protected int _option;

	/** The device associated with the option */
	protected JSane_Base_Device _device;

	/**
	 * 
	 */
	public JSane_Base_Option_Type_Descriptor()
	{
		super();
		_device = null;
		_option = -1;
	}

	public JSane_Base_Option_Type_Descriptor( JSane_Base_Device device )
	{
		super();
		_device = device;
		_option = -1;
	}

	/**
	 * Get the name of the option.
	 * @return The option's name.
	 */
	/*
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public JSane_Base_Type_String getName()
	{
		return _name;
	}

	/**
	 * Is this option active?
	 * @return True if the option is currently active.
	 */
	/*
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public boolean isActive()
	{
		return (_cap & SANE_CAP_INACTIVE) == 0;
	}

	/**
	 * Can this option be set.
	 * @return True if the option is settable.
	 */
	/*
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public boolean isSettable()
	{
		return (_cap & SANE_CAP_SOFT_SELECT) != 0;
	}

	public void setOptionNumber( int number )
	{
		_option = number;
	}

	/**
	 * Inherited method from Object. To convert this object into a string
	 * used in debugging.
	 * @return String repersenting this object.
	 */
	/* date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public String toString()
	{
		return "Option - " + _option + " " + _name + " " + _desc + "\n" + _con;
	}

	public abstract JSane_Base_Type getValue() throws JSane_Exception;

	public abstract void setValue( String val ) throws JSane_Exception;

	public JSane_Base_Constraint getConstraint()
	{
		return _con;
	}

	/**
	 * @param factory
	 * @return
	 */
	/*
	 * Date		: Dec 29, 2004 
	 * Author	: panda 
	 * Date		:	Author		Reason
	 * Dec 29, 2004		panda		First added Updates comments
	 */
	public abstract JSane_Widget_Wrapper_Interface getWidget(
		JSane_Swing_Widget_Factory factory );

	/**
	 * @param value
	 * @throws JSane_Exception
	 */
	/*
	 * Date		: Dec 30, 2004 
	 * Author	: panda 
	 * Date		:	Author		Reason
	 * Dec 30, 2004		panda		First added Updates comments
	 */
	public abstract void setValue( Object value ) throws JSane_Exception;

	public abstract JSane_Base_Type getValueType() throws JSane_Exception;
	
	/**
	 * @return
	 */
	/*
	 * Date		: Jan 7, 2005 
	 * Author	: panda 
	 * Date		:	Author		Reason
	 * Jan 7, 2005		panda		First added Updates comments
	 */
	public JSane_Base_Type_String getTitle()
	{
		return _title;
	}
}
