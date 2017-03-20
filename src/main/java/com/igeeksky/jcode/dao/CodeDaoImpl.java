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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.igeeksky.common.util.StringUtils;
import com.igeeksky.jcode.domain.ColumnInfo;
import com.igeeksky.jcode.domain.IndexInfo;
import com.igeeksky.jcode.domain.KeyInfo;
import com.igeeksky.jcode.domain.TableInfo;

/**
 * <b>说明：</b>代码生成器DAO<br>
 * @author Tony.Lau
 * @blog: https://my.oschina.net/xcafe
 * @createTime 2017-03-14 06:05:50
 */
@Repository
public class CodeDaoImpl implements CodeDao {

	@Autowired
	private BaseDao baseDao;
	
	@Override
	public TableInfo queryTable(String tableSchema, String tableName) {
		if(StringUtils.isEmpty(tableSchema)){
			tableSchema = baseDao.getTableSchema();
		}
		String driverClassName = baseDao.getDriverClassName();
		if(StringUtils.isEmpty(driverClassName) || StringUtils.isEmpty(tableName)){
			return null;
		}
		if(driverClassName.equals("com.mysql.jdbc.Driver")){
			return getAndConvertOne(CodeSQL.QUERY_TABLE, TableInfo.class, tableSchema, tableName);
		}
		return null;
	}

	@Override
	public List<TableInfo> queryTablesBySchema(String tableSchema){
		if(StringUtils.isEmpty(tableSchema)){
			tableSchema = baseDao.getTableSchema();
		}
		String driverClassName = baseDao.getDriverClassName();
		if(StringUtils.isEmpty(driverClassName)){
			return Collections.emptyList();
		}
		if(driverClassName.equals("com.mysql.jdbc.Driver")){
			return getAndConvert(CodeSQL.QUERY_TABLES_BY_SCHEMA, TableInfo.class, tableSchema);
		}
		return Collections.emptyList();
	}
	
	@Override
	public List<ColumnInfo> queryColumnsByTable(String tableSchema, String tableName){
		if(StringUtils.isEmpty(tableSchema)){
			tableSchema = baseDao.getTableSchema();
		}
		return getAndConvert(CodeSQL.QUERY_COLUMN_BY_TABLE, ColumnInfo.class, tableSchema, tableName);
	}
	
	@Override
	public List<IndexInfo> queryIndexByColumn(String tableSchema, String tableName, String columnName) {
		if(StringUtils.isEmpty(tableSchema)){
			tableSchema = baseDao.getTableSchema();
		}
		return getAndConvert(CodeSQL.QUERY_INDEX_BY_COLUMN, IndexInfo.class, tableSchema, tableName, columnName);
	}

	@Override
	public List<KeyInfo> queryKeysByColumn(String tableSchema, String tableName, String columnName) {
		if(StringUtils.isEmpty(tableSchema)){
			tableSchema = baseDao.getTableSchema();
		}
		return getAndConvert(CodeSQL.QUERY_KEY_BY_COLUMN, KeyInfo.class, tableSchema, tableName, columnName);
	}
	
	private <T>List<T> getAndConvert(String sql, Class<T> type, Object... params){
		List<JSONObject> list = baseDao.query(sql, params);
		List<T> results = new ArrayList<T>();
		for(JSONObject json : list){
			T index = json.toJavaObject(type);
			results.add(index);
		}
		return results;
	}
	
	private <T> T getAndConvertOne(String sql, Class<T> type, Object... params){
		JSONObject json = baseDao.queryOne(sql, params);
		return json.toJavaObject(type);
	}
	
	public CodeDaoImpl(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
