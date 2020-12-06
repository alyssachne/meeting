package Controller;

import Entity.Event;
import Usecase.*;
import Usecase.Sorter.EventEnrollmentSorter;
import Usecase.Sorter.PercentageSorter;

import java.util.ArrayList;
import java.util.HashMap;

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

    public static void getTopFiveEvents(DiscussionManager dm , TalkManager tm, PartyManager pm){
        ArrayList<Event> eventArrayList = getAllEvents(dm,tm,pm);
        EventEnrollmentSorter a = new EventEnrollmentSorter();
        a.sort(eventArrayList);
        while (eventArrayList.size() > 5){
            eventArrayList.remove(-1);
        }
        HashMap<String,Integer> hashMap = new HashMap<String,Integer>();
        for (Event event: eventArrayList){
            hashMap.put(event.getId()+ ". " + event.getTitle(), event.getNumOfAttendees());
        }
        if(eventArrayList.size()<5){
            System.out.println("There is only" + eventArrayList.size() +"events and the top-list is" );
        }
        System.out.println(hashMap);

    }

    public static void getAppTraffic(DiscussionManager dm , TalkManager tm, PartyManager pm){
        ArrayList<Event> eventArrayList = getAllEvents(dm,tm,pm);
        PercentageSorter a = new PercentageSorter();
        a.sort(eventArrayList);
        HashMap<String, Float> TrafficMap= new HashMap<String, Float>();
        for (Event event: eventArrayList){
            TrafficMap.put(event.getId()+ ". " + event.getTitle(), (float)event.getNumOfAttendees()/event.getMaxCapacity());
        }
        System.out.println(TrafficMap);
    }

    public static void enrollmentStatistics(DiscussionManager dm , TalkManager tm, PartyManager pm){
        ArrayList<Event> eventArrayList = getAllEvents(dm,tm,pm);
        PercentageSorter a = new PercentageSorter();
        a.sort(eventArrayList);
        System.out.println("most Attendee chose" + eventArrayList.get(0) );
        System.out.println("");
    }


    private static ArrayList<Event> getAllEvents(DiscussionManager dm , TalkManager tm, PartyManager pm){
        ArrayList<Event> eventArrayList = new ArrayList<>();
        eventArrayList.addAll(dm.allDiscussions);
        eventArrayList.addAll(tm.allTalks);
        eventArrayList.addAll(pm.allParties);
        return eventArrayList;
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
