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

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeSet;

import com.igeeksky.jcode.domain.ColumnInfo;
import com.igeeksky.jcode.domain.IndexInfo;
import com.igeeksky.jcode.domain.KeyInfo;
import com.igeeksky.jcode.domain.TableInfo;

/**
 * <b></b><br>
 * @author Tony.Lau
 * @blog: https://my.oschina.net/xcafe
 * @createTime 2017-03-15 04:37:35
 */

public class AnnotationUtil {
	
	public static String buildTableAnnotation(TableInfo table) {
		StringBuilder builder = new StringBuilder("@Table(name=\"");
		builder.append(table.getTableName()).append("\"");
		builder.append(", catalog=\"").append(table.getTableCatalog()).append("\"");
		builder.append(", schema=\"").append(table.getTableSchema()).append("\"");
		
		boolean isAppendIndex = false;
		StringBuilder indexStr = new StringBuilder("indexes={");
		StringBuilder uniqueStr = new StringBuilder("uniqueConstraints={");
		if(table.getIndexes().size()>1){
			isAppendIndex = true;
		}
		
		Iterator<Entry<String, TreeSet<IndexInfo>>> entryIt = table.getIndexes().entrySet().iterator();
		while(entryIt.hasNext()){
			Entry<String, TreeSet<IndexInfo>> entry = entryIt.next();
			String indexName = entry.getKey();
			
			String indexAno = "@Index(name=\"##INDEX_NAME##\",columnList=(\"##COLUMN_LIST##\"),unique=##UNIQUE##)";
			String uniqueAno = "@UniqueConstraint(columnNames = {##COLUMN_NAMES##},name=\"##NAME##\")";
			indexAno = indexAno.replace("##INDEX_NAME##", indexName);
			uniqueAno = uniqueAno.replace("##NAME##", indexName);
			
			TreeSet<IndexInfo> set = entry.getValue();
			if(indexName.equals("PRIMARY") && set.size()>1){
				isAppendIndex = true;
			}
			Iterator<IndexInfo> it = set.iterator();
			StringBuilder columnList = new StringBuilder();
			StringBuilder columnNames = new StringBuilder();
			boolean isUnique = false;
			while(it.hasNext()){
				IndexInfo index = it.next();
				String columnName = index.getColumnName();
				columnList = columnList.append(columnName);
				columnNames.append("\"").append(columnName).append("\"");
				if(!index.getNonUnique()){
					isUnique = true;
				}
				if(it.hasNext()){
					columnList.append(", ");
					columnNames.append(", ");
				}
			}
			indexAno = indexAno.replace("##COLUMN_LIST##", columnList);
			uniqueAno = uniqueAno.replace("##COLUMN_NAMES##", columnNames);
			
			if(isUnique){
				indexAno = indexAno.replace("##UNIQUE##", "true");
			}else{
				indexAno = indexAno.replace("##UNIQUE##", "false");
			}
			
			indexStr.append(indexAno);
			uniqueStr.append(uniqueAno);
			
			if(entryIt.hasNext()){
				indexStr.append(",\n	");
				uniqueStr.append(",\n	");
			}else{
				indexStr.append("}");
				uniqueStr.append("}");
			}
		}
		if(isAppendIndex){
			builder.append(",\n").append(indexStr).append(",\n").append(uniqueStr);
		}
		return builder.append(")").toString();
	}
	
	public static String buildColumnAnnotation(ColumnInfo column) {
		StringBuilder builder = new StringBuilder();
		for(KeyInfo key : column.getKeys()){
			String keyAnno = key.getAnnotation();
			builder.append(keyAnno).append("\n	");
		}
		builder.append("@Column(name=\"").append(column.getColumnName()).append("\"");
		if(!column.getIsNullable()){
			builder.append(", nullable=false");
		}
		if(column.isUnique()){
			builder.append(", unique=true");
		}
		String javaType = column.getDataType().getJavaType();
		if(javaType.equals("String")){
			builder.append(", length=").append(column.getCharacterMaximumLength());
		}else if(javaType.equals("Double") || javaType.equals("Float") || javaType.equals("BigDecimal")){
			builder.append(", precision=").append(column.getNumericPrecision()).append("scale=").append(column.getNumericScale());
		}
		if(column.getFieldName().equals("createTime")){
			builder.append(", updatable=false");
		}
		if(column.getFieldName().equals("updateTime")){
			builder.append(", insertable=false");
		}
		return builder.append(")").toString();
	}
	
	public static String buildKeyAnnotation(KeyInfo key){
		StringBuilder builder = new StringBuilder();
		if(key.getConstraintName().equals("PRIMARY")){
			builder.append("@Id");
		}
		/*
		builder.append("\n").append("@Key(name=\"").append(constraintName).append("\", sequence=").append(ordinalPosition);
		if (StringUtils.isNotEmpty(referencedTableSchema)) {
			builder.append(", refSchema=").append(referencedTableSchema);
		}
		if (StringUtils.isNotEmpty(referencedTableName)) {
			builder.append(", refTable=").append(referencedTableName);
		}
		if (StringUtils.isNotEmpty(referencedColumnName)) {
			builder.append(", refColumn=").append(referencedColumnName);
		}
		builder.append(")");*/
		return builder.toString();
	}

}
