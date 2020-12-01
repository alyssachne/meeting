package Controller;

import Usecase.*;

public class EventSigner {

    /**
     * Signup this attendee to the event.
     * @param eventId The Id of the event this attendee wants to sign up for.
     */
    public static void signUp(int eventId,AttendeeAct aa,EventManager em,String username){
        aa.signUp(username,eventId);
        em.addAttendee(username,eventId);
    }

    /**
     * Remove this attendee from the event.
     * @param eventId The Id of the event this attendee wants to be removed from.
     */
    public static void cancelSpot(int eventId, AttendeeAct aa, EventManager em, String username){
        aa.cancelSpot(username,eventId);
        em.cancelSpot(username,eventId);
    }
}
