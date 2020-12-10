package Entity;

import java.io.Serializable;
import java.util.*;

public class MessageBox implements Serializable {
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

    public Set<String> getAllContacts(){
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

    public Set<String> getContacts(String box) {
        if(box.equals("Read")){
            return ReadMessage.keySet();
        } else if (box.equals("Unread")) {
            return UnreadMessage.keySet();
        } else{
            return ArchivedMessage.keySet();
        }
    }

    public void sendMessage(String sender, String message) {
        if (ReadMessage.containsKey(sender)){
            ReadMessage.get(sender).add(message);
        }else{
            ArrayList<String> arr = new ArrayList<>();
            arr.add(message);
            ReadMessage.put(sender,arr);
        }
    }

    public void addTo(String message, String sender, String destination) {
        if (getAllMessage(destination).containsKey(sender)) {
            getMessageFromOne(sender, destination).add(message);
        } else {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(message);
            getAllMessage(destination).put(sender, temp);
        }
    }

    public void removeFrom(String sender, String message, String destination) {
        if (getAllMessage(destination).get(sender).size() == 1) {
            getAllMessage(destination).remove(sender);
        } else {
            getMessageFromOne(sender, destination).remove(message);
        }
    }

}
