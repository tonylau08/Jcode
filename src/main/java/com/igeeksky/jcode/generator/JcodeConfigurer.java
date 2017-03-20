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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.SpringProperties;

/**
 * <b></b><br>
 * @author Tony.Lau
 * @blog: https://my.oschina.net/xcafe
 * @createTime 2017-03-15 07:36:43
 */
public class JcodeConfigurer {

	private static final String PROPERTIES_RESOURCE_LOCATION = "jcode.properties";

	private static final Log logger = LogFactory.getLog(SpringProperties.class);

	private static final Properties LOCAL_PROPERTIES = new Properties();

	static {
		try {
			ClassLoader cl = JcodeConfigurer.class.getClassLoader();
			URL url = (cl != null ? cl.getResource(PROPERTIES_RESOURCE_LOCATION)
					: ClassLoader.getSystemResource(PROPERTIES_RESOURCE_LOCATION));
			if (url != null) {
				logger.info("Found 'jcode.properties' file in local classpath");
				InputStream is = url.openStream();
				try {
					LOCAL_PROPERTIES.load(is);
				} finally {
					is.close();
				}
			}
		} catch (IOException e) {
			logger.error("Could not load 'spring.properties' file from local classpath: " + e.getMessage(), e);
		}
	}

	public static void setProperty(String key, String value) {
		if (value != null) {
			LOCAL_PROPERTIES.setProperty(key, value);
		} else {
			LOCAL_PROPERTIES.remove(key);
		}
	}

	public static String getProperty(String key) {
		String value = LOCAL_PROPERTIES.getProperty(key);
		if (value == null) {
			try {
				value = System.getProperty(key);
			} catch (Throwable e) {
				logger.error("Could not retrieve system property '" + key + "': " + e.getMessage(), e);
			}
		}
		return value;
	}
	
	public static Properties getProperties(){
		return LOCAL_PROPERTIES;
	}

}
