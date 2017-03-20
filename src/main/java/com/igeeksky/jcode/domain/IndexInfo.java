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

/**
 * <b>索引信息</b><br>
 * <b>information_schema.STATISTICS</b><br>
 * @author Tony.Lau
 * @blog: https://my.oschina.net/xcafe
 * @createTime 2017-03-11 12:16:46
 */
public class IndexInfo implements Comparable<IndexInfo> {

	/**  */
	private String tableCatalog;

	/** 模式名 */
	private String tableSchema;

	/** 表名 */
	private String tableName;

	/** 非唯一 */
	private boolean nonUnique = true;

	/**  */
	private String indexSchema;

	/** 索引名 */
	private String indexName;

	/** 索引顺序 */
	private Integer seqInIndex;

	/** 字段名 */
	private String columnName;

	/**  */
	private String collation;

	/**  */
	private Integer cardinality;

	/**  */
	private Integer subPart;

	/**  */
	private String packed;

	/**  */
	private String nullable;

	/** 索引类型 */
	private String indexType;

	/**  */
	private String comment;

	/**  */
	private String indexComment;

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

	public Boolean getNonUnique() {
		return nonUnique;
	}

	public void setNonUnique(Integer nonUnique) {
		if(null != nonUnique && nonUnique <= 1 && nonUnique >= 0){
			this.nonUnique = nonUnique==0 ? false : true;
		}
	}

	public String getIndexSchema() {
		return indexSchema;
	}

	public void setIndexSchema(String indexSchema) {
		this.indexSchema = indexSchema;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public Integer getSeqInIndex() {
		return seqInIndex;
	}

	public void setSeqInIndex(Integer seqInIndex) {
		this.seqInIndex = seqInIndex;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getCollation() {
		return collation;
	}

	public void setCollation(String collation) {
		this.collation = collation;
	}

	public Integer getCardinality() {
		return cardinality;
	}

	public void setCardinality(Integer cardinality) {
		this.cardinality = cardinality;
	}

	public Integer getSubPart() {
		return subPart;
	}

	public void setSubPart(Integer subPart) {
		this.subPart = subPart;
	}

	public String getPacked() {
		return packed;
	}

	public void setPacked(String packed) {
		this.packed = packed;
	}

	public String getNullable() {
		return nullable;
	}

	public void setNullable(String nullable) {
		this.nullable = nullable;
	}

	public String getIndexType() {
		return indexType;
	}

	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getIndexComment() {
		return indexComment;
	}

	public void setIndexComment(String indexComment) {
		this.indexComment = indexComment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardinality == null) ? 0 : cardinality.hashCode());
		result = prime * result + ((collation == null) ? 0 : collation.hashCode());
		result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((indexComment == null) ? 0 : indexComment.hashCode());
		result = prime * result + ((indexName == null) ? 0 : indexName.hashCode());
		result = prime * result + ((indexSchema == null) ? 0 : indexSchema.hashCode());
		result = prime * result + ((indexType == null) ? 0 : indexType.hashCode());
		result = prime * result + (nonUnique ? 1231 : 1237);
		result = prime * result + ((nullable == null) ? 0 : nullable.hashCode());
		result = prime * result + ((packed == null) ? 0 : packed.hashCode());
		result = prime * result + ((seqInIndex == null) ? 0 : seqInIndex.hashCode());
		result = prime * result + ((subPart == null) ? 0 : subPart.hashCode());
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
		IndexInfo other = (IndexInfo) obj;
		if (cardinality == null) {
			if (other.cardinality != null)
				return false;
		} else if (!cardinality.equals(other.cardinality))
			return false;
		if (collation == null) {
			if (other.collation != null)
				return false;
		} else if (!collation.equals(other.collation))
			return false;
		if (columnName == null) {
			if (other.columnName != null)
				return false;
		} else if (!columnName.equals(other.columnName))
			return false;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (indexComment == null) {
			if (other.indexComment != null)
				return false;
		} else if (!indexComment.equals(other.indexComment))
			return false;
		if (indexName == null) {
			if (other.indexName != null)
				return false;
		} else if (!indexName.equals(other.indexName))
			return false;
		if (indexSchema == null) {
			if (other.indexSchema != null)
				return false;
		} else if (!indexSchema.equals(other.indexSchema))
			return false;
		if (indexType == null) {
			if (other.indexType != null)
				return false;
		} else if (!indexType.equals(other.indexType))
			return false;
		if (nonUnique != other.nonUnique)
			return false;
		if (nullable == null) {
			if (other.nullable != null)
				return false;
		} else if (!nullable.equals(other.nullable))
			return false;
		if (packed == null) {
			if (other.packed != null)
				return false;
		} else if (!packed.equals(other.packed))
			return false;
		if (seqInIndex == null) {
			if (other.seqInIndex != null)
				return false;
		} else if (!seqInIndex.equals(other.seqInIndex))
			return false;
		if (subPart == null) {
			if (other.subPart != null)
				return false;
		} else if (!subPart.equals(other.subPart))
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
	public int compareTo(IndexInfo another) {
		if(null != another && another.getSeqInIndex()!=null){
			return seqInIndex.compareTo(another.getSeqInIndex());
		}
		return 1;
	}


}
