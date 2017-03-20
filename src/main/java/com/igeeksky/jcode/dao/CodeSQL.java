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

/**
 * <b></b><br>
 * @author Tony.Lau
 * @blog: https://my.oschina.net/xcafe
 * @createTime 2017-03-15 00:32:31
 */

public class CodeSQL {
	
	public static final String MYSQL_TABLE = "TABLE_CATALOG as tableCatalog,"
			+ "t.TABLE_SCHEMA as tableSchema,"
			+ "t.TABLE_NAME as tableName,"
			+ "t.TABLE_TYPE as tableType,"
			+ "t.ENGINE as engine,"
			+ "t.VERSION as version,"
			+ "t.ROW_FORMAT as rowFormat,"
			+ "t.TABLE_ROWS as tableRows,"
			+ "t.AVG_ROW_LENGTH as avgRowLength,"
			+ "t.DATA_LENGTH as dataLength,"
			+ "t.MAX_DATA_LENGTH as maxDataLength,"
			+ "t.INDEX_LENGTH as indexLength,"
			+ "t.DATA_FREE as dataFree,"
			+ "t.AUTO_INCREMENT as autoIncrement,"
			+ "t.CREATE_TIME as createTime,"
			+ "t.UPDATE_TIME as updateTime,"
			+ "t.CHECK_TIME as checkTime,"
			+ "t.TABLE_COLLATION as tableCollation,"
			+ "t.CHECKSUM as checksum,"
			+ "t.CREATE_OPTIONS as createOptions,"
			+ "t.TABLE_COMMENT as tableComment" ;
	
	public static final String MYSQL_COLUMN = "c.TABLE_SCHEMA as tableSchema,"
			+ "c.TABLE_NAME as tableName,"
			+ "c.COLUMN_NAME as columnName,"
			+ "c.ORDINAL_POSITION as ordinalPosition,"
			+ "c.IS_NULLABLE as isNullable,"
			+ "c.DATA_TYPE as dataType,"
			+ "c.CHARACTER_MAXIMUM_LENGTH as characterMaximumLength,"
			+ "c.CHARACTER_OCTET_LENGTH as characterOctetLength,"
			+ "c.NUMERIC_PRECISION as numericPrecision,"
			+ "c.NUMERIC_SCALE as numericScale,"
			+ "c.DATETIME_PRECISION as datetimePrecision,"
			+ "c.CHARACTER_SET_NAME as characterSetName,"
			+ "c.COLLATION_NAME as collationName,"
			+ "c.COLUMN_TYPE as columnType,"
			+ "c.COLUMN_KEY as columnKey,"
			+ "c.EXTRA as extra,"
			+ "c.PRIVILEGES as privileges,"
			+ "c.COLUMN_COMMENT as columnComment";
	
	public static final String MYSQL_INDEX = "s.TABLE_CATALOG as tableCatalog,"
			+ "s.TABLE_SCHEMA as tableSchema,"
			+ "s.TABLE_NAME as tableName,"
			+ "s.NON_UNIQUE as nonUnique,"
			+ "s.INDEX_SCHEMA as indexSchema,"
			+ "s.INDEX_NAME as indexName,"
			+ "s.SEQ_IN_INDEX as seqInIndex,"
			+ "s.COLUMN_NAME as columnName,"
			+ "s.COLLATION as collation,"
			+ "s.CARDINALITY as cardinality,"
			+ "s.SUB_PART as subPart,"
			+ "s.PACKED as packed,"
			+ "s.NULLABLE as nullable,"
			+ "s.INDEX_TYPE as indexType,"
			+ "s.COMMENT as comment,"
			+ "s.INDEX_COMMENT as indexComment";
	
	public static final String MYSQL_KEY = "k.CONSTRAINT_CATALOG as constraintCatalog, "
			+ "k.CONSTRAINT_SCHEMA as constraintSchema, "
			+ "k.CONSTRAINT_NAME as constraintName, "
			+ "k.TABLE_CATALOG as tableCatalog, "
			+ "k.TABLE_SCHEMA as tableSchema, "
			+ "k.TABLE_NAME as tableName, "
			+ "k.COLUMN_NAME as columnName, "
			+ "k.ORDINAL_POSITION as ordinalPosition, "
			+ "k.POSITION_IN_UNIQUE_CONSTRAINT as positionInUniqueConstraint, "
			+ "k.REFERENCED_TABLE_SCHEMA as referencedTableSchema, "
			+ "k.REFERENCED_TABLE_NAME as referencedTableName, "
			+ "k.REFERENCED_COLUMN_NAME as referencedColumnName";
	
	public static final String QUERY_TABLES_BY_SCHEMA = "select " + MYSQL_TABLE + " from information_schema.tables t where t.TABLE_SCHEMA=?";
	
	public static final String QUERY_TABLE = QUERY_TABLES_BY_SCHEMA + " and t.TABLE_NAME=?";
	
	public static final String QUERY_COLUMN_BY_TABLE = "select " + MYSQL_COLUMN + " from information_schema.`COLUMNS` c where c.TABLE_SCHEMA=? and c.TABLE_NAME=?";
	
	public static final String QUERY_INDEX_BY_COLUMN = "select " + MYSQL_INDEX + " from information_schema.STATISTICS s where s.TABLE_SCHEMA=? and s.TABLE_NAME=? and s.COLUMN_NAME=?";
	
	public static final String QUERY_KEY_BY_COLUMN  = "select " + MYSQL_KEY + " from information_schema.key_column_usage k where k.TABLE_SCHEMA=? and k.TABLE_NAME=? and k.COLUMN_NAME=?";
	
}
