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

import java.util.Set;
import java.util.TreeSet;

import com.igeeksky.common.util.StringUtils;
import com.igeeksky.jcode.constants.DataType;
import com.igeeksky.jcode.generator.AnnotationUtil;
import com.igeeksky.jcode.generator.NameUtil;

/**
 * <b>字段信息</b><br>
 * <b>information_schema.COLUMNS</b><br>
 * @author Tony.Lau
 * @blog: https://my.oschina.net/xcafe
 * @createTime 2017-03-11 05:15:11
 */
public class ColumnInfo implements Comparable<ColumnInfo> {
	
	/** TABLE_CATALOG */
	private String tableCatalog;
	
	/** TABLE_SCHEMA */
	private String tableSchema;
	
	/** 表名 TABLE_NAME */
	private String tableName;
	
	/** 字段名 COLUMN_NAME */
	private String columnName;

	/** ORDINAL_POSITION */
	private Integer ordinalPosition;
	
	/** COLUMN_DEFAULT */
	private String columnDefault;
	
	/** 是否允许空值 IS_NULLABLE */
	private boolean isNullable = true;

	/** DATA_TYPE */
	private DataType dataType;

	/** 数据类型 MySQL columns表字段 */
	private String columnType;
	
	/** 字段注释 COLUMN_COMMENT */
	private String columnComment;

	/** CHARACTER_MAXIMUM_LENGTH */
	private Long characterMaximumLength;
	
	/** CHARACTER_OCTET_LENGTH */
	private Long characterOctetLength;
	
	/** NUMERIC_PRECISION */
	private Integer numericPrecision;
	
	/** NUMERIC_SCALE */
	private Integer numericScale;
	
	/** DATETIME_PRECISION */
	private Integer datetimePrecision;
	
	/** CHARACTER_SET_NAME */
	private String characterSetName;
	
	/** COLLATION_NAME */
	private String collationName;

	/** COLUMN_KEY */
	private String columnKey;
	
	/** EXTRA */
	private String extra;
	
	/** PRIVILEGES */
	private String privileges;
	
	private TreeSet<KeyInfo> keys = new TreeSet<KeyInfo>();
	
	private TreeSet<IndexInfo> indexes = new TreeSet<IndexInfo>();
	
	private boolean unique = false;
	
	private String fieldName;
	
	private String methodName;
	
	public String getAnnotation() {
		return AnnotationUtil.buildColumnAnnotation(this);
	}
	
	public void setIndexes(TreeSet<IndexInfo> indexes) {
		for(IndexInfo index : indexes){
			if(!index.getNonUnique()) this.unique = true;
		}
		this.indexes = indexes;
	}
	
	public TreeSet<IndexInfo> getIndexes() {
		return indexes;
	}
	
	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
	public String getMethodName() {
		return methodName;
	}

	public Set<KeyInfo> getKeys() {
		return keys;
	}

