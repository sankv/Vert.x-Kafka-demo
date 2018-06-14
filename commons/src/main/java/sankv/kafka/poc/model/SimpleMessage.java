package sankv.kafka.poc.model;

public class SimpleMessage {

    private String id;

    private String msg;

    public SimpleMessage() {
    }

    public SimpleMessage(String id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public String getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "SimpleMessage{" +
                "id='" + id + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
