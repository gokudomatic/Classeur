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

/**
 * @author Andi McLean
 *
 */
public class JSane_Image
{
	private static final int BLUE_OFFSET = 0;

	private static final int GREEN_OFFSET = 8;

	private static final int RED_OFFSET = 16;

	private JSane_Base_Parameters _param;

	private int _x = 0;

	private int _y = 0;

	private int _colourIndex = 0;

	private int _colourNumber = 1;

	private int _redIndex = 0;

	private int _greenIndex = 1;

	private int _blueIndex = 2;

	private BufferedImage _image = null;

	/**
	 * 
	 */
	public JSane_Image()
	{
		super();

	}

	/**
	 * @param _param
	 */
	/*
	 * Date		: Jan 8, 2005 
	 * Author	: panda 
	 * Date		:	Author		Reason
	 * Jan 8, 2005		panda		First added Updates comments
	 */
	public void startNewFrame( JSane_Base_Parameters param )
	{
		_param = param;

		if ( _param.format == JSane_Base_Parameters.FRAME_GRAY )
		{
			_image = new BufferedImage(
				_param.pixelsPerLine , _param.lines , BufferedImage.TYPE_BYTE_GRAY );
			_colourNumber = 1;
		}
		else
		{
			_image = new BufferedImage(
				_param.pixelsPerLine , _param.lines , BufferedImage.TYPE_3BYTE_BGR );

			_colourNumber = 1;

			if ( _param.format == JSane_Base_Parameters.FRAME_RGB )
			{
				_colourNumber = 3;
			}
		}
		_x = 0;
		_y = 0;
	}

	/**
	 * @param array
	 */
	/*
	 * Date		: Jan 8, 2005 
	 * Author	: panda 
	 * Date		:	Author		Reason
	 * Jan 8, 2005		panda		First added Updates comments
	 */
	public void addData( byte[] array )
	{
		for ( int loop = 0; loop < array.length; ++loop )
		{
			if ( _param.depth == 8 )
			{
				addData( array[loop] );
			}
			if ( _param.depth == 1 )
			{
				addDataByBit( array[loop] );
			}
		}
	}

	/**
	 * @param b
	 */
	/*
	 * Date		: Jan 8, 2005 
	 * Author	: panda 
	 * Date		:	Author		Reason
	 * Jan 8, 2005		panda		First added Updates comments
	 */
	private void addDataByBit( byte byt )
	{
		for ( int bit = 7; bit >= 0; --bit )
		{
			byte colour = (byte) 0;
			if ( (byt & (1 << bit)) == 0 )
			{
				colour = (byte) 255;
			}

			if ( _x < _param.pixelsPerLine && _y < _param.lines )
			{
				_storeAtXY( colour );
			}

			++_x;

			if ( _x >= _param.bytesPerLine * 8 )
			{
				_x = 0;
				++_y;
			}

		}
	}

	/**
	 * @param b
	 */
	/*
	 * Date		: Jan 8, 2005 
	 * Author	: panda 
	 * Date		:	Author		Reason
	 * Jan 8, 2005		panda		First added Updates comments
	 */
	private void addData( byte b )
	{
		if ( _x < _param.pixelsPerLine && _y < _param.lines )
		{
			_storeAtXY( b );
		}

		++_colourIndex;

		if ( _colourIndex >= _colourNumber )
		{
			_colourIndex = 0;

			++_x;

			if ( _x >= _param.bytesPerLine / _colourNumber )
			{
				_x = 0;
				++_y;
			}
		}
	}

	/**
	 * @param b
	 */
	/*
	 * Date		: Jan 8, 2005 
	 * Author	: panda 
	 * Date		:	Author		Reason
	 * Jan 8, 2005		panda		First added Updates comments
	 */
	private void _storeAtXY( byte b )
	{
		int colour = _image.getRGB( _x , _y );

		switch (_param.format)
		{
			case JSane_Base_Parameters.FRAME_GRAY :
				colour = (b << 16) + (b << 8) + b;
				break;

			case JSane_Base_Parameters.FRAME_BLUE :
				colour += (b << BLUE_OFFSET);
				break;

			case JSane_Base_Parameters.FRAME_GREEN :
				colour += (b << GREEN_OFFSET);
				break;

			case JSane_Base_Parameters.FRAME_RED :
				colour += (b << RED_OFFSET);
				break;

			case JSane_Base_Parameters.FRAME_RGB :
				if ( _colourIndex == _redIndex )
				{
					colour += (b << RED_OFFSET);
				}
				if ( _colourIndex == _greenIndex )
				{
					colour += (b << GREEN_OFFSET);
				}
				if ( _colourIndex == _blueIndex )
				{
					colour += (b << BLUE_OFFSET);
				}
		}

		_image.setRGB( _x , _y , colour );
	}

	/**
	 * @return
	 */
	/*
	 * Date		: Jan 8, 2005 
	 * Author	: panda 
	 * Date		:	Author		Reason
	 * Jan 8, 2005		panda		First added Updates comments
	 */
	public BufferedImage getImage()
	{
		return _image;
	}

}