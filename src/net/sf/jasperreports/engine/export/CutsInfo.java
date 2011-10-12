/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class used by grid exporters to create a grid for page layout.
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id$
 */
public class CutsInfo
{
//	public static final int USAGE_NOT_EMPTY = 1;
//	public static final int USAGE_SPANNED = 2;
//	public static final int USAGE_BREAK = 4;
	
	private final List<Integer> cutOffsets = new ArrayList<Integer>();
//	private int[] cutUsage;
	private Cut[] cuts;
	
	public CutsInfo()
	{
		addCutOffset(Integer.valueOf(0));
	}
	
	public CutsInfo(Integer lastCutOffset)
	{
		this();
		addCutOffset(lastCutOffset);
	}
	
	public List<Integer> getCutOffsets()
	{
		return cutOffsets;
	}
	
	public int size()
	{
		return cutOffsets.size();
	}
	
	public void use()//FIXMEXLS maybe remove this
	{
		if (cuts == null)
		{
			cuts = new Cut[cutOffsets.size()];
		}
	}
	
	public int getCutOffset(int index)
	{
		return cutOffsets.get(index).intValue();
	}
	
	public Cut getCut(int index)
	{
		Cut cut = cuts[index];
		if (cut == null)
		{
			cut = new Cut();
			cuts[index] = cut;
		}
		return cut;
	}
	
	public void addUsage(int index, int usage)
	{
		Cut cut = getCut(index);
		int tmpUsage = cut.getUsage();
		tmpUsage |= usage;
		cut.setUsage(tmpUsage);
	}
	
	public void setAutoFit(int index, Boolean autoFit)
	{
		getCut(index).setAutoFit(autoFit);
	}
	
	public void setCustomWidth(int index, int customWidth)
	{
		getCut(index).setCustomWidth(customWidth);
	}
	
	public void setWidthRatio(int index, float customWidth)
	{
		getCut(index).setWidthRatio(customWidth);
	}
	
	public boolean addCutOffset(Integer cutOffset)
	{
		int idx = Collections.binarySearch(cutOffsets, cutOffset);
		
		if (idx >= 0)
		{
			return false;
		}
		
		cutOffsets.add(-idx - 1, cutOffset);
		return true;
	}
	
	public int indexOfCutOffset(Integer cutOffset)
	{
		int idx = Collections.binarySearch(cutOffsets, cutOffset);
		
		if (idx < 0)
		{
			idx = -1;
		}
		
		return idx;
	}

	/**
	 * 
	 */
	public boolean isAutoFit(int index)//FIXMEXLS do this in Cut
	{
		return getCut(index).getAutoFit();
	}

	/**
	 * 
	 */
	public Integer getCustomWidth(int index)//FIXMEXLS do this in Cut
	{
		return getCut(index).getCustomWidth();
	}

	/**
	 * 
	 */
	public Float getWidthRatio(int index)//FIXMEXLS do this in Cut
	{
		return getCut(index).getWidthRatio();
	}

	/**
	 * Decides whether a cut is empty or not.
	 * 
	 * @param index the cut index
	 * @return <code>true</code> if and only if the cut is not empty
	 */
	public boolean isCutNotEmpty(int index)//FIXMEXLS do this in Cut
	{
		Cut cut = getCut(index);
		return ((cut.getUsage() & Cut.USAGE_NOT_EMPTY) > 0);
	}

	/**
	 * Decides whether a cut is occupied by spanning cells or not.
	 * 
	 * @param index the cut index
	 * @return <code>true</code> if and only if the cut is not empty
	 */
	public boolean isCutSpanned(int index)
	{
		Cut cut = getCut(index);
		return ((cut.getUsage() & Cut.USAGE_SPANNED) > 0);
	}

	/**
	 * 
	 */
	public boolean isBreak(int index)
	{
		Cut cut = getCut(index);
		return ((cut.getUsage() & Cut.USAGE_BREAK) > 0);
	}
	
	public boolean hasCuts()
	{
		return !cutOffsets.isEmpty();
	}
	
	public int getFirstCutOffset()
	{
		return getCutOffset(0);
	}
	
	public int getLastCutOffset()
	{
		return getCutOffset(size() - 1);
	}
	
	public int getTotalLength()
	{
		return hasCuts() ? getLastCutOffset() - getFirstCutOffset() : 0;
	}
}
