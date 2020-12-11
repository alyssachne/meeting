package Controller;

import Usecase.*;

import java.io.Serializable;

public class MessageDealer implements Serializable {

    /**
     * Allow the organizer to send messages to all attendees or all speakers.
     * @param message The message this organizer wants to send.
     * @param userType The type of users this organizer wants to send to, i.e. Speaker or Attendee.
     */
    public static void groupMessageTo(String message, String userType, ActFactory af, MessageManager mm,
                                      String sender){
        if (userType.equals("Speaker")){
            for(String receiver: af.speakerList()){
                mm.sendMessage(receiver,sender,message);
            }
        }else if (userType.equals("Attendee")){
            for(String receiver: af.attendeeList()){
                mm.sendMessage(receiver,sender,message);
            }
        }

    }

    /**
     * Allow the speaker to send messages to all attendees in certain event.
     * @param message The message this speaker wants to send.
     * @param eventId The Id of the certain event this speaker choose.
     */
    public static void eventMessage_Attendee(String message, Integer eventId, EventFactory em,
                                             MessageManager mm, String sender){
        for (String receiver: em.getEvent(eventId).getAttendees()) {
            mm.sendMessage(receiver,sender,message);
        }
    }

    /**
     * Allow the user to send messages to a specific user.
     * @param receiver The username of the user who is going to receive this message.
     * @param sender The username of the user who is going to send this message.
     * @param message The message this user wants to send.
     */
    public static void privateMessageTo(String receiver, String message, MessageManager mm, String sender){
        mm.sendMessage(receiver,sender,message);
    }

    /**
     * Printout all messages sent from this user.
     * @param sender The username of the user who sent messages to this user.
     */
    public static void getMessage(String sender, MessageManager mm, String username, String box){
        mm.seeMessageFromOne(username,sender, box);
//        System.out.println("Here are the read messages from this person");
//        mm.seeMessageFromOne(username,sender, "Read");
//        System.out.println("Here are the archived messages from this person");
//        mm.seeMessageFromOne(username,sender, "Archived");
    }

    public static void readAllMessage(String box, String username, MessageManager mm){
        mm.seeAllMessage(username, box);
    }

    public static void markAsUnread(String receiver, MessageManager mm, String sender, int index, String box) {
        mm.MarkAsUnread(receiver, index, sender, box);
    }

    public static void deleteMessage(String receiver, MessageManager mm, String sender, int index, String box) {
        mm.deleteMessage(receiver, index, sender, box);
    }

    public static void archiveMessage(String receiver, MessageManager mm, String sender, int index) {
        mm.archiveMessage(receiver, index, sender);
    }

    public static boolean hasContacts(String username, MessageManager mm, String box) {
        if(mm.getMessageBox(username).getAllMessage(box).isEmpty()){
            return false;
        }
        return true;
    }

    public static boolean hasMessage(String username, MessageManager mm, String box){
        for(String name: mm.getMessageBox(username).getAllMessage(box).keySet()){
            if(mm.getMessageBox(username).getAllMessage(box).get(name).size() != 0){
                return true;
            }
        }
        return false;
    }
}
