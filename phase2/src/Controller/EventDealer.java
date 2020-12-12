package Controller;

import Entity.Event;
import Usecase.*;
import com.sun.javafx.event.EventHandlerManager;
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

    /**
     * A method used to cancel a event. The event will be removed from the system.
     * @param eventId: the id of the event to be cancelled
     * @param date: the date that the event will be held
     * @param em: the class we used to manage all the event
     * @param af: a class we used to manage all the users
     * @param rm: a class we used to manage all the rooms
     * @param cm: a class we used to manage all the calendars
     */
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

    /**
     * A method used to change the event from VIP to Normal or vice versa
     * @param eventId: id of the event to be changed
     * @param em: the class we used to manage all the events
     * @param access: the access after we change the event
     */
    public static void changeEventAccess(int eventId, EventFactory em, String access) {
        if(!em.changeAccess(eventId,access)){
            System.out.println("This is access is the same as the current access.");
        }
    }

    /**
     * A method used to document that a user like an event
     * @param eventId: id of the event to be liked by the user
     * @param af: the class we used to manage all the users
     * @param username: the username of the user that will like an event
     */
    public static void likeEvent(int eventId, ActFactory af, String username) {
        af.likeEvent(username, eventId);
    }

    /**
     * A method used to tell if an event exist
     * @param ef: the class we use to manage all the events
     * @return  a boolean value indicating if an event exists in out system
     */
    public static boolean hasEvent(EventFactory ef){
        return !ef.allEvents.isEmpty();
    }

    /**
     * A method used to tell if an user has signed up for any event
     * @param username: the user name of the user to be checked
     * @return  a boolean value indicating if the user has signed up for any event
     */
    public static boolean hasSignUp(String username, ActFactory af){
        return !af.getEvents(username).isEmpty();
    }

    /**
     * Printout all events in the system.
     */
    public static void getAllEvents(EventFactory ef){
        for(Event event: ef.allEvents){
            System.out.println(event);
        }
    }
}
