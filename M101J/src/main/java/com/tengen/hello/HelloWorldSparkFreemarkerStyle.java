package com.tengen.hello;

import static spark.Spark.get;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldSparkFreemarkerStyle {

	public static void main(String[] args) {
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");

		get(new Route("/") {

			@Override
			public Object handle(Request request, Response response) {
				StringWriter writer = new StringWriter();
				try {
					Template helloTemplate = configuration.getTemplate("hello.ftl");

					Map<String, Object> helloMap = new HashMap<String, Object>();
					helloMap.put("name", "Freemarker");

					helloTemplate.process(helloMap, writer);
				} catch (Exception e) {
					halt(500);
					e.printStackTrace();
				}
				return writer;
			}
		});

	}

}
