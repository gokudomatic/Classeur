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
import java.net.UnknownHostException;
import java.util.Vector;

/**
 * @author Andi McLean
 */
public class JSane_Wire_Binary extends JSane_Wire
{

	private final static int STRINGLENGTH = 250;
	int curr = 0;
	
	/**
	 * 
	 */
	public JSane_Wire_Binary(String hostname , int port) throws IOException , UnknownHostException
	{
		super(hostname , port);
	}

	/* (non-Javadoc)
	 * @see uk.org.jsane.JSane_Wire#sendWord(int)
	 */
	public void sendWord(int word)  throws IOException
	{
		
		sendByte((word >> 24) & 0xff);
		sendByte((word >> 16) & 0xff);
		sendByte((word >> 8) & 0xff);
		sendByte((word >> 0) & 0xff);
		if (showOutput)
			System.out.println("Word Out = " + word);
	}

	/* (non-Javadoc)
	 * @see uk.org.jsane.JSane_Wire#sendString(java.lang.String)
	 */
	public void sendString(String str)  throws IOException
	{
		sendWord(STRINGLENGTH);
		for(int loop = 0 ; loop < STRINGLENGTH; ++loop)
		{
			if (loop < str.length())
			{ 
				sendByte((int) str.charAt(loop));
			}
			else
			{
				sendByte(0);
			}
		}
		if (showOutput)
			System.out.println("Sending String " + str);		

	}

	/* (non-Javadoc)
	 * @see uk.org.jsane.JSane_Wire#sendArray(java.lang.Object[])
	 */
	public void sendArray(JSane_Net_Transport[] array)  throws IOException
	{
		sendWord(array.length);
		for(int loop = 0; loop < array.length ; ++loop)
		{
			array[loop]._sendElement(this);
		}
	}

	/* 
	 */
	public int getWord()  throws IOException
	{
		int value = 0;
		
		value += (getByte() << 24);
		value += (getByte() << 16);
		value += (getByte() << 8);
		value += (getByte() << 0);
		if (showInput)
			System.out.println("Word In = " + value);

		return value;
	}

	/* (non-Javadoc)
	 * @see uk.org.jsane.JSane_Wire#getString()
	 */
	public String getString() throws IOException
	{
		
		String str = "";
		
		int size = getWord();
		boolean add = true;
		
		if (size >0)
		{
			for (int loop = 0 ; loop < size; ++loop)
			{
				int byt = getByte();
				char chr =  (char) byt;
				if (byt == 0)
				{
					add = false;
				}
				if (add)
				{
					str += chr;
				}
			}
		}
		if (showInput)
			System.out.println("Getting String " + str);		
		return str;
	}

	/* (non-Javadoc)
	 * @see uk.org.jsane.JSane_Wire#getArray()
	 */
	public Vector getArray(JSane_Net_Transport obj) throws IOException
	{
		int size = getWord();
		
		Vector vec = new Vector();

		for(int loop = 0 ; loop < size ; ++loop)
		{
			vec.add(obj._getNewElement(this));		
		}
		return vec;
	}
	
	public Vector getPtrArray(JSane_Net_Transport obj) throws IOException
	{
		int size = getWord();

		Vector vec = new Vector();
		int isNull = 0;//getWord();
		while (size >0 )
		{
			isNull = getWord();
			if (isNull == 0) {
				vec.add(obj._getNewElement(this));
			}
			--size;
		}
		return vec;
	}
}
