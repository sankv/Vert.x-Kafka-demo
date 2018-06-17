package sankv.kafka.poc.verticles;

import com.typesafe.config.Config;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import sankv.kafka.poc.bus.Messages;
import sankv.kafka.poc.model.SimpleMessage;
import sankv.kafka.poc.publisher.KafkaMessagePublisher;

public class KafkaPublisherVerticle extends AbstractVerticle {

    private Config config;

    private KafkaMessagePublisher kafkaPublisher;

    private EventBus eventBus;

    public KafkaPublisherVerticle(Config config, KafkaMessagePublisher kafkaPublisher) {
        this.config = config;
        this.kafkaPublisher = kafkaPublisher;
    }

    @Override
    public void start() {
        eventBus = vertx.eventBus();

        handleMsg();
    }

    private void handleMsg() {
        eventBus.consumer(Messages.KAFKA_SEND_MSG, (Message<String> msg) -> {
            String body = msg.body();

            SimpleMessage simpleMessage = Json.decodeValue(body, SimpleMessage.class);

            kafkaPublisher.publish(simpleMessage);
        });
    }
}
