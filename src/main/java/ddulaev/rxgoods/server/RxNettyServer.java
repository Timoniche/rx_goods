package ddulaev.rxgoods.server;

import java.util.List;
import java.util.Map;

import com.mongodb.rx.client.Success;
import ddulaev.rxgoods.configuration.ReactiveMongoDriver;
import ddulaev.rxgoods.dao.Currency;
import ddulaev.rxgoods.dao.User;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.netty.protocol.http.server.HttpServer;
import rx.Observable;

public class RxNettyServer {

    public void run() {
        HttpServer
                .newServer(8080)
                .start((req, resp) -> {
                    Observable<String> response;
                    String command = req.getDecodedPath().substring(1);
                    var paramsMap = req.getQueryParameters();
                    switch (command) {
                        case "addUser":
                            response = addUser(paramsMap);
                            resp.setStatus(HttpResponseStatus.OK);
                            break;
                        default:
                            response = Observable.just("Request command is not valid");
                            resp.setStatus(HttpResponseStatus.BAD_REQUEST);
                            break;
                    }
                    return resp.writeString(response);
                })
                .awaitShutdown();
    }

    public static void main(final String[] args) {
        new RxNettyServer().run();
    }

    private Observable<String> addUser(Map<String, List<String>> paramsMap) {
        int id = Integer.parseInt(paramsMap.get("id").get(0));
        String name = paramsMap.get("name").get(0);
        Currency currency = Currency.valueOf(paramsMap.get("currency").get(0));
        if (ReactiveMongoDriver.addUser(new User(id, name, currency)) == Success.SUCCESS) {
            return Observable.just("Success");
        } else {
            return Observable.just("Error");
        }
    }
}