package Controller;

import Usecase.*;

public class Messagers {

    /**
     * Allow the organizer to send messages to all attendees or all speakers.
     * @param message The message this organizer wants to send.
     * @param userType The type of users this organizer wants to send to, i.e. Speaker or Attendee.
     */
    public static void groupMessageTo(String message, String userType, SpeakerAct sa, AttendeeAct aa, String Susername){
        if (userType.equals("Speaker")){
            for(String username: sa.speakerMap.keySet()){
                sa.addMessage(username,Susername,message);
            }
        }else if (userType.equals("Attendee")){
            for(String username: aa.attendeeMap.keySet()){
                aa.addMessage(username,Susername,message);
            }
        }

    }

    /**
     * Allow the speaker to send messages to all attendees in certain event.
     * @param message The message this speaker wants to send.
     * @param eventId The Id of the certain event this speaker choose.
     */
    public static void eventMessage_Attendee(String message, Integer eventId, AttendeeAct aa, EventManager em, String Susername){
        for (String username: em.getEvent(eventId).getAttendees()) {
            aa.addMessage(username,Susername,message);
        }
    }

    /**
     * Allow the user to send messages to a specific user.
     * @param receiver The username of the user who is going to receive this message.
     * @param userType The type of users this organizer wants to send to, i.e. Speaker, organizer, or Attendee.
     * @param message The message this user wants to send.
     */
    public static void privateMessageTo(String receiver, String userType, String message, SpeakerAct sa, AttendeeAct aa, String username){
        if (userType.equals("Speaker")){
            sa.addMessage(receiver,username,message);
        }else if (userType.equals("Attendee")){
            aa.addMessage(receiver,username,message);
        }
    }

    /**
     * Printout all messages sent from this user.
     * @param sender The username of the user who sent messages to this user.
     */
    public static void getMessage(String sender, SpeakerAct sa, AttendeeAct aa, OrganizerAct oa, String type, String username){
        if (type.equals("Organizer")){
            for (String message : oa.getMessage(username,sender)){
                System.out.println(message);
            }
        }else if (type.equals("Speaker")){
            for (String message : sa.getMessage(username,sender)){
                System.out.println(message);
            }
        }else if (type.equals("Attendee")){
            for (String message : aa.getMessage(username,sender)){
                System.out.println(message);
            }
        }
    }

}
