/*
 * Copyright (C) 2017 alchemystar, Inc. All Rights Reserved.
 */
package com.igeeksky.jcode.generator;

/**
 * VelocityUtil
 *
 * @Author lizhuyang
 */

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

public class VelocityUtil {
	
	private static final VelocityEngine ve = new VelocityEngine();
	
	static{
		ve.setProperty(Velocity.RESOURCE_LOADER, "class");
        ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        ve.init();
	}

    public static String render(String vmpath, VelocityContext context) {
        try {
            Template t = ve.getTemplate(vmpath, "UTF-8");
            StringWriter writer = new StringWriter();
            t.merge(context, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException("模版转化错误!");
        }
    }

}