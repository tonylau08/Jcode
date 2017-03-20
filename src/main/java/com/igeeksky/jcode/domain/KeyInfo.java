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

import com.igeeksky.jcode.generator.AnnotationUtil;

/**
 * <b>主键/外键信息</b><br>
 * <b>information_schema.key_column_usage</b><br>
 * @author Tony.Lau
 * @blog: https://my.oschina.net/xcafe
 * @createTime 2017-03-12 08:51:15
 */
public class KeyInfo implements Comparable<KeyInfo> {
	
	/** CONSTRAINT_CATALOG */
	private String constraintCatalog;
	
	/** 常量模式 CONSTRAINT_SCHEMA */
	private String constraintSchema;
	
	/** 键类型 CONSTRAINT_NAME */
	private String constraintName;
	
	/** TABLE_CATALOG */
	private String tableCatalog;
	
	/** 表模式 TABLE_SCHEMA */
	private String tableSchema;
	
	/** 表名 TABLE_NAMEG */
	private String tableName;
	
	/** 字段名 COLUMN_NAME */
	private String columnName;
	
	/** 字段顺序 ORDINAL_POSITION */
	private Integer ordinalPosition;
	
	/** POSITION_IN_UNIQUE_CONSTRAINT */
	private Integer positionInUniqueConstraint;
	
	/** REFERENCED_TABLE_SCHEMA */
	private String referencedTableSchema;
	
	/** REFERENCED_TABLE_NAME */
	private String referencedTableName;
	
	/** REFERENCED_COLUMN_NAME */
	private String referencedColumnName;
	
	public String getAnnotation() {
		return AnnotationUtil.buildKeyAnnotation(this);
	}

	public String getConstraintCatalog() {
		return constraintCatalog;
	}

	public void setConstraintCatalog(String constraintCatalog) {
		this.constraintCatalog = constraintCatalog;
	}

	public String getConstraintSchema() {
		return constraintSchema;
	}

	public void setConstraintSchema(String constraintSchema) {
		this.constraintSchema = constraintSchema;
	}

	public String getConstraintName() {
		return constraintName;
	}

	public void setConstraintName(String constraintName) {
		this.constraintName = constraintName;
	}

	public String getTableCatalog() {
		return tableCatalog;
	}

	public void setTableCatalog(String tableCatalog) {
		this.tableCatalog = tableCatalog;
	}

	public String getTableSchema() {
		return tableSchema;
	}

	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Integer getOrdinalPosition() {
		return ordinalPosition;
	}

	public void setOrdinalPosition(Integer ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}

	public Integer getPositionInUniqueConstraint() {
		return positionInUniqueConstraint;
	}

	public void setPositionInUniqueConstraint(Integer positionInUniqueConstraint) {
		this.positionInUniqueConstraint = positionInUniqueConstraint;
	}

	public String getReferencedTableSchema() {
		return referencedTableSchema;
	}

	public void setReferencedTableSchema(String referencedTableSchema) {
		this.referencedTableSchema = referencedTableSchema;
	}

	public String getReferencedTableName() {
		return referencedTableName;
	}

	public void setReferencedTableName(String referencedTableName) {
		this.referencedTableName = referencedTableName;
	}

	public String getReferencedColumnName() {
		return referencedColumnName;
	}

	public void setReferencedColumnName(String referencedColumnName) {
		this.referencedColumnName = referencedColumnName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
		result = prime * result + ((constraintCatalog == null) ? 0 : constraintCatalog.hashCode());
		result = prime * result + ((constraintName == null) ? 0 : constraintName.hashCode());
		result = prime * result + ((constraintSchema == null) ? 0 : constraintSchema.hashCode());
		result = prime * result + ((ordinalPosition == null) ? 0 : ordinalPosition.hashCode());
		result = prime * result + ((positionInUniqueConstraint == null) ? 0 : positionInUniqueConstraint.hashCode());
		result = prime * result + ((referencedColumnName == null) ? 0 : referencedColumnName.hashCode());
		result = prime * result + ((referencedTableName == null) ? 0 : referencedTableName.hashCode());
		result = prime * result + ((referencedTableSchema == null) ? 0 : referencedTableSchema.hashCode());
		result = prime * result + ((tableCatalog == null) ? 0 : tableCatalog.hashCode());
		result = prime * result + ((tableName == null) ? 0 : tableName.hashCode());
		result = prime * result + ((tableSchema == null) ? 0 : tableSchema.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeyInfo other = (KeyInfo) obj;
		if (columnName == null) {
			if (other.columnName != null)
				return false;
		} else if (!columnName.equals(other.columnName))
			return false;
		if (constraintCatalog == null) {
			if (other.constraintCatalog != null)
				return false;
		} else if (!constraintCatalog.equals(other.constraintCatalog))
			return false;
		if (constraintName == null) {
			if (other.constraintName != null)
				return false;
		} else if (!constraintName.equals(other.constraintName))
			return false;
		if (constraintSchema == null) {
			if (other.constraintSchema != null)
				return false;
		} else if (!constraintSchema.equals(other.constraintSchema))
			return false;
		if (ordinalPosition == null) {
			if (other.ordinalPosition != null)
				return false;
		} else if (!ordinalPosition.equals(other.ordinalPosition))
			return false;
		if (positionInUniqueConstraint == null) {
			if (other.positionInUniqueConstraint != null)
				return false;
		} else if (!positionInUniqueConstraint.equals(other.positionInUniqueConstraint))
			return false;
		if (referencedColumnName == null) {
			if (other.referencedColumnName != null)
				return false;
		} else if (!referencedColumnName.equals(other.referencedColumnName))
			return false;
		if (referencedTableName == null) {
			if (other.referencedTableName != null)
				return false;
		} else if (!referencedTableName.equals(other.referencedTableName))
			return false;
		if (referencedTableSchema == null) {
			if (other.referencedTableSchema != null)
				return false;
		} else if (!referencedTableSchema.equals(other.referencedTableSchema))
			return false;
		if (tableCatalog == null) {
			if (other.tableCatalog != null)
				return false;
		} else if (!tableCatalog.equals(other.tableCatalog))
			return false;
		if (tableName == null) {
			if (other.tableName != null)
				return false;
		} else if (!tableName.equals(other.tableName))
			return false;
		if (tableSchema == null) {
			if (other.tableSchema != null)
				return false;
		} else if (!tableSchema.equals(other.tableSchema))
			return false;
		return true;
	}

	@Override
	public int compareTo(KeyInfo another) {
		if(null != another && another.getOrdinalPosition()!=null){
			return ordinalPosition.compareTo(another.getOrdinalPosition());
		}
		return 1;
	}

}
