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
 * Container for the parameters needed to scan a page. 
 */
public class JSane_Base_Parameters
{
	public final static int FRAME_GRAY = 0;
	public final static int FRAME_RGB = 1;
	public final static int FRAME_RED = 2;
	public final static int FRAME_GREEN = 3;
	public final static int FRAME_BLUE = 4;
	
	public int format;
	public boolean lastFrame = false;
	public int lines;
	public int depth;
	public int pixelsPerLine;
	public int bytesPerLine;


	public String toString()
	{
		return "Parameter : format = " + format + " is last frame =  " + lastFrame + " number of lines" + lines + " depth =" + depth + " Number of pixels = " + pixelsPerLine + " number of bytes per line" + bytesPerLine;
	}

}
