package chat.test.com.ping.model;

/**
 * A POJO for Message Model
 * @author Devesh Shetty
 */
public class Message {

    private String text;
    private long time;
    private String senderId;

    public Message(String text, long time, String senderId) {
        this.text = text;
        this.time = time;
        this.senderId = senderId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
}
