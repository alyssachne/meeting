package Controller;

import Usecase.*;

public class UserCheckers {


    /**
     * Printout all usernames of speakers who give the events this attendee signed up for.
     */
    public static void checkSpeakers(AttendeeAct aa, String username, DiscussionManager dm, TalkManager tm,
                                     PartyManager pm) {
        for(Integer eventId: aa.getEvents(username)) {
            System.out.println(checkEventType(eventId,dm,tm,pm).getEvent(eventId).getSpeaker());
        }
    }

    /**
     * Printout all usernames of attendees who attend this event.
     * @param eventId The Id of the certain event that is being chosen.
     */
    public static void checkAudiences(Integer eventId, EventManager em){
        for(String name : em.getEvent(eventId).getAttendees()){
            System.out.println(name);
        }
    }

    /**
     * printout all usernames of users who had sent messages to this user.
     */
    public static void checkContacts(String type, OrganizerAct oa, SpeakerAct sa, AttendeeAct aa, String username){
        if (type.equals("Organizer")){
            for(String name : oa.getContacts(username)){
                System.out.println(name);
            }
        }else if (type.equals("Speaker")){
            for(String name : sa.getContacts(username)){
                System.out.println(name);
            }
        }else if (type.equals("Attendee")){
            for(String name : aa.getContacts(username)){
                System.out.println(name);
            }
        }
    }

    public static EventManager checkEventType(int id, DiscussionManager dm, TalkManager tm,PartyManager pm) {
        if(pm.containEvent(id)) {
            return pm;
        } else if(tm.containEvent(id)) {
            return tm;
        } else {
            return dm;
        }
    }
}
