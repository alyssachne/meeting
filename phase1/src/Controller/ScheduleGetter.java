package Controller;

import Entity.Event;
import Usecase.*;

public class ScheduleGetter {

    /**
     * printout all speakers and their available times.
     */
    public static void speakerList(SpeakerAct sa){
        sa.speakerList();
    }

    /**
     * printout all rooms and their available times.
     */
    public static void roomList(RoomManager rm){
        rm.roomList();
    }

    /**
     * printout all events this speaker is going to give. This schedule is only shown to the speaker.
     */
    public static void speakerSchedule(SpeakerAct sa, String username, EventManager em){
        for (int id : sa.eventList(username)){
            //sa.eventList(username) is arraylist of eventIds
            System.out.println(em.getEvent(id).toString());
        }
    }

    /**
     * printout all events this attendee signed up for. This schedule is only shown to this attendee.
     */
    public static void attendeeSchedule(AttendeeAct aa, String username, EventManager em){
        for (int id : aa.getEvents(username)){
            System.out.println(em.getEvent(id).toString());
        }
    }

    /**
     * printout all events this attendee can signed up for. That is, events that are not full, the rooms where those
     * events take place haven't reach the the rooms' maximum capacity, and this attendee hasn't sign up for the event,
     * yet.
     */
    public static void getAvailableEvent(RoomManager rm, String username, EventManager em){
        //events that are not full
        for (Event event: em.allEvents){
            if ((!event.getAttendees().contains(username))&&
                    rm.getMaxCapacity(event.getRoom())>event.getAttendees().size()){
                //check if the attendee has signed up the event or not and if the event reaches its room's maxCapacity
                System.out.println(em.getEvent(event.getId()).toString());
            }
        }
    }

}
