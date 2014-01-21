package com.tengen.crud;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class UpdateTest extends AbstractCrudTest {

	public static void main(String[] args) throws UnknownHostException {
		DBCollection collection = getCollection("updateTest");
		collection.drop();

		List<String> names = Arrays.asList("alice", "bobby", "cathy", "david",
				"ethan");
		for (String name : names) {
			collection.insert(new BasicDBObject("_id", name));
		}

		System.out.println("Before everything");
		printCollection(collection);

		// update
		DBObject query = new BasicDBObject("_id", "alice");
		DBObject update = new BasicDBObject("$set",
				new BasicDBObject("age", 24));
		collection.update(query, update);

		update = new BasicDBObject("$set", new BasicDBObject("gender", "F"));
		collection.update(query, update);

		System.out.println("\nAfter update");
		printCollection(collection);

		// upsert
		query = new BasicDBObject("_id", "frank");
		update = new BasicDBObject("$set", new BasicDBObject("gender", "F"));
		collection.update(query, update, true, false);

		System.out.println("\nAfter upsert");
		printCollection(collection);

		// multi
		query = new BasicDBObject();
		update = new BasicDBObject("$set", new BasicDBObject("title", "Dr"));
		collection.update(query, update, false, true);

		System.out.println("\nAfter multi");
		printCollection(collection);

		// remove
		query = new BasicDBObject("_id", "alice");
		collection.remove(query);

		System.out.println("\nAfter remove");
		printCollection(collection);
	}

}
