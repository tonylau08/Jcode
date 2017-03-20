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

package com.igeeksky.jcode.generator;

import org.apache.commons.lang.WordUtils;

import com.igeeksky.common.util.StringUtils;

/**
 * <b></b><br>
 * @author Tony.Lau
 * @blog: https://my.oschina.net/xcafe
 * @createTime 2017-03-15 05:27:25
 */
public class NameUtil {
	
	public static String getCamelCaseString(String columnName) {
		return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
	}
	
	public static String firstToLowwer(String methodName) {
		StringBuilder builder = new StringBuilder(methodName);
		builder.setCharAt(0, Character.toLowerCase(methodName.charAt(0)));
		return builder.toString();
	}
	
	public static String buildClassName(String tableName) {
		String tablePrefix = "";
		if(StringUtils.isNotEmpty(tablePrefix)){
			tableName = tableName.replace(tablePrefix, "");
		}
		return getCamelCaseString(tableName);
	}

}
