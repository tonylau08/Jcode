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

package com.igeeksky.jcode.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igeeksky.jcode.domain.ColumnInfo;
import com.igeeksky.jcode.domain.TableInfo;
import com.igeeksky.jcode.service.CodeService;
import com.igeeksky.jcode.util.IOUtils;

/**
 * <b>说 明：</b>代码生成<br>
 * @author Tony.Lau
 * @blog: https://my.oschina.net/xcafe
 * @createTime 2017-03-11 09:44:12
 */
@RestController
public class CodeController {

	@Autowired
	private CodeService codeService;

	/**
	 * <b>中文名：获取全部数据表</b><br>
	 * <b>说 明：根据配置文件的tableSchema获取全部表</b>
	 * @return
	 */
	@RequestMapping("/getAllTables.do")
	public List<TableInfo> getAllTables(String tableSchema) {
		List<TableInfo> list = codeService.getAllTables(tableSchema);
		System.out.println(tableSchema);
		System.out.println(list.toString());
		return list;
	}

	/**
	 * <b>中文名：获取全部字段</b><br>
	 * <b>说 明：根据表名获取全部字段</b>
	 * 
	 * @param tableName
	 * @return
	 */
	@RequestMapping("/getColumnsByTable.do")
	public List<ColumnInfo> getColumns(String tableSchema, String tableName) {
		if (StringUtils.isNotEmpty(tableName)) {
			return codeService.getColumnsByTable(tableSchema, tableName);
		}
		return Collections.emptyList();
	}

	@RequestMapping("/genCode.do")
	public void genCode(HttpServletResponse response, String tableSchema, String[] tableNames) {
		IOUtils.write(response , codeService.genCode(tableSchema, tableNames));
	}
	
}
