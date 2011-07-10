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

/**
 * @author Andi McLean
 */
public interface JSane_Net_Transport
{
	/**
	 * Send the current value to saned.
	 * @param wire
	 */
	public abstract void _sendElement(JSane_Wire wire)  throws IOException;
	
	/**
	 * Get the current value from saned
	 * @param wire
	 */
	public abstract void _getElement(JSane_Wire wire)  throws IOException;
	
	/**
	 * Create a new element with their current value from saned.
	 * @param wire
	 * @return A new instance of the current class, with the current value taken from the wire.
	 */
	public abstract JSane_Net_Transport _getNewElement(JSane_Wire wire)  throws IOException;
}