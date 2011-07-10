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

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import uk.org.jsane.JSane_Base.JSane_Base_Device;
import uk.org.jsane.JSane_Base.JSane_Base_Frame;
import uk.org.jsane.JSane_Net.JSane_Net_Connection;

/**
 * @Author Andi McLean
 */
public class ScanPage
{

	/**
	 * 
	 */
	public ScanPage()
	{
		try
		{
			JSane_Net_Connection connection =
				new JSane_Net_Connection("127.0.0.1", 6566);

			// Make sure there is at least one device connected.
			if (connection.getNumberDevices() > 0)
			{
				// Get the device.
				JSane_Base_Device device = connection.getDevice(0);

				//Make sure we have a device
				if (device != null)
				{
					// Try to open the device
					device.open();

					device.getOption("resolution").setValue("150");
					device.getOption("mode").setValue("Halftone");
					device.getOption("source").setValue("ADF");
					//device.getOption("depth").setValue("8");
					device.getOption("tl-x").setValue("52.8");
					device.getOption("tl-y").setValue("0.0");
					device.getOption("br-x").setValue("164.6");
					device.getOption("br-y").setValue("170.3");

					// Get the frame from the device.
					// This performs the scan on the scanner.
					JSane_Base_Frame frame = device.getFrame();

					// Get the image from frame
					BufferedImage image = frame.getImage(false);
					device.close();
					connection.exit();
					JFrame imageFrame = new JFrame();
					JLabel label = new JLabel(new ImageIcon(image));

					imageFrame.getContentPane().add(label);
					imageFrame.pack();
					imageFrame.show();

				}
				else
				{
					System.out.println("Couldn't get device");
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		System.out.println("Scanning a page");
		new ScanPage();
	}
}
