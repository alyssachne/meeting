package Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

//Todo: Implement Observer design pattern for message system

public class MessageBox extends Observable implements Serializable {
    public String user;
    public HashMap<String, ArrayList<String>> ReadMessage;
    public HashMap<String, ArrayList<String>> UnreadMessage;
    public HashMap<String, ArrayList<String>> ArchivedMessage;

    public MessageBox(String username) {
        user = username;
        ReadMessage = new HashMap<>();
        UnreadMessage = new HashMap<>();
        ArchivedMessage = new HashMap<>();
    }

    public String getUser() {
        return user;
    }

    public HashMap<String, ArrayList<String>> getAllReadMessage() {
        return ReadMessage;
    }

    public HashMap<String, ArrayList<String>> getUnreadMessage() {
        return UnreadMessage;
    }

    public HashMap<String, ArrayList<String>> getArchivedMessage() {
        return ArchivedMessage;
    }

    public ArrayList<String> getReadMessage_One(String sender) {
        return ReadMessage.get(sender);
    }

    public ArrayList<String> getUnreadMessage_One(String sender) {
        return UnreadMessage.get(sender);
    }

    public ArrayList<String> getArchivedMessage_One(String sender) {
        return ArchivedMessage.get(sender);
    }
}
