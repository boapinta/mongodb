package com.tengen.home;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.tengen.crud.AbstractCrudTest;

public class Week2Homework2 extends AbstractCrudTest {
	public static void main(String[] args) throws UnknownHostException {
		DBCollection collection = getCollection("students", "grades");

		DBObject query = QueryBuilder.start("type").is("homework").get();
		DBObject sort = new BasicDBObject("student_id", 1).append("score", 1);

		List<ObjectId> idsToRemove = new ArrayList<ObjectId>();
		DBCursor cursor = collection.find(query).sort(sort);
		try {
			int lastStudentId = -1;
			while (cursor.hasNext()) {
				DBObject document = cursor.next();
				int studentId = (Integer) document.get("student_id");
				if (lastStudentId != studentId) {
					lastStudentId = studentId;
					idsToRemove.add((ObjectId) document.get("_id"));
				}
			}
		} finally {
			cursor.close();
		}

		// for (ObjectId idToRemove : idsToRemove) {
		// collection.remove(new BasicDBObject("_id", idToRemove));
		// }

	}
}
