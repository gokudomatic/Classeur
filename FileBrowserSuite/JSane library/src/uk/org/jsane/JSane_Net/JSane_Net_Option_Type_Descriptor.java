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

package uk.org.jsane.JSane_Net;

import java.io.IOException;

import uk.org.jsane.JSane_Base.JSane_Base_Constraint;
import uk.org.jsane.JSane_Base.JSane_Base_Device;
import uk.org.jsane.JSane_Base.JSane_Base_Option_Type_Descriptor;
import uk.org.jsane.JSane_Base.JSane_Base_Type;
import uk.org.jsane.JSane_Base.JSane_Base_Type_Unit;
import uk.org.jsane.JSane_Exceptions.JSane_Exception;
import uk.org.jsane.JSane_Gui.Interfaces.JSane_Widget_Wrapper_Interface;
import uk.org.jsane.JSane_Gui.Swing.JSane_Swing_Widget_Factory;

/**
 * @author Andi McLean
 * Describes an option that the scanner / backend supports.
 */
public class JSane_Net_Option_Type_Descriptor extends
		JSane_Base_Option_Type_Descriptor implements JSane_Net_Transport
{
	/**
	 * Constructor
	 */
	/*
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public JSane_Net_Option_Type_Descriptor( JSane_Base_Device device )
	{
		super( device );
	}

	/* date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public void _sendElement( JSane_Wire wire )
	{
		// Current only need by saned Write 
	}

	/*
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public void _getElement( JSane_Wire wire ) throws IOException
	{
		_name = new JSane_Net_Type_String( wire );
		_title = new JSane_Net_Type_String( wire );
		_desc = new JSane_Net_Type_String( wire );
		_type = wire.getWord();

		_unit = new JSane_Net_Type_Unit( wire );

		_size = wire.getWord();

		//System.out.println("Type = " + _type + " Size = " + _size);

		_value = JSane_Net_Type.getNewType( _type , _size );

		_cap = wire.getWord();

		int ty = wire.getWord();
		_con = JSane_Net_Constraint.getNewConstraint( ty , _type );

		if ( ty == JSane_Base_Constraint.SANE_CONSTRAINT_NONE )
		{
			return;
		}
		if ( _type == JSane_Base_Type.SANE_TYPE_BOOL
				&& ty == JSane_Base_Constraint.SANE_CONSTRAINT_RANGE )
		{
			return;
		}

		( ( JSane_Net_Transport ) _con )._getElement( wire );

	}

	public JSane_Net_Transport _getNewElement( JSane_Wire wire )
			throws IOException
	{
		JSane_Net_Option_Type_Descriptor des = new JSane_Net_Option_Type_Descriptor(
				_device );
		des._getElement( wire );
		return des;
	}

	/**
	 * Set the value of this option as used by the backend.
	 * @param val
	 */
	/*
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public void setValue( String val ) throws JSane_Exception
	{
		if ( isSettable() )
		{
			_value.setValue( val );
			JSane_Net_Connection.Control_option_reply reply = ( ( JSane_Net_Device ) _device )
					.net_control_option( _option , SANE_ACTION_SET_VALUE ,
							_value );
			if ( reply.status != JSane_Net_Connection.SANE_STATUS_GOOD )
			{
				throw JSane_Exception.getException( reply.status );
			}
			_value = reply.value;
		}
	}

	/**
	 * Get the current value of this option that's currently being used by the backend.
	 * @return The current value.
	 */
	/*
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public JSane_Base_Type getValue() throws JSane_Exception
	{
		if ( isActive() )
		{
			JSane_Net_Connection.Control_option_reply reply = ( ( JSane_Net_Device ) _device )
					.net_control_option( _option , SANE_ACTION_GET_VALUE ,
							_value );
			if ( reply.status != JSane_Net_Connection.SANE_STATUS_GOOD )
			{
				throw JSane_Exception.getException( reply.status );
			}
			return reply.value;
		}
		return null;
	}

	/*
	 Overridden method
	 */
	public JSane_Widget_Wrapper_Interface getWidget(
			JSane_Swing_Widget_Factory factory )
	{
		return _value.getWidget( factory , _con );
	}

	public JSane_Base_Type getValueType() throws JSane_Exception
	{
		return _value;
	}
	
	/*
	 Overridden method
	 */
	public void setValue( Object value ) throws JSane_Exception
	{
		if ( value == null )
		{
			return;
		}

		setValue( value.toString() );
	}
}