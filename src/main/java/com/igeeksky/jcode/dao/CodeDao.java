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

package com.igeeksky.jcode.dao;

import java.util.List;

import com.igeeksky.jcode.domain.ColumnInfo;
import com.igeeksky.jcode.domain.IndexInfo;
import com.igeeksky.jcode.domain.KeyInfo;
import com.igeeksky.jcode.domain.TableInfo;

/**
 * <b></b><br>
 * @author Tony.Lau
 * @blog: https://my.oschina.net/xcafe
 * @createTime 2017-03-14 05:57:14
 */
public interface CodeDao {
	
	public List<TableInfo> queryTablesBySchema(String tableSchema);
	
	public TableInfo queryTable(String tableSchema, String tableName);
	
	public List<ColumnInfo> queryColumnsByTable(String tableSchema, String tableName);

	public List<IndexInfo> queryIndexByColumn(String tableSchema, String tableName, String columnName);

	public List<KeyInfo> queryKeysByColumn(String tableSchema, String tableName, String columnName);

}
