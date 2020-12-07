package Usecase;

import Entity.*;
import com.sun.xml.internal.ws.api.message.Message;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Set;

public class MessageManager extends Observable {

    public ArrayList<MessageBox> allMessages;

    /**
     * Creates an empty message manager.
     */
    public MessageManager() {
        allMessages = new ArrayList<>();
    }

    public void createMessageBox(String username) {
        MessageBox messageBox = new MessageBox(username);
        allMessages.add(messageBox);
    }

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


    public void seeMessageFromOne(String username, String sender, String box) {
        System.out.println("Here are the messages from" + sender + ":");
        for (int i = 0; i < getMessageBox(username).getMessageFromOne(sender, box).size(); i++) {
            System.out.println("Message" + i + 1 + ": " + getMessageBox(username).getMessageFromOne(sender, box).get(i));
        }
    }

    public void MarkAsUnread(String username, String message, String sender) {
        getMessageBox(username).removeFrom(username, sender, message, "Read");
        getMessageBox(username).addTo(username, message, sender, "Unread");
        System.out.println("You have marked the message as unread");
    }

    public void archiveMessage(String username, String message, String sender) {
        getMessageBox(username).removeFrom(username, sender, message, "Read");
        getMessageBox(username).addTo(username, message, sender, "Archive");
        System.out.println("You have archived the message");
    }

    public void deleteMessage(String username, String message, String sender, String destination) {
        getMessageBox(username).removeFrom(username, sender, message, destination);
        System.out.println("You have deleted the message");
    }

    /**
     * Add a new message to the messageInbox
     * @param receiver: User who receive the message
     * @param sender: User who send the message
     * @param message: The content of the message
     */
    public void addMessage(String receiver, String sender, String message){
       getMessageBox(receiver).addMessage(sender, message);
    }

    /**
     * Get the list of contact of the user
     * @param username: the username of the user
     * @return list of user in User's contact
     */
    public Set<String> getContacts(String username){
        return getMessageBox(username).getContacts();
    }

}
