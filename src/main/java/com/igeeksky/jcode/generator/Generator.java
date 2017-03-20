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

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.velocity.VelocityContext;

import com.igeeksky.jcode.dao.BaseDao;
import com.igeeksky.jcode.dao.CodeDaoImpl;
import com.igeeksky.jcode.domain.TableInfo;
import com.igeeksky.jcode.service.CodeServiceImpl;
import com.igeeksky.jcode.util.DateUtils;
import com.igeeksky.jcode.util.IOUtils;

/**
 * <b></b><br>
 * 
 * @author Tony.Lau
 * @blog: https://my.oschina.net/xcafe
 * @createTime 2017-03-15 06:18:53
 */
public class Generator {

	public static void main(String[] args) {
		BaseDao baseDao = new BaseDao();
		baseDao.setUseConnectionPool(false);
		CodeDaoImpl codeDao = new CodeDaoImpl(baseDao);
		CodeServiceImpl service = new CodeServiceImpl(codeDao);
		byte[] bytes = service.genCode("test", new String[]{"blog", "city", "country"});

		IOUtils.bytesToFile(bytes, "d:/jcode.zip");
	}

	public static void buildCode(TableInfo table, ZipOutputStream zip) {
		String packageName = JcodeConfigurer.getProperty("jcode.package") + ".";
		VelocityContext context = new VelocityContext();
		context.put("table", table);
		context.put("package", packageName);
		context.put("author", JcodeConfigurer.getProperty("jcode.author"));
		context.put("email", JcodeConfigurer.getProperty("jcode.email"));
		context.put("createTime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));

		Set<String> set = getTemplateSet();
		for (String tplName : set) {
			String result = VelocityUtil.render("template/" + tplName, context);
			String fileName = getFileName(packageName, table.getClassName(), tplName);
			try {
				zip.putNextEntry(new ZipEntry(fileName));
				byte[] bytes = result.getBytes();
				zip.write(bytes, 0, bytes.length);
				zip.closeEntry();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getFileName(String packageName, String className, String tplName) {
		StringBuilder builder = new StringBuilder();
		String[] array = tplName.split("\\.");
		String suffix = array[1].toLowerCase();
		
		if(suffix.equals("html")){
			builder.append("static").append(File.separator).append("html").append(File.separator).append(NameUtil.firstToLowwer(className));
		}else if(suffix.equals("js")){
			builder.append("static").append(File.separator).append("js").append(File.separator).append(NameUtil.firstToLowwer(className));
		}else{
			builder.append(packageName.replace(".", File.separator));
			String basePath = array[0].toLowerCase();
			if(basePath.endsWith("impl")){
				basePath = basePath.substring(0, basePath.length()-4) + File.separator + "impl";
			}
			builder.append(basePath).append(File.separator).append(className);
		}
		
		return builder.append(array[0]).append(".").append(array[1]).toString();
	}

	public static Set<String> getTemplateSet() {
		Set<String> set = new HashSet<String>();
		Properties properties = JcodeConfigurer.getProperties();
		Set<Object> keySet = properties.keySet();
		for (Object obj : keySet) {
			String key = obj.toString();
			if (key.startsWith("jcode.tpl.")) {
				set.add(properties.getProperty(key));
			}
		}
		return set;
	}

}
