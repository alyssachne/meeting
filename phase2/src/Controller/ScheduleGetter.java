package Controller;

import Entity.Event;
import Usecase.*;

import java.util.ArrayList;

import java.util.List;

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
    public static void roomList(RoomManager rm, int maxCapacity, List<String> constraints){
        rm.suggestedRooms(maxCapacity, constraints);
    }

    /**
     * printout all events this speaker is going to give. This schedule is only shown to the speaker.
     */
    public static void speakerSchedule(SpeakerAct sa, String username, DiscussionManager dm, TalkManager tm,
                                       PartyManager pm){
        for (int id : sa.eventList(username)){
            //sa.eventList(username) is arraylist of eventIds
            System.out.println(checkEventType(id, dm, tm, pm).getEvent(id).toString());
        }
    }

    /**
     * printout all events this attendee signed up for. This schedule is only shown to this attendee.
     */
    public static void attendeeSchedule(AttendeeAct aa, String username, DiscussionManager dm, TalkManager tm,
                                        PartyManager pm){
        for (int id : aa.getEvents(username)){
            System.out.println(checkEventType(id, dm, tm, pm).getEvent(id).toString());
        }
    }

    /**
     * printout all events this attendee can signed up for. That is, events that are not full, the rooms where those
     * events take place haven't reach the the rooms' maximum capacity, and this attendee hasn't sign up for the event,
     * yet.
     */

    // !!!see if there is anything can do to reduce redundant code!!!
    // I wrote a getAllEvents, may it helps
    public static void getAvailableEvent(RoomManager rm, String username, DiscussionManager dm, TalkManager tm,
                                         PartyManager pm){
        //events that are not full
        for (Event event: dm.allDiscussions){
            if ((!event.getAttendees().contains(username))&&
                    rm.getMaxCapacity(event.getRoom())>event.getAttendees().size()){
                //check if the attendee has signed up the event or not and if the event reaches its room's maxCapacity
                System.out.println(dm.getEvent(event.getId()).toString());
            }
        }
        for (Event event: tm.allTalks){
            if ((!event.getAttendees().contains(username))&&
                    rm.getMaxCapacity(event.getRoom())>event.getAttendees().size()){
                //check if the attendee has signed up the event or not and if the event reaches its room's maxCapacity
                System.out.println(tm.getEvent(event.getId()).toString());
            }
        }
        for (Event event: pm.allParties){
            if ((!event.getAttendees().contains(username))&&
                    rm.getMaxCapacity(event.getRoom())>event.getAttendees().size()){
                //check if the attendee has signed up the event or not and if the event reaches its room's maxCapacity
                System.out.println(pm.getEvent(event.getId()).toString());
            }
        }
    }

    private static EventManager checkEventType(int id, DiscussionManager dm, TalkManager tm,PartyManager pm) {
        if(pm.containEvent(id)) {
            return pm;
        } else if(tm.containEvent(id)) {
            return tm;
        } else {
            return dm;
        }
    }
}
