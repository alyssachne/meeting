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

    public HashMap<String, ArrayList<String>> getMessage(String boxType) {
        if(boxType == "Read") {
            return ReadMessage;
        } else if(boxType == "Unread") {
            return UnreadMessage;
        } else {
            return ArchivedMessage;
        }
    }

    public ArrayList<String> getMessageFromOne(String sender, String boxType) {
        if(boxType == "Read") {
            return ReadMessage.get(sender);
        } else if(boxType == "Unread") {
            return UnreadMessage.get(sender);
        } else {
            return ArchivedMessage.get(sender);
        }

    }

}
