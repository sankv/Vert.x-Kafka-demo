package sankv.kafka.poc.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Configuration {

    private static Config vertx;

    private static Config kafka;

    static {
        vertx = ConfigFactory.load("vertx");
        kafka = ConfigFactory.load("kafka");
    }

    public static Config vertx() {
        return vertx;
    }

    public static Config kafka() {
        return kafka;
    }
}
