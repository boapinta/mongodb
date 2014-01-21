package com.tengen.crud;

import java.net.UnknownHostException;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

public class DotNotationTest extends AbstractCrudTest {

	public static void main(String[] args) throws UnknownHostException {
		DBCollection collection = getCollection("dotNotationTest");
		collection.drop();

		Random rand = new Random();

		for (int i = 0; i < 10; i++) {
			collection.insert(new BasicDBObject("_id", i).append(
					"start",
					new BasicDBObject("x", rand.nextInt(90) + 10).append("y",
							rand.nextInt(90) + 10)).append(
					"end",
					new BasicDBObject("x", rand.nextInt(90) + 10).append("y",
							rand.nextInt(90) + 10)));

		}

		DBObject query = QueryBuilder.start("start.x").greaterThan(50).get();

		DBObject fields = new BasicDBObject("start.y", true).append("_id",
				false);

		DBCursor cursor = collection.find(query, fields);
		printCollection(cursor);
	}
}
