package sankv.kafka.poc;

import com.typesafe.config.Config;
import io.vertx.core.Vertx;
import io.vertx.kafka.client.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import sankv.kafka.poc.config.Configuration;
import sankv.kafka.poc.receiver.KafkaConsoleMessageReceiver;
import sankv.kafka.poc.verticles.KafkaConsumerVerticle;

import java.util.Properties;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

public class Consumer {

    private Config kafkaConfig;

    private Vertx vertx;

    public Consumer(Config kafkaConfig, Vertx vertx) {
        this.kafkaConfig = kafkaConfig;
        this.vertx = vertx;
    }

    public static void main(String[] args) {
        Consumer consumer = new Consumer(Configuration.kafka(), Vertx.vertx());

        consumer.start();
    }

    private void start() {
        vertx.deployVerticle(new KafkaConsumerVerticle(createKafkaConsumer(), new KafkaConsoleMessageReceiver()));
    }

    private KafkaConsumer<String, String> createKafkaConsumer() {
        Properties config = new Properties();
        config.put(BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getString(BOOTSTRAP_SERVERS_CONFIG));
        config.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(GROUP_ID_CONFIG, kafkaConfig.getString(GROUP_ID_CONFIG));
        config.put(AUTO_OFFSET_RESET_CONFIG, kafkaConfig.getString(AUTO_OFFSET_RESET_CONFIG));
        config.put(ENABLE_AUTO_COMMIT_CONFIG, kafkaConfig.getString(ENABLE_AUTO_COMMIT_CONFIG));

        return KafkaConsumer.create(vertx, config);
    }

}
