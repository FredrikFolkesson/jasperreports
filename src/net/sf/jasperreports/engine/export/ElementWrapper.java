/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Contributors:
 * Greg Hilton 
 */

package net.sf.jasperreports.engine.export;

import net.sf.jasperreports.engine.JRPrintElement;

/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id$
 */
public class ElementWrapper
{
	protected static final int ELEMENT_INDEX_NONE = Integer.MIN_VALUE;
	
	private JRPrintElement element;
	private PrintElementIndex parentIndex;
	private int elementIndex;
	private ElementWrapper[] wrappers;
	private ElementWrapper parent;
	
	public ElementWrapper(
		ElementWrapper parent,
		JRPrintElement element, 
		PrintElementIndex parentIndex,
		int elementIndex
		)
	{
		this.parent = parent;
		this.element = element;
		this.parentIndex = parentIndex;
		this.elementIndex = elementIndex;
	}
	
	public ElementWrapper getParent()
	{
		return parent;
	}
	
	public JRPrintElement getElement()
	{
		return element;
	}

	public PrintElementIndex getParentIndex()
	{
		return parentIndex;
	}

	public int getElementIndex()
	{
		return elementIndex;
	}
	
	public String getAddress()
	{
		if (elementIndex == ELEMENT_INDEX_NONE)
		{
			return null;
		}

		return PrintElementIndex.asAddress(parentIndex, elementIndex);
	}
	
	public ElementWrapper[] getWrappers()
	{
		return wrappers;
	}
	
	public void setWrappers(ElementWrapper[] wrappers)
	{
		this.wrappers = wrappers;
	}
	
	public String getProperty(String propName)
	{
		if (
			element != null
			&& element.hasProperties()
			&& element.getPropertiesMap().containsProperty(propName)
			)
		{
			return element.getPropertiesMap().getProperty(propName);
		}
		else if (parent != null)
		{
			return parent.getProperty(propName);
		}
		
		return null;
	}
}