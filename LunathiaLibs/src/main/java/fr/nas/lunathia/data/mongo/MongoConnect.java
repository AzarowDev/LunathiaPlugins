package fr.nas.lunathia.data.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.List;

public class MongoConnect {

    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoConnect() {
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .codecRegistry(CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder()
                        .automatic(true)
                        .conventions(List.of(Conventions.ANNOTATION_CONVENTION)).build()))
                ).applyToSslSettings(builder -> builder.enabled(false)
                ).uuidRepresentation(UuidRepresentation.STANDARD).applyConnectionString(new ConnectionString("mongodb://localhost:27017")).build();
        this.mongoClient = MongoClients.create(mongoClientSettings);
        this.database = mongoClient.getDatabase("Lunathia");
    }

    public <TDocument> MongoCollection<TDocument> getCollection(String collectionName, Class<TDocument> documentClass) {
        return this.database.getCollection(collectionName, documentClass);
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
