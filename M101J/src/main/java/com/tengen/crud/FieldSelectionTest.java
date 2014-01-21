package com.tengen.crud;

import java.net.UnknownHostException;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

public class FieldSelectionTest extends AbstractCrudTest {

	public static void main(String[] args) throws UnknownHostException {
		DBCollection collection = getCollection("fieldSelectionTest");
		collection.drop();

		Random rand = new Random();

		for (int i = 0; i < 10; i++) {
			collection.insert(new BasicDBObject("x", rand.nextInt(2)).append(
					"y", rand.nextInt(100)).append("z", rand.nextInt(1000)));
		}

		DBObject query = QueryBuilder.start("x").is(0).and("y").greaterThan(10)
				.lessThan(70).get();

		DBObject fields = new BasicDBObject("y", true).append("_id", false);

		DBCursor cursor = collection.find(query, fields);
		printCollection(cursor);
	}
}
