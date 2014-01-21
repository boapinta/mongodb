package com.tengen.hello;

import static spark.Spark.get;

import java.io.StringWriter;
import java.net.UnknownHostException;

import spark.Request;
import spark.Response;
import spark.Route;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldMongoDBSparkFreemarkerStyle {

	public static void main(String[] args) throws UnknownHostException {
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");

		MongoClient client = new MongoClient("localhost", 27017);

		DB database = client.getDB("course");
		final DBCollection collection = database.getCollection("hello");

		get(new Route("/") {

			@Override
			public Object handle(Request request, Response response) {
				StringWriter writer = new StringWriter();
				try {
					Template helloTemplate = configuration.getTemplate("hello.ftl");

					DBObject document = collection.findOne();

					helloTemplate.process(document, writer);
				} catch (Exception e) {
					halt(500);
					e.printStackTrace();
				}
				return writer;
			}
		});

	}

}