	public void setKeys(TreeSet<KeyInfo> keys) {
		this.keys = keys;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
		this.methodName = NameUtil.getCamelCaseString(columnName);
		this.fieldName = NameUtil.firstToLowwer(methodName);
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public Boolean getIsNullable() {
		return isNullable;
	}

	public void setIsNullable(String isNullable) {
		if(StringUtils.isNotEmpty(isNullable)){
			this.isNullable = isNullable.toUpperCase().equals("YES") ? true : false;
		}
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

	public Integer getOrdinalPosition() {
		return ordinalPosition;
	}

	public void setOrdinalPosition(Integer ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}

	public String getColumnDefault() {
		return columnDefault;
	}

	public void setColumnDefault(String columnDefault) {
		this.columnDefault = columnDefault;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		if(StringUtils.isNotEmpty(dataType)){
			this.dataType = DataType.valueOf(dataType.toUpperCase());
		}
	}

	public Long getCharacterMaximumLength() {
		return characterMaximumLength;
	}

	public void setCharacterMaximumLength(Long characterMaximumLength) {
		this.characterMaximumLength = characterMaximumLength;
	}

	public Long getCharacterOctetLength() {
		return characterOctetLength;
	}

	public void setCharacterOctetLength(Long characterOctetLength) {
		this.characterOctetLength = characterOctetLength;
	}

	public Integer getNumericPrecision() {
		return numericPrecision;
	}

	public void setNumericPrecision(Integer numericPrecision) {
		this.numericPrecision = numericPrecision;
	}

	public Integer getNumericScale() {
		return numericScale;
	}

	public void setNumericScale(Integer numericScale) {
		this.numericScale = numericScale;
	}

	public Integer getDatetimePrecision() {
		return datetimePrecision;
	}

	public void setDatetimePrecision(Integer datetimePrecision) {
		this.datetimePrecision = datetimePrecision;
	}

	public String getCharacterSetName() {
		return characterSetName;
	}

	public void setCharacterSetName(String characterSetName) {
		this.characterSetName = characterSetName;
	}

	public String getCollationName() {
		return collationName;
	}

	public void setCollationName(String collationName) {
		this.collationName = collationName;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getPrivileges() {
		return privileges;
	}

	public void setPrivileges(String privileges) {
		this.privileges = privileges;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((characterMaximumLength == null) ? 0 : characterMaximumLength.hashCode());
		result = prime * result + ((characterOctetLength == null) ? 0 : characterOctetLength.hashCode());
		result = prime * result + ((characterSetName == null) ? 0 : characterSetName.hashCode());
		result = prime * result + ((collationName == null) ? 0 : collationName.hashCode());
		result = prime * result + ((columnComment == null) ? 0 : columnComment.hashCode());
		result = prime * result + ((columnDefault == null) ? 0 : columnDefault.hashCode());
		result = prime * result + ((columnKey == null) ? 0 : columnKey.hashCode());
		result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
		result = prime * result + ((columnType == null) ? 0 : columnType.hashCode());
		result = prime * result + ((dataType == null) ? 0 : dataType.hashCode());
		result = prime * result + ((datetimePrecision == null) ? 0 : datetimePrecision.hashCode());
		result = prime * result + ((extra == null) ? 0 : extra.hashCode());
		result = prime * result + (isNullable ? 1231 : 1237);
		result = prime * result + ((numericPrecision == null) ? 0 : numericPrecision.hashCode());
		result = prime * result + ((numericScale == null) ? 0 : numericScale.hashCode());
		result = prime * result + ((ordinalPosition == null) ? 0 : ordinalPosition.hashCode());
		result = prime * result + ((privileges == null) ? 0 : privileges.hashCode());
		result = prime * result + ((tableCatalog == null) ? 0 : tableCatalog.hashCode());
		result = prime * result + ((tableName == null) ? 0 : tableName.hashCode());
		result = prime * result + ((tableSchema == null) ? 0 : tableSchema.hashCode());
		result = prime * result + (unique ? 1231 : 1237);
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
		ColumnInfo other = (ColumnInfo) obj;
		if (characterMaximumLength == null) {
			if (other.characterMaximumLength != null)
				return false;
		} else if (!characterMaximumLength.equals(other.characterMaximumLength))
			return false;
		if (characterOctetLength == null) {
			if (other.characterOctetLength != null)
				return false;
		} else if (!characterOctetLength.equals(other.characterOctetLength))
			return false;
		if (characterSetName == null) {
			if (other.characterSetName != null)
				return false;
		} else if (!characterSetName.equals(other.characterSetName))
			return false;
		if (collationName == null) {
			if (other.collationName != null)
				return false;
		} else if (!collationName.equals(other.collationName))
			return false;
		if (columnComment == null) {
			if (other.columnComment != null)
				return false;
		} else if (!columnComment.equals(other.columnComment))
			return false;
		if (columnDefault == null) {
			if (other.columnDefault != null)
				return false;
		} else if (!columnDefault.equals(other.columnDefault))
			return false;
		if (columnKey == null) {
			if (other.columnKey != null)
				return false;
		} else if (!columnKey.equals(other.columnKey))
			return false;
		if (columnName == null) {
			if (other.columnName != null)
				return false;
		} else if (!columnName.equals(other.columnName))
			return false;
		if (columnType == null) {
			if (other.columnType != null)
				return false;
		} else if (!columnType.equals(other.columnType))
			return false;
		if (dataType != other.dataType)
			return false;
		if (datetimePrecision == null) {
			if (other.datetimePrecision != null)
				return false;
		} else if (!datetimePrecision.equals(other.datetimePrecision))
			return false;
		if (extra == null) {
			if (other.extra != null)
				return false;
		} else if (!extra.equals(other.extra))
			return false;
		if (isNullable != other.isNullable)
			return false;
		if (numericPrecision == null) {
			if (other.numericPrecision != null)
				return false;
		} else if (!numericPrecision.equals(other.numericPrecision))
			return false;
		if (numericScale == null) {
			if (other.numericScale != null)
				return false;
		} else if (!numericScale.equals(other.numericScale))
			return false;
		if (ordinalPosition == null) {
			if (other.ordinalPosition != null)
				return false;
		} else if (!ordinalPosition.equals(other.ordinalPosition))
			return false;
		if (privileges == null) {
			if (other.privileges != null)
				return false;
		} else if (!privileges.equals(other.privileges))
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
		if (unique != other.unique)
			return false;
		return true;
	}
	
	@Override
	public int compareTo(ColumnInfo another) {
		if(null != another && another.getOrdinalPosition()!=null){
			return ordinalPosition.compareTo(another.getOrdinalPosition());
		}
		return 1;
	}

}
