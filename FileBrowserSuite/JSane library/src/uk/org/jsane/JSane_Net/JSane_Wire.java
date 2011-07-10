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
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import uk.org.jsane.JSane_Base.JSane_Base_Type_Word;
/**
 * @author Andi McLean
 */
public abstract class JSane_Wire
{
	private InputStream _in = null;
	private OutputStream _out = null;
	private Socket _socket = null;

	public static boolean showInput = false;
	public static boolean showOutput = false;

	/**
	 * 
	 */
	public JSane_Wire(String hostname, int port)
		throws IOException, UnknownHostException
	{
		_socket = new Socket(hostname, port);
		_in = _socket.getInputStream();
		_out = _socket.getOutputStream();
	}

	public void sendByte(int byt) throws IOException
	{
		if (showOutput)
			System.out.print("Out " + byt + " /");

		_out.write(byt);
	}

	public int getByte()  throws IOException
	{
		int val = _in.read();
		if (showInput)
			System.out.print("In " + val + " /");
		return val;
	}

	public byte[] getBlock(int size)
	{
		byte[] array = new byte[size];
		int offset = 0;
		try
		{
			while (size > 0)
			{
				int len = _in.read(array, offset, size);
				offset += len;
				size -= len;
			}
		}
		catch (IOException e)
		{

		}

		return array;
	}

	public abstract void sendWord(int word)  throws IOException;

	public abstract void sendString(String str)  throws IOException;
	public abstract void sendArray(JSane_Net_Transport[] array)  throws IOException;

	public abstract int getWord()  throws IOException;

	public abstract String getString()  throws IOException;
	public abstract Vector getArray(JSane_Net_Transport obj)  throws IOException;
	public abstract Vector getPtrArray(JSane_Net_Transport obj)  throws IOException;

	public void sendPtr(JSane_Net_Transport obj)  throws IOException
	{
		if (obj == null)
		{
			sendWord(1);
		}
		else
		{
			sendWord(0);
			obj._sendElement(this);
		}
	}

	public void getPtr(JSane_Net_Transport obj)  throws IOException
	{
		int isNull = getWord();
		if (isNull == 0)
		{
			obj._getElement(this);
		}
	}

	/**
	 * @param handle
	 */
	public void sendWord(JSane_Base_Type_Word handle)  throws IOException
	{
		sendWord(handle.getValue());
	}
}
