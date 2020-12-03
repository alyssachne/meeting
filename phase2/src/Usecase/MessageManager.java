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


    public void getMessageFromOne(String username, String sender, String box) {
        System.out.println("Here are the messages from" + sender + ":");
        for (int i = 0; i < getMessageBox(username).getMessageFromOne(sender, box).size(); i++) {
            System.out.println("Message" + i + 1 + ": " + getMessageBox(username).getMessageFromOne(sender, box).get(i));
        }
    }

    public void MarkAsUnread(String username, Integer MessageIndex, String sender) {
        MessageBox messageBox = getMessageBox(username);
        String message = messageBox.getMessageFromOne(sender, "Read").get(MessageIndex - 1);
        removeFrom(username, sender, message, "Read");
        addTo(username, message, sender, "Unread");
    }

    public void archiveMessage(String username, Integer MessageIndex, String sender) {
        MessageBox messageBox = getMessageBox(username);
        String message = messageBox.getMessageFromOne(sender, "Read").get(MessageIndex - 1);
        removeFrom(username, sender, message, "Read");
        addTo(username, message, sender, "Archive");
    }

    private void addTo(String username, String message, String sender, String destination) {
        MessageBox messageBox = getMessageBox(username);
        if (messageBox.getMessage(destination).containsKey(sender)) {
            messageBox.getMessageFromOne(sender, destination).add(message);
        } else {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(message);
            messageBox.getMessage(destination).put(sender, temp);
        }
    }

    private void removeFrom(String username, String sender, String message, String destination) {
        MessageBox messageBox = getMessageBox(username);
        if (messageBox.getMessage(destination).get(sender).size() == 1) {
            messageBox.getMessage(destination).remove(sender);
        } else {
            messageBox.getMessageFromOne(sender, destination).remove(message);
        }
    }

}
