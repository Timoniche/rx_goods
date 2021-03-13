package ddulaev.rxgoods.configuration;

import com.mongodb.rx.client.MongoClient;
import com.mongodb.rx.client.MongoClients;
import com.mongodb.rx.client.MongoCollection;
import ddulaev.rxgoods.dao.User;
import org.bson.Document;
import rx.Observable;

public class ReactiveMongoDriver {

    private static MongoClient client = createMongoClient();

    public static void main(String[] args) throws InterruptedException {
        MongoCollection<Document> collection = client.getDatabase("rxtest").getCollection("user");
        Observable<User> user = getAllUsers(collection);
        user.subscribe(ReactiveMongoDriver::getPrintln);

        // Wait for asynchronous mongo request processing
        Thread.sleep(1000);
    }

    private static void getPrintln(User user) {
        System.out.println(user);
    }


    private static Observable<User> getAllUsers(MongoCollection<Document> collection) {
        return collection.find().toObservable().map(User::new);
    }

    private static MongoClient createMongoClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }
}