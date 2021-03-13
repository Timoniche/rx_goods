package ddulaev.rxgoods.server;

import io.reactivex.netty.protocol.http.server.HttpServer;
import rx.Observable;

public class RxNettyServer {

    public static void main(final String[] args) {
        HttpServer
                .newServer(8080)
                .start((req, resp) -> {

                    String name = req.getDecodedPath().substring(1);
                    Observable<String> response = Observable
                            .just(name)
                            .map(usd -> "Hello, " + name + "!");

                    return resp.writeString(response);
                })
                .awaitShutdown();
    }
}