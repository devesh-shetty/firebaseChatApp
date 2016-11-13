package chat.test.com.ping.message.fragment;

/**
 * @author Devesh Shetty
 */
public class FriendListItem {

    private String friendName;
    private String friendId;

    public FriendListItem(String friendName, String friendId) {
        this.friendName = friendName;
        this.friendId = friendId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }
}
