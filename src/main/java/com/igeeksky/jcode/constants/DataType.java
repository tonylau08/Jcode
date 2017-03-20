/*
 * Copyright 2017 Tony.lau All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.igeeksky.jcode.constants;

import java.sql.Types;

/**
 * <b>MySQL与Java类型对应表</b><br>
 * @author Tony.Lau
 * @blog: https://my.oschina.net/xcafe
 * @createTime 2017-03-12 09:59:32
 */
public enum DataType {
	
	/** 整型 */
	TTINYINT("Integer", Types.TINYINT),
	SMALLINT("Integer", Types.SMALLINT),
	MEDIUMINT("Integer", Types.INTEGER),
	INT("Integer", Types.INTEGER),
	INTEGER("Integer", Types.INTEGER),
	BIGINT("Long", Types.BIGINT),
	BIT("Boolean", Types.BIT),
	
	/** 浮点数类型 */
	REAL("Float", Types.REAL),
	FLOAT("Float", Types.FLOAT),
	DOUBLE("Double", Types.DOUBLE),
	/** DECIMAL(5,2) -999.99 to 999.99 */
	DECIMAL("BigDecimal", Types.DECIMAL),
	NUMERIC("BigDecimal", Types.NUMERIC),
	
	/** 字符串类型 */
	CHAR("String", Types.CHAR),
	VARCHAR("String", Types.VARCHAR),
	TINYTEXT("String", Types.VARCHAR),
	TEXT("String", Types.LONGNVARCHAR),
	MEDIUMTEXT("String", Types.LONGNVARCHAR),
	LONGTEXT("String", Types.LONGNVARCHAR),
	
	/** 二进制文本 */
	TINYBLOB("byte[]", Types.BLOB),
	BLOB("byte[]", Types.BLOB),
	MEDIUMBLOB("byte[]", Types.BLOB),
	LONGBLOB("byte[]", Types.BLOB),
	
	/** 时间 */
	DATE("String", Types.DATE),			//只有日期
	TIME("String", Types.TIME),			//只有时间
	YEAR("Integer", Types.DATE),		//只有年（废弃）
	TIMESTAMP("Date",Types.TIME_WITH_TIMEZONE),		//包含时区
	DATETIME("Date", Types.TIMESTAMP),		//未包含时区
	
	/** 字符串枚举 */
	ENUM("枚举：error_please_create_enum_object", Types.OTHER),
	SET("集合：error_please_create_enum_object", Types.OTHER),
	
	/** 二进制 */
	BINARY("byte[]", Types.BINARY),
	VARBINARY("byte[]", Types.VARBINARY),
	
	/** 地理位置信息 */
	POINT("地理位置：error_please_create_Point_object", Types.OTHER),
	LINESTRING("地理位置：error_please_create_Linestring_object", Types.OTHER),
	POLYGON("地理位置：error_please_create_Polygon_object", Types.OTHER),
	GEOMETRY("地理位置：error_please_create_Geometry_object", Types.OTHER),
	MULTIPOINT("地理位置：error_please_create_Multipoint_object", Types.OTHER),
	MULTILINESTRING("地理位置：error_please_create_Multilinestring_object", Types.OTHER),
	MULTIPOLYGON("地理位置：error_please_create_Multipolygon_object", Types.OTHER),
	GEOMETRYCOLLECTION("地理位置：error_please_create_Geometrycollection_object", Types.OTHER);
	
	private String javaType;
	
	private int typesCode;
	
	private DataType(String javaType, int typesCode){
		this.javaType = javaType;
		this.typesCode = typesCode;
	}
	
	public String getJavaType() {
		return javaType;
	}
	
	public int getTypesCode() {
		return typesCode;
	}

}
