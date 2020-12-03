package Entity;

import java.io.Serializable;
import java.util.*;

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

    public HashMap<String, ArrayList<String>> getAllMessage(String boxType) {
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


    /**
     * Get a list of usernames of the users who had sent message to this user.
     */

    public Set<String> getContacts(){
        Set<String> contacts = ReadMessage.keySet();
        for(String username: UnreadMessage.keySet()) {
            if(!contacts.contains(username)) {
                contacts.add(username);
            }
        }
        for(String username: ArchivedMessage.keySet()) {
            if(!contacts.contains(username)) {
                contacts.add(username);
            }
        }
        return contacts;
    }

    public void addMessage(String sender, String message) {
        if (ReadMessage.containsKey(sender)){
            ReadMessage.get(sender).add(message);
        }else{
            ArrayList<String> arr = new ArrayList<>();
            arr.add(message);
            ReadMessage.put(sender,arr);
        }
    }

}
