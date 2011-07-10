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

/**
 * @author Andi McLean
 * Wrapper for a fixed float.
 */
public class JSane_Base_Type_Fixed extends JSane_Base_Type_Word
{
	/**
	 * 
	 */
	public JSane_Base_Type_Fixed()
	{
		super();
	}

	public JSane_Base_Type_Fixed( int val )
	{
		super( val );
	}

	public JSane_Base_Type_Fixed( double val )
	{
		super( (int) (val * (1 << 16)) );
	}

	/**
	 * Return the value as a double
	 * @return Current value.
	 */
	public double getDouble()
	{
		return ((double) _value) / (1 << 16);
	}

	/**
	 * Set the current value
	 * @param val the new value
	 */
	public void setDouble( double val )
	{
		_value = (int) (val * (1 << 16));
	}

	public String toString()
	{
		return "" + getDouble();
	}

	public int getType()
	{
		return SANE_TYPE_FIXED;
	}

	public void setValue( String val )
	{
		setDouble( Double.parseDouble( val ) );
	}

	public Number getNumber()
	{
		return new Double( getDouble() );
	}
}