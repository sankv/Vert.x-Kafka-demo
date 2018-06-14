package sankv.kafka.poc.verticles;

import com.typesafe.config.Config;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import sankv.kafka.poc.bus.Messages;
import sankv.kafka.poc.constants.Vertx;

public class HttpServerVerticle extends AbstractVerticle {

    private Config config;

    private EventBus eventBus;

    public HttpServerVerticle(Config config) {
        this.config = config;
    }

    @Override
    public void start() {
        initHttpServer();
        initEventBus();
    }

    private void initHttpServer() {
        HttpServer httpServer = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.post("/message").handler(BodyHandler.create());
        router.post("/message").handler(rc -> {
            String body = rc.getBodyAsString();

            eventBus.send(Messages.KAFKA_SEND_MSG, body);

            rc.response().end("Message sent");
        });

        httpServer.requestHandler(router::accept);

        httpServer.listen(config.getInt(Vertx.PORT));
    }

    private void initEventBus() {
        eventBus = vertx.eventBus();
    }
}
