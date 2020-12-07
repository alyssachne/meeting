package Controller;

import Controller.Schedule.GetScheduleBySpeaker;
import Entity.Event;
import Entity.Talk;
import Usecase.*;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaNamespaceSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventDealer {

    /**
     * Signup this attendee to the event.
     * @param eventId The Id of the event this attendee wants to sign up for.
     */
    public static void signUp(int eventId,AttendeeAct aa,EventManager em,String username){
        if (aa.checkAccess(username).equals(em.checkAccess(eventId))) {
            aa.signUp(username,eventId);
            em.addAttendee(username,eventId);
        } else {
            System.out.println("This event is not open to you.");
        }

    }

    /**
     * Remove this attendee from the event.
     * @param eventId The Id of the event this attendee wants to be removed from.
     */
    public static void cancelSpot(int eventId, AttendeeAct aa, EventManager em, String username){
        aa.removeFromEvent(username,eventId);
        em.cancelSpot(username,eventId);
    }

    public static void cancelEvent(int eventId, Date date, EventManager em, SpeakerAct sa, AttendeeAct aa, RoomManager rm,
                                   CalendarManager cm) {
        for (String s : em.getEvent(eventId).getSpeaker()) {
            sa.cancelEvent(s, eventId, date);
        }
        for (String a : em.getEvent(eventId).getAttendees()) {
            aa.removeFromEvent(a, eventId);
        }
        rm.cancel(em.getEvent(eventId).getRoom(), eventId, date);
        for (int i = 0; i < em.getEvent(eventId).getDuration() - 1; i++) {
            cm.cancelEvent(date, i, eventId);
            em.cancelEvent(eventId);
        }
    }

    public static void changeAccess(int eventId, EventManager em, String access) {
        if(!em.changeAccess(eventId,access)){
            System.out.println("This is access is the same as the current access.");
        }
    }
}
