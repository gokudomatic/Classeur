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

import uk.org.jsane.JSane_Base.JSane_Base_Parameters;

/**
 * @author Andi McLean
 */
public class JSane_Net_Parameters extends JSane_Base_Parameters implements JSane_Net_Transport
{

	/**
	 * 
	 */
	public JSane_Net_Parameters()
	{
		super();
	}
	
	public JSane_Net_Parameters(JSane_Wire wire)  throws IOException
	{
		super();
		_getElement(wire);
	}

	/* (non-Javadoc)
	 * @see uk.org.jsane.Sane_Object#sendElement(uk.org.jsane.JSane_Wire)
	 */
	public void _sendElement(JSane_Wire wire)
	{
		// Shouldn't have to send a frame to saned.

	}

	/* (non-Javadoc)
	 * @see uk.org.jsane.Sane_Object#getElement(uk.org.jsane.JSane_Wire)
	 */
	public void _getElement(JSane_Wire wire)  throws IOException
	{
		format = wire.getWord();
		lastFrame = new JSane_Net_Type_Bool(wire).getBool();
		bytesPerLine = wire.getWord();
		pixelsPerLine = wire.getWord();
		lines = wire.getWord();
		depth = wire.getWord();
	}

	/* (non-Javadoc)
	 * @see uk.org.jsane.Sane_Object#getNewElement(uk.org.jsane.JSane_Wire)
	 */
	public JSane_Net_Transport _getNewElement(JSane_Wire wire)  throws IOException
	{
		return new JSane_Net_Parameters(wire);
	}
}
