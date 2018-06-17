package sankv.kafka.poc.publisher;

import sankv.kafka.poc.model.SimpleMessage;

public interface KafkaMessagePublisher {

    void publish(SimpleMessage simpleMessage);

}
