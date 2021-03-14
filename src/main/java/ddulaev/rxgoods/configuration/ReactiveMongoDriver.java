package ddulaev.rxgoods.configuration;

import java.util.concurrent.TimeUnit;

import com.mongodb.rx.client.MongoClient;
import com.mongodb.rx.client.MongoClients;
import com.mongodb.rx.client.Success;
import ddulaev.rxgoods.dao.Currency;
import ddulaev.rxgoods.dao.Product;
import ddulaev.rxgoods.dao.User;
import rx.Observable;

import static com.mongodb.client.model.Filters.eq;


public class ReactiveMongoDriver {
    private final static int TIMEOUT_SEC = 5;
    private static final MongoClient client = createMongoClient();

    public static Success addUser(User user) {
        return client
                .getDatabase("rxtest")
                .getCollection("users")
                .insertOne(user.getDocument())
                .timeout(TIMEOUT_SEC, TimeUnit.SECONDS)
                .toBlocking()
                .single();
    }

    public static Success addProduct(Product product) {
        return client
                .getDatabase("rxtest")
                .getCollection("products")
                .insertOne(product.getDocument())
                .timeout(TIMEOUT_SEC, TimeUnit.SECONDS)
                .toBlocking()
                .single();
    }

    public static Observable<String> getProducts(int id) {
        User user = getUser(id);
        Currency currency = user.getCurrency();
        return client
                .getDatabase("rxtest")
                .getCollection("products")
                .find()
                .toObservable()
                .map(Product::new)
                .map(p -> p.toString() + " " + p.getCostWithCurrency(currency) + currency.name())
                .reduce((s1, s2) -> s1 + System.lineSeparator() + s2);

    }

    public static User getUser(int id) {
        return client
                .getDatabase("rxtest")
                .getCollection("users")
                .find(eq("id", id))
                .first()
                .map(User::new)
                .timeout(TIMEOUT_SEC, TimeUnit.SECONDS)
                .toBlocking()
                .single();
    }

    private static MongoClient createMongoClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }
}