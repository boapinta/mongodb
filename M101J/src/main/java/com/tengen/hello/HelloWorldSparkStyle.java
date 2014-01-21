package com.tengen.hello;

import static spark.Spark.get;
import spark.Request;
import spark.Response;
import spark.Route;

public class HelloWorldSparkStyle {

	public static void main(String[] args) {

		get(new Route("/hello") {

			@Override
			public Object handle(Request request, Response response) {
				return "Hello World!";
			}
		});

	}

}
