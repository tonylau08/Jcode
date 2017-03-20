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

package com.igeeksky.jcode.service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.TreeSet;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igeeksky.jcode.dao.CodeDao;
import com.igeeksky.jcode.domain.ColumnInfo;
import com.igeeksky.jcode.domain.IndexInfo;
import com.igeeksky.jcode.domain.KeyInfo;
import com.igeeksky.jcode.domain.TableInfo;
import com.igeeksky.jcode.generator.Generator;
import com.igeeksky.jcode.util.IOUtils;

/**
 * @author Tony.Lau
 * @blog: https://my.oschina.net/xcafe
 * @createTime 2017-03-11 04:09:15
 */
@Service
public class CodeServiceImpl implements CodeService{
	
	@Autowired
	private CodeDao codeDao;
	
	@Override
	public byte[] genCode(String tableSchema, String[] tableNames) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		
		for(String tableName : tableNames){
			TableInfo table = getOneTable(tableSchema, tableName);
			Generator.buildCode(table, zip);
		}
		IOUtils.release(zip);
		
		byte[] bytes = outputStream.toByteArray();
		IOUtils.release(outputStream);
		
		return bytes;
	}
	
	private TableInfo getOneTable(String tableSchema, String tableName) {
		TableInfo table = codeDao.queryTable(tableSchema, tableName);
		TreeSet<ColumnInfo> columns = new TreeSet<ColumnInfo>(codeDao.queryColumnsByTable(tableSchema, tableName));
		for(ColumnInfo column : columns){
			String columnName = column.getColumnName();
			TreeSet<KeyInfo> keys = new TreeSet<KeyInfo>(codeDao.queryKeysByColumn(tableSchema, tableName, columnName));
			TreeSet<IndexInfo> indexes = new TreeSet<IndexInfo>(codeDao.queryIndexByColumn(tableSchema, tableName, columnName));
			column.setKeys(keys);
			column.setIndexes(indexes);
			table.addIndexSet(indexes);
		}
		table.setColumns(columns);
		return table;
	}

	@Override
	public List<TableInfo> getAllTables(String tableSchema){
		return codeDao.queryTablesBySchema(tableSchema);
	}
	
	/**
	 * <b>说  明：</b>根据表获取字段<br>
	 */
	@Override
	public List<ColumnInfo> getColumnsByTable(String tableSchema, String tableName){
		return codeDao.queryColumnsByTable(tableSchema, tableName);
	}
	
	public CodeServiceImpl(CodeDao codeDao){
		this.codeDao = codeDao;
	}

}
