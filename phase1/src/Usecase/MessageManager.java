package Usecase;

import Entity.*;
import com.sun.xml.internal.ws.api.message.Message;

import java.util.ArrayList;

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


    public void getReadMessageFromOne(String username, String sender) {
        System.out.println("Here are the messages from" + sender + ":");
        for(int i = 0; i < getMessageBox(username).getReadMessage_One(sender).size(); i++) {
            System.out.println("Message" + i + 1 + ": " + getMessageBox(username).getReadMessage_One(sender).get(i));
        }
    }

    public void getUnreadMessageFromOne(String username, String sender) {
        System.out.println("Here are the messages from" + sender + ":");
        for(int i = 0; i < getMessageBox(username).getUnreadMessage_One(sender).size(); i++) {
            System.out.println("Message" + i + 1 + ": " + getMessageBox(username).getUnreadMessage_One(sender).get(i));
            addToRead(username, sender, getMessageBox(username).getUnreadMessage_One(sender).get(i));
            removeFromUnread(username, sender, getMessageBox(username).getUnreadMessage_One(sender).get(i));
        }
    }

    public void MarkAsUnread(String username, Integer MessageIndex, String sender) {
        MessageBox messageBox = getMessageBox(username);
        String message = messageBox.getReadMessage_One(sender).get(MessageIndex - 1);
        removeFromRead(username, sender, message);
        addToUnread(username, message, sender);
    }

    public void archiveMessage(String username, Integer MessageIndex, String sender) {
        MessageBox messageBox = getMessageBox(username);
        String message = messageBox.getReadMessage_One(sender).get(MessageIndex - 1);
        removeFromRead(username, sender, message);
        addToArchive(username, message, sender);
    }

    private void addToUnread(String username, String message, String sender) {
        MessageBox messageBox = getMessageBox(username);
        if(messageBox.getUnreadMessage().containsKey(sender)) {
            messageBox.getUnreadMessage_One(sender).add(message);
        } else {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(message);
            messageBox.getUnreadMessage().put(sender, temp);
        }
    }

    private void addToRead(String username, String message, String sender) {
        MessageBox messageBox = getMessageBox(username);
        if(messageBox.getAllReadMessage().containsKey(sender)) {
            messageBox.getReadMessage_One(sender).add(message);
        } else {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(message);
            messageBox.getAllReadMessage().put(sender, temp);
        }
    }

    private void addToArchive(String username, String message, String sender) {
        MessageBox messageBox = getMessageBox(username);
        if(messageBox.getArchivedMessage().containsKey(sender)) {
            messageBox.getArchivedMessage_One(sender).add(message);
        } else {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(message);
            messageBox.getArchivedMessage().put(sender, temp);
        }
    }

    private void removeFromUnread(String username, String sender, String message) {
        MessageBox messageBox = getMessageBox(username);
        if(messageBox.getUnreadMessage().get(sender).size() == 1) {
            messageBox.getUnreadMessage().remove(sender);
        } else {
            messageBox.getUnreadMessage_One(sender).remove(message);
        }
    }

    private void removeFromRead(String username, String sender, String message) {
        MessageBox messageBox = getMessageBox(username);
        if(messageBox.getAllReadMessage().get(sender).size() == 1) {
            messageBox.getAllReadMessage().remove(sender);
        } else {
            messageBox.getReadMessage_One(sender).remove(message);
        }
    }
}
