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

package com.igeeksky.jcode.util;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

/**
 * <b></b><br>
 * @author Tony.Lau
 * @blog: https://my.oschina.net/xcafe
 * @createTime 2017-03-15 09:00:40
 */

public class IOUtils {
	
	public static void bytesToFile(byte[] bytes, String fileName){
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			file = new File(fileName);
			String filePathName = file.getParent();
			File filePath = new File(filePathName);
			if(!filePath.exists()){
				filePath.mkdirs();
			}
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			release(bos, fos);
		}
	}
	
	public static void release(Closeable... closes){
		try{
			for(Closeable close : closes){
				if(null != close) close.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void write(HttpServletResponse response, byte[] data) {
		if(null == data){
			return;
		}
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"jcode.zip\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			os.write(data);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			release(os);
		}
	}

}
