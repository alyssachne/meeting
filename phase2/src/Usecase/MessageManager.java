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


    public void seeAllMessage(String username, String box) {
        System.out.println("Here are the messages in " + box + " :");
        for (int i = 0; i < getMessageBox(username).getAllMessage(box).size(); i++) {
            System.out.println("Message" + i + 1 + ": " + getMessageBox(username).getAllMessage(box).get(i));
        }
        if(box.equals("Unread")){
            for(String sender: getMessageBox(username).getAllMessage("Unread").keySet()){
                seeUnreadMessage(username, sender);
            }
        }
    }
    public void seeMessageFromOne(String username, String sender, String box) {
        System.out.println("Here are the messages from" + sender + ":");
        for (int i = 0; i < getMessageBox(username).getMessageFromOne(sender, box).size(); i++) {
            System.out.println("Message" + i + 1 + ": " + getMessageBox(username).getMessageFromOne(sender, box).get(i));
        }
        if(box.equals("Unread")){
            seeUnreadMessage(username, sender);
        }
    }

    private void seeUnreadMessage(String username, String sender ){
        for(String m: getMessageBox(username).getMessageFromOne(sender, "Unread")){
            getMessageBox(username).addTo(sender,m,"Read");
            getMessageBox(username).removeFrom(sender,m,"Unread");
        }
    }

    public void MarkAsUnread(String username, int index, String sender, String box) {
        getMessageBox(username).removeFrom(sender, getMessage(username,index,sender,box), box);
        getMessageBox(username).addTo(getMessage(username,index,sender,box), sender, "Unread");
        System.out.println("You have marked the message as unread");
    }

    public void archiveMessage(String username, int index, String sender, String box) {
        getMessageBox(username).removeFrom(sender, getMessage(username,index,sender,box), "Read");
        getMessageBox(username).addTo(getMessage(username,index,sender,box), sender, "Archive");
        System.out.println("You have archived the message");
    }

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

    public Set<String> getContacts(String username, String box){
        return getMessageBox(username).getContacts(box);
    }

    private String getMessage(String username, int index, String sender, String box){
        return getMessageBox(username).getMessageFromOne(sender,box).get(index-1);
    }
}
