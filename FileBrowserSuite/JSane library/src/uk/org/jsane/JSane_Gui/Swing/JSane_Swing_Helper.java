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

package uk.org.jsane.JSane_Gui.Swing;

/**
 * @author Andi McLean
 *
 */
public class JSane_Swing_Helper
{
	public static java.awt.GridBagConstraints getNewGridBagConstraints( int fill ,
		JSane_Swing_Helper.Panda_Position pos , int weightx , int weighty ,
		int width , int height )
	{
		java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();

		gridBagConstraints.fill = fill;
		gridBagConstraints.gridx = pos.getX();
		gridBagConstraints.gridy = pos.getY();
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.gridheight = height;
		gridBagConstraints.gridwidth = width;
		gridBagConstraints.ipadx = 5;

		pos.move( width , height );

		return gridBagConstraints;
	}

	public static class Panda_Position
	{
		/** Vertical movement */
		public static final int VERTICAL = 1;

		/** Horizontal movement */
		public static final int HORIZONTAL = 2;

		protected int _x = 0;

		protected int _y = 0;

		protected int _direction;

		/**
		 * Create a Panda_Position with a default start of 0,0
		 * 
		 * @param direction.
		 *          The direction that we intend to go in.
		 */
		public Panda_Position( int direction )
		{
			super();

			_x = 0;
			_y = 0;

			_direction = direction;
		}

		/**
		 * Create a Panda_Position with a default start of 0,0 and moves
		 * Horizontaly.
		 */
		public Panda_Position()
		{
			super();

			_x = 0;
			_y = 0;

			_direction = HORIZONTAL;
		}

		/**
		 * Move either horizontaly or verticaly depending on the type.
		 * 
		 * @param xinc
		 *          number to move horizontaly
		 * @param yinc
		 *          number to move vertiacaly
		 */
		public void move( int xinc , int yinc )
		{
			if ( _direction == HORIZONTAL )
			{
				_x += xinc;
			}
			else
			{
				_y += yinc;
			}
		}

		/**
		 * Move to the next row. Resetting the x position
		 */
		public void nextRow()
		{
			_x = 0;
			++_y;
		}

		/**
		 * Get the current x position
		 * 
		 * @return the current x position
		 */
		public int getX()
		{
			return _x;
		}

		/**
		 * Get the current y position
		 * 
		 * @return the current y position
		 */
		public int getY()
		{
			return _y;
		}

	}

}