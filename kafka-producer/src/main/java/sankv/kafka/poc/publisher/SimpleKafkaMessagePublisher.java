package sankv.kafka.poc.publisher;

import io.vertx.core.json.Json;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.kafka.client.producer.KafkaProducerRecord;
import sankv.kafka.poc.model.SimpleMessage;

public class SimpleKafkaMessagePublisher implements KafkaMessagePublisher {

    private final String topic;

    private KafkaProducer<String, String> kafkaProducer;

    public SimpleKafkaMessagePublisher(String topic, KafkaProducer<String, String> kafkaProducer) {
        this.topic = topic;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void publish(SimpleMessage simpleMessage) {
        kafkaProducer.write(KafkaProducerRecord.create(topic, Json.encode(simpleMessage)));
    }
}
