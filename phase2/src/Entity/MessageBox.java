package Entity;

import java.io.Serializable;
import java.util.*;

public class MessageBox implements Serializable {
    public String user;
    public HashMap<String, ArrayList<String>> ReadMessage;
    public HashMap<String, ArrayList<String>> UnreadMessage;
    public HashMap<String, ArrayList<String>> ArchivedMessage;

    /**
     * Initialize a MessageBox
     * @param username: the name of the user
     */
    public MessageBox(String username) {
        user = username;
        ReadMessage = new HashMap<>();
        UnreadMessage = new HashMap<>();
        ArchivedMessage = new HashMap<>();
    }

    /**
     * Get the User of the MessageBox
     */
    public String getUser() {
        return user;
    }

    /**
     * Get all messages by type
     * @param boxType: the type of message
     * @return all messages in one type
     */
    public HashMap<String, ArrayList<String>> getAllMessage(String boxType) {
        if(boxType.equals("Read")) {
            return ReadMessage;
        } else if(boxType.equals("Unread")) {
            return UnreadMessage;
        } else {
            return ArchivedMessage;
        }
    }

    /**
     * Get a list of all messages from one specific sender by type
     * @param sender: User who send the message
     * @param boxType: Message's type
     * @return ArchivedMessage: a list of messages
     */
    public ArrayList<String> getMessageFromOne(String sender, String boxType) {
        if(boxType.equals("Read")) {
            return ReadMessage.get(sender);
        } else if(boxType.equals("Unread")) {
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

    /**
     * Get a list of usernames of the user who had sent message to this user
     * by message type
     * @param box: type of message
     */
    public Set<String> getContacts(String box) {
        if(box.equals("Read")){
            return ReadMessage.keySet();
        } else if (box.equals("Unread")) {
            return UnreadMessage.keySet();
        } else{
            return ArchivedMessage.keySet();
        }
    }

    /**
     * Send messages to other users. If sender is already in the contact list, then add the new message to the list.
     * Other wise, create a new Arraylist of the sender and add the message.
     * @param sender: the User who send the message
     * @param message: the Content of the message
     */
    public void sendMessage(String sender, String message) {
        if (UnreadMessage.containsKey(sender)){
            UnreadMessage.get(sender).add(message);
        }else{
            ArrayList<String> arr = new ArrayList<>();
            arr.add(message);
            UnreadMessage.put(sender,arr);
        }
    }

    /**
     * Add a message to destination
     * @param message: the Content of the message
     * @param sender: the User who send the message
     * @param destination: type of message that the User want it to be
     */
    public void addTo(String sender, String message, String destination) {
        if (getAllMessage(destination).containsKey(sender)) {
            getMessageFromOne(sender, destination).add(message);
        } else {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(message);
            getAllMessage(destination).put(sender, temp);
        }
    }

    /**
     * Remove a message from the destination
     * @param sender: the User who send the message
     * @param message: the Content of the message
     * @param destination: type of message that the User want it to be
     */
    public void removeFrom(String sender, String message, String destination) {
        getMessageFromOne(sender, destination).remove(message);
    }

}
