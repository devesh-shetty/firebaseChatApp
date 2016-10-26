package chat.test.com.ping.model;

import java.util.HashMap;

/**
 * A POJO for model User
 * @author Devesh Shetty
 */
public class PingUser {

    private String userId;
    private String emailId;
    private HashMap<String, Object> friends;

    public PingUser(String userId, String emailId, HashMap<String, Object> friends) {
        this.userId = userId;
        this.emailId = emailId;
        this.friends = friends;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public HashMap<String, Object> getFriends() {
        return friends;
    }

    public void setFriends(HashMap<String, Object> friends) {
        this.friends = friends;
    }
}
