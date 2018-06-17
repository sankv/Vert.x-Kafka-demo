package sankv.kafka.poc.receiver;

import sankv.kafka.poc.model.SimpleMessage;

public interface KafkaMessageReceiver {

    void receiveMessage(SimpleMessage simpleMessage);

}
