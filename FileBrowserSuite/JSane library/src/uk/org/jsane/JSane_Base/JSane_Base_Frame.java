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

import java.awt.image.BufferedImage;
import java.util.Vector;

import uk.org.jsane.JSane_Exceptions.JSane_Exception;

/**
 * A scanned image from the device.
 * 
 * @author Andi McLean
 */
public abstract class JSane_Base_Frame
{
	protected int status;

	protected Vector _frames = new Vector();

	protected JSane_Base_Parameters _param;

	protected JSane_Base_Connection.Start_Reply _reply;
	
	protected JSane_Image _frameImage = new JSane_Image();

	/**
	 * Converts the received data into a image that can be easily displayed. (Note
	 * currently only supports 256 gray scale.)
	 * 
	 * @return The received data as an Image.
	 */

	public JSane_Base_Frame() throws JSane_Exception
	{
	}

	public BufferedImage getImage() throws JSane_Exception
	{
		if (_frameImage == null)
			return null;
		return _frameImage.getImage();
	}

	/*
	 * date author reason 09/Dec/03 am Initial version.
	 */
	public BufferedImage getImage( boolean invert ) throws JSane_Exception
	{
		return getImage();
	}
}