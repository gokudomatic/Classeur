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

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import uk.org.jsane.JSane_Base.JSane_Base_Constraint_Range;
import uk.org.jsane.JSane_Base.JSane_Base_Type_Word;
import uk.org.jsane.JSane_Gui.Interfaces.JSane_Widget_Wrapper_Interface;

/**
 * @author Andi McLean
 *  
 */
public class JSane_Range extends JSpinner implements
	JSane_Widget_Wrapper_Interface
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 984245402805823542L;

	private JSane_Base_Type_Word _currentValue = null;

	private JSane_Base_Type_Word _min = null;

	private JSane_Base_Type_Word _max = null;

	private JSane_Base_Type_Word _quant = null;

	/**
	 *  
	 */
	public JSane_Range()
	{
		super();
		_init();
	}

	private void _init()
	{
		setModel( new SpinnerNumberModel() );
	}

	/*
	 * Overridden method
	 */
	public void setWidgetValue( Object value )
	{

		if ( value instanceof JSane_Base_Type_Word )
		{
			_currentValue = (JSane_Base_Type_Word) value;
			Number number = ((JSane_Base_Type_Word) value).getNumber();
			SpinnerNumberModel model = new SpinnerNumberModel(
				number , (Comparable) _min.getNumber() , (Comparable) _max.getNumber() ,
				_quant.getNumber() );

			setModel( model );
		}
	}

	/*
	 * Overridden method
	 */
	public Object getWidgetValue()
	{
		if ( _currentValue != null )
		{
			_currentValue.setNumber( ((SpinnerNumberModel) getModel()).getValue() );
		}
		return _currentValue;
	}

	/*
	 * Overridden method
	 */
	public void setWidgetOptions( Object value )
	{
		if ( value instanceof JSane_Base_Constraint_Range )
		{
			_min = ((JSane_Base_Constraint_Range) value).getMin();
			_max = ((JSane_Base_Constraint_Range) value).getMax();
			_quant = ((JSane_Base_Constraint_Range) value).getQuant();

			if ( _currentValue != null )
			{
				SpinnerNumberModel model = new SpinnerNumberModel( _currentValue
					.getNumber() , (Comparable) _min.getNumber() , (Comparable) _max
					.getNumber() , _quant.getNumber() );

				setModel( model );
			}
		}
	}

	/* (non-Javadoc)
	 * @see uk.org.jsane.JSane_Gui.Interfaces.JSane_Widget_Wrapper_Interface#hasValueChanged()
	 */
	public boolean hasValueChanged()
	{
		return !(((SpinnerNumberModel) getModel()).getValue().equals(_currentValue));
	}
}