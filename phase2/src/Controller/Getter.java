package Controller;

import Controller.Schedule.GetScheduleByTime;
import Controller.Sorter.*;
import Entity.Event;
import Usecase.*;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Getter {

    /**
     * printout all speakers and their available times on the selected date.
     */
    public static void speakerAvailable(SpeakerAct sa, Date date, CalendarManager cm){
        for(String s: sa.speakerList()) {
            System.out.println(s);
            for(Integer time: cm.getAvailable(date, sa.getEvents(s))) {
                System.out.println(time);
            };
        }
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
                                       PartyManager pm) {
        for (Date date : sa.allEventList(username).keySet()) {
            System.out.println("On " + date);
            for (int id : sa.allEventList(username).get(date)) {
                //sa.eventList(username) is arraylist of eventIds
                System.out.println(checkEventType(id, dm, tm, pm).getEvent(id).toString());
            }
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

    /**
     * printout all events this attendee can signed up for. That is, events that are not full, the rooms where those
     * events take place haven't reach the the rooms' maximum capacity, and this attendee hasn't sign up for the event,
     * yet.
     */
    public static void getAvailableEvent(RoomManager rm, String username,String sort,
                                         DiscussionManager dm, TalkManager tm, PartyManager pm){
        ArrayList<Event> all = availableEvent(rm,username,dm,tm,pm);
        sortEvents(all, sort);
        for(Event e: all) {
            System.out.println(e.toString());}
    }

    private static ArrayList<Event> availableEvent(RoomManager rm, String username, DiscussionManager dm, TalkManager tm,
                                         PartyManager pm){
        //events that are not full
        ArrayList<Event> temp = new ArrayList<>();
        for (Event event: getAllEvents(dm,tm,pm)){
            //check if the attendee has signed up the event or not and if the event reaches its room's maxCapacity
            if ((!event.getAttendees().contains(username))&&
                    rm.getMaxCapacity(event.getRoom())>event.getAttendees().size()){
                temp.add(event);
            }
        }
        return temp;
    }

    private static ArrayList<Event> sortEvents(ArrayList<Event> lst, String sort){
        if(sort.equals("eventId")) {
            EventIdSorter sorter = new EventIdSorter();
            sorter.sort(lst);
        } else if (sort.equals("RoomId")) {
            EventRoomSorter sorter = new EventRoomSorter();
            sorter.sort(lst);
        } else if (sort.equals("Speaker")) {
            EventSpeakerSorter sorter = new EventSpeakerSorter();
            sorter.sort(lst);
        } else if (sort.equals("Title")) {
            EventTitleSorter sorter = new EventTitleSorter();
            sorter.sort(lst);
        } else {
            // by default, it is sorted by time.
            EventTimeSorter sorter = new EventTimeSorter();
            sorter.sort(lst);
        }
        return lst;
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
