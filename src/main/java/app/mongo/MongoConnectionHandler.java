package app.mongo;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoConnectionHandler {

	private static MongoConnectionHandler instance;

	private MongoDatabase database;

	private MongoConnectionHandler() {
		init();
	}

	public static synchronized MongoConnectionHandler getInstance() {
		if (instance == null) {
			synchronized (MongoConnectionHandler.class) {
				if (instance == null) {
					instance = new MongoConnectionHandler();
				}
			}
		}
		return instance;
	}

	public void init() {

		ConnectionString connectionString = new ConnectionString("mongodb://root:root@127.0.0.1:27017");

		CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());

		CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
		MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.codecRegistry(codecRegistry).build();

		MongoClient mongoClient = MongoClients.create(clientSettings);

		database = mongoClient.getDatabase("spark");
	}

	public <T> MongoCollection<T> getCollection(Class<T> clazz) {
		return (MongoCollection<T>) database.getCollection(clazz.getSimpleName(), clazz);
	}
}
