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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import uk.org.jsane.JSane_Base.JSane_Base_Frame;
import uk.org.jsane.JSane_Base.JSane_Base_Parameters;
import uk.org.jsane.JSane_Base.JSane_Base_Type_Word;
import uk.org.jsane.JSane_Exceptions.JSane_Exception;
import uk.org.jsane.JSane_Exceptions.JSane_Exception_IoError;
import uk.org.jsane.JSane_Exceptions.JSane_Exception_Unsupported;

/**
 * @author weirdpanda
 * Contains a scanned page.
 */
public class JSane_Net_Frame extends JSane_Base_Frame
{
	protected JSane_Net_Device _dev;

	protected JSane_Wire _wire;

	protected ByteArrayOutputStream _array = new ByteArrayOutputStream();

	/**
	 * Constructor
	 * @param dev The device to get the frame from.
	 */
	/* 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	public JSane_Net_Frame( JSane_Net_Device dev ) throws JSane_Exception
	{
		super();

		_dev = dev;

		do
		{
			_reply = _dev.start( ( JSane_Base_Type_Word ) _dev._getHandle() );
			status = _reply.status;

			if ( status == JSane_Net_Connection.SANE_STATUS_GOOD )
			{
				try
				{
					_wire = new JSane_Wire_Binary( _dev.getHostname() ,
							_reply.port.getValue() );

					JSane_Base_Parameters param = dev.getParameters();

					if ( param.depth > 8 )
					{
						_dev.cancel();

						throw new JSane_Exception_Unsupported(
								"Colour depths greater than 8 bits are not supported" );
					}

					_param = param;

					_frameImage.startNewFrame( _param );

					_getFrameData();
				}
				catch ( IOException e )
				{
					System.out.println( e );
				}
			}
			else
			{
				throw JSane_Exception.getException( status );
			}
		}
		while ( !_param.lastFrame );
	}

	/* 
	 * date				author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	protected void _getFrameData() throws JSane_Exception
	{
		try
		{
			int size = 0;
			do
			{
				size = _wire.getWord();
				if ( size > 0 )
				{
					_getData( size );
				}
			}
			while ( size != -1 );
		}
		catch ( IOException e )
		{
			throw new JSane_Exception_IoError( e.getMessage() );
		}
	}

	/* 
	 * date			author			reason
	 * 09/Dec/03	am				Initial version.
	 */
	protected void _getData( int size )
	{
		_frameImage.addData( _wire.getBlock( size ) );
	}
}