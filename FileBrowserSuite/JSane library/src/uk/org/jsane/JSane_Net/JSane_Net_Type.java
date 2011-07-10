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

import uk.org.jsane.JSane_Base.JSane_Base_Type;
/**
 * @author Andi McLean
 */
public abstract class JSane_Net_Type extends  JSane_Base_Type implements JSane_Net_Transport
{
	public static JSane_Base_Type getNewType(int type, int size)
	{
		JSane_Base_Type baseType = null;
		switch (type)
		{
			case SANE_TYPE_BOOL:
				baseType = new JSane_Net_Type_Bool();
				break;
			case SANE_TYPE_INT:
				baseType = new JSane_Net_Type_Word();
				break;
			case SANE_TYPE_FIXED:
				baseType = new JSane_Net_Type_Fixed();
				break;	
			case SANE_TYPE_STRING:
				baseType = new JSane_Net_Type_String();
				size = baseType.getSize();
				break; 
			case SANE_TYPE_BUTTON:
				baseType = new JSane_Net_Type_Button(); // Option can be selected.
				break;
			case SANE_TYPE_GROUP:
				baseType = new JSane_Net_Type_Group(); // Option can be one of a number of options.
				break;
			default: return null;
		}
		
		if (baseType != null && size != baseType.getSize())
		{
			return new JSane_Net_Type_Vector(baseType , size);
		}
		return baseType;
	}
}

