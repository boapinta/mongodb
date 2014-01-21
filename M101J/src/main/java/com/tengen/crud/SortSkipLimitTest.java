package com.tengen.crud;

import java.net.UnknownHostException;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class SortSkipLimitTest extends AbstractCrudTest {

	public static void main(String[] args) throws UnknownHostException {
		DBCollection collection = getCollection("sortSkipLimitTest");
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

		DBCursor cursor = collection.find();
		cursor.sort(new BasicDBObject("start.x", 1).append("start.y", -1));
		cursor.skip(0);
		cursor.limit(5);
		printCollection(cursor);
	}
}
