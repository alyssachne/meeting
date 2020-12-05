package Usecase;

import Entity.*;
import com.sun.xml.internal.ws.api.message.Message;

import java.util.ArrayList;
import java.util.Set;

public class MessageManager {

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

    public boolean MarkAsUnread(String username, Integer MessageIndex, String sender) {
        MessageBox messageBox = getMessageBox(username);
        if(MessageIndex - 1 <= messageBox.getAllMessage("Read").size()) {
            String message = messageBox.getMessageFromOne(sender, "Read").get(MessageIndex - 1);
            removeFrom(username, sender, message, "Read");
            addTo(username, message, sender, "Unread");
            return true;
        }
        return false;
    }

    public boolean archiveMessage(String username, Integer MessageIndex, String sender) {
        MessageBox messageBox = getMessageBox(username);
        if(MessageIndex - 1 <= messageBox.getAllMessage("Read").size()) {
            String message = messageBox.getMessageFromOne(sender, "Read").get(MessageIndex - 1);
            removeFrom(username, sender, message, "Read");
            addTo(username, message, sender, "Archive");
            return true;
        }
        return false;
    }

    public boolean deleteMessage(String username, Integer messageIndex, String sender, String destination) {
        MessageBox messageBox = getMessageBox(username);
        if(messageIndex - 1 <= messageBox.getAllMessage(destination).size()) {
            String message = messageBox.getMessageFromOne(sender, destination).get(messageIndex - 1);
            removeFrom(username, sender, message, destination);
            return true;
        }
        return false;
    }
    private void addTo(String username, String message, String sender, String destination) {
        MessageBox messageBox = getMessageBox(username);
        if (messageBox.getAllMessage(destination).containsKey(sender)) {
            messageBox.getMessageFromOne(sender, destination).add(message);
        } else {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(message);
            messageBox.getAllMessage(destination).put(sender, temp);
        }
    }

    private void removeFrom(String username, String sender, String message, String destination) {
        MessageBox messageBox = getMessageBox(username);
        if (messageBox.getAllMessage(destination).get(sender).size() == 1) {
            messageBox.getAllMessage(destination).remove(sender);
        } else {
            messageBox.getMessageFromOne(sender, destination).remove(message);
        }
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
