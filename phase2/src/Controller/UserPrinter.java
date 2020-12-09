package Controller;

import Usecase.*;

public class UserPrinter {


    /**
     * Printout all usernames of speakers who give the events this attendee signed up for.
     */
    public static void checkSpeakers(ActFactory af, String username, EventFactory ef) {
        for(Integer eventId: af.getEvents(username)) {
            System.out.println(ef.getEvent(eventId).getSpeaker());
        }
    }

    /**
     * Printout all usernames of attendees who attend this event.
     * @param eventId The Id of the certain event that is being chosen.
     */
    public static void checkAudiences(Integer eventId, EventFactory em){
        for(String name : em.getEvent(eventId).getAttendees()){
            System.out.println(name);
        }
    }

    /**
     * printout all usernames of users who had sent messages to this user.
     */
    public static void checkContacts(MessageManager mm, String username) {
        for (String name : mm.getContacts(username)) {
            System.out.println(name);
        }
    }

    public static void changeUserAccess(String username, ActFactory af, String access) {
        af.changeAccess(username, access);
    }
}