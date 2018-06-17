package sankv.kafka.poc.receiver;

import sankv.kafka.poc.model.SimpleMessage;

public class KafkaConsoleMessageReceiver implements KafkaMessageReceiver {

    @Override
    public void receiveMessage(SimpleMessage simpleMessage) {
        System.out.println("Received msg: " + simpleMessage);
    }
}
