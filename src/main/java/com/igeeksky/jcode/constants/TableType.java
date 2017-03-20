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

/**
 * <b></b><br>
 * @author Tony.Lau
 * @blog: https://my.oschina.net/xcafe
 * @createTime 2017-03-12 12:18:42
 */
public enum TableType {
	
	BASE_TABLE("BASE TABLE"),
	SYSTEM_VIEW("SYSTEM VIEW"),
	VIEW("VIEW");
	
	private String type;
	
	private TableType(String type){
		this.type = type;
	}
	
	public String getType(){
		return type;
	}
	
	public static TableType getTableType(String type){
		String[] temp = type.split(" ");
		if(temp.length>1){
			type = temp[0]+"_"+temp[1];
		}
		return TableType.valueOf(type);
	}

}
