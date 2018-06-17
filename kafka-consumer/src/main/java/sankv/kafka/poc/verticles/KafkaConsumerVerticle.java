package sankv.kafka.poc.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.Json;
import io.vertx.kafka.client.consumer.KafkaConsumer;
import sankv.kafka.poc.model.SimpleMessage;
import sankv.kafka.poc.receiver.KafkaMessageReceiver;

public class KafkaConsumerVerticle extends AbstractVerticle {

    private KafkaConsumer<String, String> kafkaConsumer;

    private KafkaMessageReceiver kafkaMessageReceiver;

    public KafkaConsumerVerticle(KafkaConsumer<String, String> kafkaConsumer,
                                 KafkaMessageReceiver kafkaMessageReceiver) {
        this.kafkaConsumer = kafkaConsumer;
        this.kafkaMessageReceiver = kafkaMessageReceiver;
    }

    @Override
    public void start() {
        kafkaConsumer.handler(record -> {
            String value = record.value();
            kafkaMessageReceiver.receiveMessage(Json.decodeValue(value, SimpleMessage.class));
        });

        kafkaConsumer.subscribe("simple_messages");
    }
}
