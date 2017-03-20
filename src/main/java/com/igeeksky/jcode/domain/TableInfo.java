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

package com.igeeksky.jcode.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.igeeksky.jcode.constants.DataType;
import com.igeeksky.jcode.constants.TableType;
import com.igeeksky.jcode.generator.AnnotationUtil;
import com.igeeksky.jcode.generator.NameUtil;

/**
 * <b>表信息</b><br>
 * <b>information_schema.tables</b><br>
 * @author Tony.Lau
 * @blog: https://my.oschina.net/xcafe
 * @createTime 2017-03-11 04:11:51
 */
public class TableInfo {
	
	/** TABLE_CATALOG */
	private String tableCatalog;
	
	/** 表名 TABLE_NAME */
	private String tableName;
	
	/** 表注释 TABLE_COMMENT */
	private String tableComment;

	/** 模式 TABLE_SCHEMA */
	private String tableSchema;
	
	/** 类型 TABLE_TYPE */
	private TableType tableType;
	
	/** 存储引擎 ENGINE */
	private String engine;
	
	/** 版本 VERSION */
	private Long version;
	
	/** ROW_FORMAT */
	private String rowFormat;
	
	/** TABLE_ROWS */
	private Long tableRows;
	
	/** AVG_ROW_LENGTH */
	private Long avgRowLength;
	
	/** DATA_LENGTH */
	private Long dataLength;
	
	/** MAX_DATA_LENGTH */
	private Long maxDataLength;
	
	/** INDEX_LENGTH */
	private Long indexLength;
	
	/** DATA_FREE */
	private Long dataFree;
	
	/** AUTO_INCREMENT */
	private Long autoIncrement;
	
	/** CREATE_TIME */
	private Date createTime;
	
	/** UPDATE_TIME */
	private Date updateTime;
	
	/** CHECK_TIME */
	private Date checkTime;
	
	/** TABLE_COLLATION */
	private String tableCollation;
	
	/** CHECKSUM */
	private Long checksum;
	
	/** CREATE_OPTIONS */
	private String createOptions;
	
	private String className;
	
	/** 是否包含BigDecimal数值类型 */
	private boolean hasBigDecimal = false;
	
	private TreeSet<ColumnInfo> columns = new TreeSet<ColumnInfo>();
	
	private Map<String, TreeSet<IndexInfo>> indexes = new HashMap<String, TreeSet<IndexInfo>>();
	
	public String getAnnotation(){
		return AnnotationUtil.buildTableAnnotation(this);
	}
	
	public boolean isHasBigDecimal() {
		return hasBigDecimal;
	}
	
	public void setColumns(TreeSet<ColumnInfo> columns) {
		for(ColumnInfo column : columns){
			if(column.getDataType().equals(DataType.DECIMAL) || column.getDataType().equals(DataType.NUMERIC)){
				hasBigDecimal = true;
			}
		}
		this.columns = columns;
	}

	public TreeSet<ColumnInfo> getColumns() {
		return columns;
	}

	public Map<String, TreeSet<IndexInfo>> getIndexes() {
		return indexes;
	}
	
	public void addIndexSet(Set<IndexInfo> indexSet) {
		for(IndexInfo index : indexSet){
			String indexName = index.getIndexName();
			TreeSet<IndexInfo> treeSet = indexes.get(indexName);
			if(null == treeSet){
				treeSet = new TreeSet<IndexInfo>();
				indexes.put(indexName, treeSet);
			}
			treeSet.add(index);
		}
	}

	public String getTableCatalog() {
		return tableCatalog;
	}

	public void setTableCatalog(String tableCatalog) {
		this.tableCatalog = tableCatalog;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
		this.className = NameUtil.buildClassName(tableName);
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public String getTableSchema() {
		return tableSchema;
	}

	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}

	public TableType getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = TableType.getTableType(tableType);
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getRowFormat() {
		return rowFormat;
	}

	public void setRowFormat(String rowFormat) {
		this.rowFormat = rowFormat;
	}

	public Long getTableRows() {
		return tableRows;
	}

	public void setTableRows(Long tableRows) {
		this.tableRows = tableRows;
	}

	public Long getAvgRowLength() {
		return avgRowLength;
	}

	public void setAvgRowLength(Long avgRowLength) {
		this.avgRowLength = avgRowLength;
	}

	public Long getDataLength() {
		return dataLength;
	}

	public void setDataLength(Long dataLength) {
		this.dataLength = dataLength;
	}

	public Long getMaxDataLength() {
		return maxDataLength;
	}

	public void setMaxDataLength(Long maxDataLength) {
		this.maxDataLength = maxDataLength;
	}

	public Long getIndexLength() {
		return indexLength;
	}

	public void setIndexLength(Long indexLength) {
		this.indexLength = indexLength;
	}

	public Long getDataFree() {
		return dataFree;
	}

	public void setDataFree(Long dataFree) {
		this.dataFree = dataFree;
	}

	public Long getAutoIncrement() {
		return autoIncrement;
	}

	public void setAutoIncrement(Long autoIncrement) {
		this.autoIncrement = autoIncrement;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getTableCollation() {
		return tableCollation;
	}

	public void setTableCollation(String tableCollation) {
		this.tableCollation = tableCollation;
	}

	public Long getChecksum() {
		return checksum;
	}

	public void setChecksum(Long checksum) {
		this.checksum = checksum;
	}

	public String getCreateOptions() {
		return createOptions;
	}

	public void setCreateOptions(String createOptions) {
		this.createOptions = createOptions;
	}

	public String getClassName() {
		return className;
	}

}
