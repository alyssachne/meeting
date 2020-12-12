package Controller;

import Usecase.*;

import java.io.Serializable;

/**
 * This class check and print information of the user,
 */
public class UserPrinter implements Serializable {

    /**
     * Print out all usernames of speakers who give the events this attendee signed up for.
     * @param af ActFactory in Use case.
     * @param username The username of the speakers.
     * @param ef EventFactory in Use case.
     */
    public static void checkSpeakers(ActFactory af, String username, EventFactory ef) {
        for(Integer eventId: af.getEvents(username)) {
            System.out.println(ef.getEvent(eventId).getSpeaker());
        }
    }

    /**
     * Print out all usernames of attendees who attend this event.
     * @param eventId The Id of the certain event that is being chosen.
     * @param em EventFactory in Use case.
     */
    public static void checkAudiences(Integer eventId, EventFactory em){
        for(String name : em.getEvent(eventId).getAttendees()){
            System.out.println(name);
        }
    }

    /**
     * Print out all usernames of users who had sent messages to this user.
     * @param mm MessageManager in Use case.
     * @param username The username of the user.
     * @param box The message box of the user.
     */
    public static void checkContacts(MessageManager mm, String username, String box) {
        if (box.equals("all")) {
            for (String name : mm.getAllContacts(username)) {
                System.out.println(name);
            }
        } else {
            for (String name:mm.getContacts(username,box)) {
                System.out.println(name);
            }
        }
    }

    /**
     * Print out all usernames of users in the system.
     * @param af ActFactory in Use case.
     * @param username The username of the user.
     */
    public static void checkUsers(ActFactory af, String username) {
        for (String name : af.userHashMap.keySet()) {
            if (!username.equals(name)) {
                System.out.println(name);
            }
        }
    }

    /**
     * Change user's access to VIP.
     * @param username The username of the User.
     * @param af ActFactory in Use case.
     * @param access The access level of the User (VIP or Normal).
     */
    public static void changeUserAccess(String username, ActFactory af, String access) {
        af.changeAccess(username, access);
    }
}
