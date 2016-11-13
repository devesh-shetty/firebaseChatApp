package chat.test.com.ping.model;

import java.util.HashMap;

/**
 * A POJO for model User
 * @author Devesh Shetty
 */
public class PingUser {

    private String userId;
    private String username;
    private String emailId;
    private HashMap<String, Object> friends;


    private PingUser(){

    }

    private PingUser(String userId, String username, String emailId, HashMap<String, Object> friends) {
        this.userId = userId;
        this.username = username;
        this.emailId = emailId;
        this.friends = friends;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmailId() {
        return emailId;
    }

    public HashMap<String, Object> getFriends() {
        return friends;
    }

    /**
     * A builder class to build a {@link PingUser} instance
     */
    public static class Builder{

        //required fields
        private String userId;
        private String username;
        private String emailId;

        //optional fields
        private HashMap<String, Object> friends = null;

        public Builder(){

        }

        public Builder setUserId(String userId) {

            if(userId == null || userId.length() == 0)
            {
                throw new IllegalArgumentException("UserId cannot be null and userId cannot be of length 0");
            }

            this.userId = userId;
            return this;
        }

        public Builder setUsername(String username) {

            if(username == null || username.length() == 0)
            {
                throw new IllegalArgumentException("Username cannot be null and username cannot be of length 0");
            }

            this.username = username;
            return this;
        }

        public Builder setEmailId(String emailId) {

            if(emailId == null || emailId.length() == 0)
            {
                throw new IllegalArgumentException("EmailId cannot be null or please enter a valid email id");
            }

            this.emailId = emailId;
            return this;
        }

        public Builder setFriends(HashMap<String, Object> friends) {
            this.friends = friends;
            return this;
        }

        public PingUser build(){

            if(userId == null || username == null || emailId == null)
            {
                throw new IllegalArgumentException("userId, emailId and username need to be set before calling build");
            }

            return new PingUser(userId, username, emailId, friends);
        }

    }

}


