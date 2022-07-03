package fr.nas.lunathia.module;

import com.mongodb.client.MongoCollection;
import fr.nas.lunathia.LunathiaLibs;
import fr.nas.lunathia.data.mongo.MongoConnect;

public class MongoModule<TDocument> {

    private LunathiaLibs plugin;
    private MongoCollection<TDocument> collection;

    protected MongoModule(String collectionName, Class<TDocument> documentClass) {
        this.collection = LunathiaLibs.getInstance().getMongoConnect().getCollection(collectionName, documentClass);
    }

    public MongoCollection<TDocument> getCollection() {
        return collection;
    }
}
