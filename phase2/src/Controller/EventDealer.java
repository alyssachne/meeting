package Controller;

import Usecase.*;
//import com.sun.org.apache.xerces.internal.impl.xs.SchemaNamespaceSupport;

import java.io.Serializable;
import java.util.Date;

public class EventDealer implements Serializable {

    /**
     * Signup this attendee to the event.
     * @param eventId The Id of the event this attendee wants to sign up for.
     */
    public static void signUp(int eventId, ActFactory af, EventFactory em, String username){
        if (af.checkAccess(username).equals("VIP")) {
            af.signUp(username,eventId);
            em.addAttendee(username,eventId);
        } else if(af.checkAccess(username).equals(em.checkAccess(eventId))) {
            af.signUp(username,eventId);
            em.addAttendee(username,eventId);
        } else{
            System.out.println("This event is not open to you.");
        }
    }

    /**
     * Remove this attendee from the event.
     * @param eventId The Id of the event this attendee wants to be removed from.
     */
    public static void cancelSpot(int eventId, ActFactory af, EventFactory em, String username){
        af.removeFromEvent(username,eventId);
        em.cancelSpot(username,eventId);
    }

    public static void cancelEvent(int eventId, Date date, EventFactory em, ActFactory af, RoomManager rm,
                                   CalendarManager cm) {
        for (String s : em.getEvent(eventId).getSpeaker()) {
            af.cancelEvent(s, eventId, date);
        }
        for (String a : em.getEvent(eventId).getAttendees()) {
            af.removeFromEvent(a, eventId);
        }
        rm.cancel(em.getEvent(eventId).getRoom(), eventId, date);
        for (int i = 0; i < em.getEvent(eventId).getDuration() - 1; i++) {
            cm.cancelEvent(date, i, eventId);
            em.cancelEvent(eventId);
        }
    }

    public static void changeEventAccess(int eventId, EventFactory em, String access) {
        if(!em.changeAccess(eventId,access)){
            System.out.println("This is access is the same as the current access.");
        }
    }

    public static void likeEvent(int eventId, ActFactory af, String username) {
        af.likeEvent(username, eventId);
    }
}
