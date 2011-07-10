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

import uk.org.jsane.JSane_Base.JSane_Base_Constraint;

/**
 * @author Andi McLean
 */
public abstract class JSane_Net_Constraint extends JSane_Base_Constraint
{
	/**
	 * Return a new instance of JSane_Net_Constraint for given type.
	 * 
	 * @param type
	 *          The type of the new constraint.
	 * @param valueType
	 *          Additional information which could be passed in the constructor of
	 *          the new type.
	 * @return The new instance of the concrete class derived from this class.
	 * 
	 * date author reason 09/Dec/03 am Initial version.
	 */
	public static JSane_Base_Constraint getNewConstraint( int type , int valueType )
	{
		switch (type)
		{
			case SANE_CONSTRAINT_NONE :
				return new JSane_Net_Constraint_None();
			case SANE_CONSTRAINT_RANGE :
				return new JSane_Net_Constraint_Range( valueType );
			case SANE_CONSTRAINT_WORD_LIST :
				return new JSane_Net_Constraint_Word_List();
			case SANE_CONSTRAINT_STRING_LIST :
				return new JSane_Net_Constraint_String_List();
			default :
				break;
		}
		return null;
	}
}