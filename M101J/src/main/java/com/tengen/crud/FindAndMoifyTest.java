package com.tengen.crud;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class FindAndMoifyTest extends AbstractCrudTest {

	public static void main(String[] args) throws UnknownHostException {
		DBCollection collection = getCollection("findAndModifyTest");
		collection.drop();

		String counterId = "abc";
		int first;
		int numNeeded;

		numNeeded = 2;
		first = getRange(counterId, numNeeded, collection);
		System.out.println("Range: " + first + "-" + (first + numNeeded - 1));

		numNeeded = 3;
		first = getRange(counterId, numNeeded, collection);
		System.out.println("Range: " + first + "-" + (first + numNeeded - 1));

		numNeeded = 10;
		first = getRange(counterId, numNeeded, collection);
		System.out.println("Range: " + first + "-" + (first + numNeeded - 1));
	}

	private static int getRange(String id, int range, DBCollection collection) {
		DBObject query = new BasicDBObject("_id", id);
		DBObject update = new BasicDBObject("$inc", new BasicDBObject(
				"counter", range));
		DBObject doc = collection.findAndModify(query, null, null, false,
				update, true, true);
		return (Integer) doc.get("counter") - range + 1;
	}
}
