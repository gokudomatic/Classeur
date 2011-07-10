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

package uk.org.jsane.JSane_Frontends;

import java.io.IOException;

import uk.org.jsane.JSane_Base.JSane_Base_Device;
import uk.org.jsane.JSane_Base.JSane_Base_Option_Type_Descriptor;
import uk.org.jsane.JSane_Base.JSane_Base_Type_Group;
import uk.org.jsane.JSane_Exceptions.JSane_Exception;
import uk.org.jsane.JSane_Net.JSane_Net_Connection;
import uk.org.jsane.JSane_Net.JSane_Wire;

/**
 * @author Andi McLan
 */
public class Tester
{
	/**
	 * 
	 */
	public Tester()
	{
		try
		{
			JSane_Net_Connection connection = new JSane_Net_Connection("127.0.0.1", 6566);

			for (int deviceNum = 0;
				deviceNum < connection.getNumberDevices();
				++deviceNum)
			{
				JSane_Base_Device device = connection.getDevice(deviceNum);
				if (device != null)
				{
					System.out.println("Device = " + device);

					if (device != null)
					{
						device.open();
						int options = device.getNumberOptions();
						for (int loop = 0; loop < options; ++loop)
						{
							JSane_Base_Option_Type_Descriptor option = device.getOption(loop);
							System.out.println(option);
							
							if (!( option.getValueType()  instanceof JSane_Base_Type_Group ) ) {
								System.out.println(device.getOption(loop).getValue());
							}
						}
						device.close();
					}
					else
					{
						System.out.println("Open failed");
					}
				}
			}
			connection.exit();
		}
		catch (IOException e)
		{
			System.out.println("Exception - " + e);

		} catch (JSane_Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		new Tester();
	}
}
