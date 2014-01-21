package com.tengen.crud;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public abstract class AbstractCrudTest {

	protected static DBCollection getCollection(String collectionName)
			throws UnknownHostException {
		return getCollection("course", collectionName);
	}

	protected static DBCollection getCollection(String db, String collectionName)
			throws UnknownHostException {
		MongoClient client = new MongoClient("localhost", 27017);

		DB database = client.getDB(db);
		DBCollection collection = database.getCollection(collectionName);
		return collection;
	}

	protected static void printCollection(DBCollection collection) {
		printCollection(collection.find());
	}

	protected static void printCollection(DBCursor cursor) {
		try {
			while (cursor.hasNext()) {
				DBObject document = cursor.next();
				System.out.println(document);
			}
		} finally {
			cursor.close();
		}
	}
}
