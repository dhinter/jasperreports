/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2014 TIBCO Software Inc. All rights reserved.
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
package net.sf.jasperreports.components.barcode4j;

import net.sf.jasperreports.engine.type.EnumUtil;
import net.sf.jasperreports.engine.type.NamedEnum;

import com.itextpdf.text.pdf.qrcode.ErrorCorrectionLevel;


/**
 * @author sanda zaharia (shertage@users.sourceforge.net)
 */
public enum ErrorCorrectionLevelEnum implements NamedEnum
{
	/**
	 *
	 */
	L(ErrorCorrectionLevel.L, "L"),

	/**
	 *
	 */
	M(ErrorCorrectionLevel.M, "M"),

	/**
	 *
	 */
	Q(ErrorCorrectionLevel.Q, "Q"),

	/**
	 *
	 */
	H(ErrorCorrectionLevel.H, "H");

	/**
	 *
	 */
	private final transient ErrorCorrectionLevel value;
	private final transient String name;

	private ErrorCorrectionLevelEnum(ErrorCorrectionLevel errorCorrectionLevel, String name) 
	{
		this.value = errorCorrectionLevel;
		this.name = name;
	}

	/**
	 *
	 */
	public String getName()
	{
		return name;
	}

	/**
	 *
	 */
	public final ErrorCorrectionLevel getErrorCorrectionLevel()
	{
		return value;
	}

	/**
	 *
	 */
	public static ErrorCorrectionLevelEnum getByName(String name)
	{
		return EnumUtil.getEnumByName(values(), name);
	}
}