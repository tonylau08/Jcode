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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.igeeksky.jcode.generator.JcodeConfigurer;

/**
 * @author Tony.Lau
 * @blog: https://my.oschina.net/xcafe
 * @createTime 2017-03-11 06:08:58
 */
@Repository
public class BaseDao {

	private Log logger = LogFactory.getLog(getClass());
	
	private static final JSONObject EMPTY_JSON = new JSONObject();

	private String driverClassName = JcodeConfigurer.getProperty("jdbc.driverClassName");
	private String url = JcodeConfigurer.getProperty("jdbc.url");
	private String username = JcodeConfigurer.getProperty("jdbc.username");
	private String password = JcodeConfigurer.getProperty("jdbc.password");
	private String tableSchema = JcodeConfigurer.getProperty("jdbc.tableSchema");
	private boolean useConnectionPool = true;
	
	@Autowired
	private DataSource dataSource;

	private Connection getConnection() {
		try {
			if(useConnectionPool){
				return dataSource.getConnection();
			}else{
				Class.forName(driverClassName);
				return DriverManager.getConnection(url, username, password);
			}
		} catch (SQLException e) {
			logger.error("get Connection fail" + e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error("get Connection fail" + e.getMessage(), e);
		}
		return null;
	}

	public List<JSONObject> query(String sql, Object[] params) {
		Connection conn = this.getConnection();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			if (null != params && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}
			rs = stmt.executeQuery();
			return this.getJSONList(rs);
		} catch (SQLException e) {
			logger.error("===query fail===" + sql + "	:	" + e.getMessage(), e);
			return Collections.emptyList();
		} finally {
			this.release(rs, stmt, conn);
		}
	}

	public JSONObject queryOne(String sql, Object[] params) {
		List<JSONObject> list = query(sql, params);
		int size = list.size();
		if(size > 1) throw new IllegalStateException("record size > 1, too many reocrd");
		return size > 0 ? list.get(0) : EMPTY_JSON;
	}

	private List<JSONObject> getJSONList(ResultSet rs) throws SQLException {
		List<JSONObject> list = new ArrayList<JSONObject>();
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		while (rs.next()) {
			JSONObject json = new JSONObject();
			for (int i = 1; i <= count; i++) {
				String columnName = rsmd.getColumnName((i)).toLowerCase();
				json.put(columnName, rs.getObject(i));
			}
			list.add(json);
		}
		return list;
	}

	private void release(ResultSet rs, Statement stmt, Connection conn) {
		this.closeResultSet(rs);
		this.closeStatement(stmt);
		this.closeConnection(conn);
	}

	private void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error("===close resultSet fail==="+ e.getMessage(), e);
			}
			rs = null;
		}
	}

	private void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				logger.error("===close statement fail==="+ e.getMessage(), e);
			}
			stmt = null;
		}
	}

	private void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("===close connection fail==="+ e.getMessage(), e);
			}
		}
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public String getTableSchema() {
		return tableSchema;
	}

	public void setUseConnectionPool(boolean useConnectionPool) {
		this.useConnectionPool = useConnectionPool;
	}

}
