package Usecase;

import Entity.*;
//import com.sun.xml.internal.ws.api.message.Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Set;

public class MessageManager extends Observable implements Serializable {

    public ArrayList<MessageBox> allMessages;

    /**
     * Creates an empty message manager.
     */
    public MessageManager() {
        allMessages = new ArrayList<>();
    }

    /**
     * Create a new message box
     * @param username: the username of the user
     */
    public void createMessageBox(String username) {
        MessageBox messageBox = new MessageBox(username);
        allMessages.add(messageBox);
    }

    /**
     * Get a user's message box
     * @param username: the username of the User
     * @return
     */
    public MessageBox getMessageBox(String username) {
        try {
            for (MessageBox m : allMessages) {
                if (m.getUser().equals(username)) {
                    return m;
                }
            }
        }
        catch(Exception e) {
            System.out.println("This message box does not exist.");
        }
        return null;
    }

    /**
     * Print all messages from one type of message
     * @param username: the username of the User
     * @param box: the type of message
     */
    public void seeAllMessage(String username, String box) {
        System.out.println("Here are the messages in " + box + " :");
        for (int i = 0; i < getMessageBox(username).getAllMessage(box).size(); i++) {
            System.out.println("Message" + (i + 1) + ": " + getMessageBox(username).getAllMessage(box).get(i));
        }
        if(box.equals("Unread")){
            for(String sender: getMessageBox(username).getAllMessage("Unread").keySet()){
                seeUnreadMessage(username, sender);
            }
        }
    }

    /**
     * Print all messages from one user by messagebox type
     * @param username: the username of the user
     * @param sender: the username of the User who send the message
     * @param box: the type of message
     */
    public void seeMessageFromOne(String username, String sender, String box) {
        System.out.println("Here are the messages from" + sender + ":");
        for (int i = 0; i < getMessageBox(username).getMessageFromOne(sender, box).size(); i++) {
            System.out.println("Message" + (i + 1) + ": " + getMessageBox(username).getMessageFromOne(sender, box).get(i));
        }
        if(box.equals("Unread")){
            seeUnreadMessage(username, sender);
        }
    }

    /**
     * Print all unread messages
     * @param username: the username of the User
     * @param sender: the username of the Sender
     */
    private void seeUnreadMessage(String username, String sender ){
        for(String m: getMessageBox(username).getMessageFromOne(sender, "Unread")){
            getMessageBox(username).addTo(sender,m,"Read");
            getMessageBox(username).removeFrom(sender,m,"Unread");
        }
    }

    /**
     * Set the message as unread
     * @param username: the username of the User
     * @param index: MessageManager
     * @param sender: the username of the sender
     * @param box: the original type of message
     */
    public void MarkAsUnread(String username, int index, String sender, String box) {
        getMessageBox(username).removeFrom(sender, getMessage(username,index,sender,box), box);
        getMessageBox(username).addTo(getMessage(username,index,sender,box), sender, "Unread");
        System.out.println("You have marked the message as unread");
    }

    /**
     * Archive a message
     * @param username: the username of the User
     * @param index: MessageManager
     * @param sender: the username of the sender
     * @param box: the original messagebox type
     */
    public void archiveMessage(String username, int index, String sender, String box) {
        getMessageBox(username).removeFrom(sender, getMessage(username,index,sender,box), "Read");
        getMessageBox(username).addTo(getMessage(username,index,sender,box), sender, "Archive");
        System.out.println("You have archived the message");
    }

    /**
     * Delete a message from one destination
     * @param username: the username of the User
     * @param index: MessageManager
     * @param sender: the username of the sender
     * @param destination: the original message type where you want to delete from.
     */
    public void deleteMessage(String username, int index, String sender, String destination) {
        getMessageBox(username).removeFrom(sender, getMessage(username,index,sender,destination), destination);
        System.out.println("You have deleted the message");
    }

    /**
     * Add a new message to the messageInbox
     * @param receiver: User who receive the message
     * @param sender: User who send the message
     * @param message: The content of the message
     */
    public void sendMessage(String receiver, String sender, String message){
       getMessageBox(receiver).sendMessage(sender, message);
    }

    /**
     * Get the list of contact of the user
     * @param username: the username of the user
     * @return list of user in User's contact
     */
    public Set<String> getAllContacts(String username){
        return getMessageBox(username).getAllContacts();
    }

    /**
     * Get a list of username of the user (list of contact) from the messages sended
     * @param username: the username of the User
     * @param box: the messagebox's type
     * @return
     */
    public Set<String> getContacts(String username, String box){
        return getMessageBox(username).getContacts(box);
    }

    /**
     * Get a specific message from the list of messages from one user
     * @param username: the username of the User
     * @param index: the index of the message
     * @param sender: the username of the User
     * @param box: the type of message want to search from
     */
    private String getMessage(String username, int index, String sender, String box){
//        System.out.println(getMessageBox(username).getMessageFromOne(sender,box));
        return getMessageBox(username).getMessageFromOne(sender,box).get(index-1);
    }
}
