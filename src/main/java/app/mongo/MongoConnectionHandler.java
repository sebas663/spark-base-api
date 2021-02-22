/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
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

/**
 * 
 * @author Sebas663
 *
 */
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

		String connection = System.getenv("MONGO_CONNECTION_STRING");

		String databaseName = System.getenv("MONGO_DB");

		ConnectionString connectionString = new ConnectionString(connection);

		CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());

		CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
		MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.codecRegistry(codecRegistry).build();

		MongoClient mongoClient = MongoClients.create(clientSettings);

		database = mongoClient.getDatabase(databaseName);
	}

	public <T> MongoCollection<T> getCollection(Class<T> clazz) {
		return (MongoCollection<T>) database.getCollection(clazz.getSimpleName(), clazz);
	}
}
