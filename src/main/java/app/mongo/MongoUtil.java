package app.mongo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.mongodb.client.FindIterable;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;

public class MongoUtil {

	private static MongoUtil instance;

	private FindOneAndReplaceOptions returnDocAfterReplace = new FindOneAndReplaceOptions()
			.returnDocument(ReturnDocument.AFTER);

	private MongoUtil() {
	}

	public static synchronized MongoUtil getInstance() {
		if (instance == null) {
			synchronized (MongoUtil.class) {
				if (instance == null) {
					instance = new MongoUtil();
				}
			}
		}
		return instance;
	}

	public <T> List<T> getListFromFindIterable(FindIterable<T> listDocuments) {

		List<T> list = new ArrayList<>();

		Consumer<T> findIterableToList = (T result) -> {
			list.add(result);
		};

		listDocuments.forEach(findIterableToList);

		return list;
	}

	public FindOneAndReplaceOptions getReturnDocAfterReplace() {
		return returnDocAfterReplace;
	}

}
