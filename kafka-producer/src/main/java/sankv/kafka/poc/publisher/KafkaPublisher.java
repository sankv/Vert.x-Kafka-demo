package sankv.kafka.poc.publisher;

import sankv.kafka.poc.model.SimpleMessage;

public interface KafkaPublisher {

    void publish(SimpleMessage simpleMessage);

}
