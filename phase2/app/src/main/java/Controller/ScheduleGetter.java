package Controller;

import Controller.Schedule.*;
import Controller.Sorter.*;
import Entity.Event;
import Usecase.*;
import java.text.ParseException;
import java.util.*;

public class ScheduleGetter {

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
     * printout selected events this attendee signed up for in the order this person wants. T
     * his schedule is only shown to this attendee.
     */
    public static void attendeeSchedule(AttendeeAct aa, String username, DiscussionManager dm, TalkManager tm,
                                        PartyManager pm, String sort, Map<String, String> filter) throws ParseException {
        ArrayList<Event> temp = new ArrayList<>();
        for (int id : aa.getEvents(username)){
            temp.add(checkEventType(id, dm, tm, pm).getEvent(id));
        }
        filterAndSort(sort, filter, temp);
        for(Event e: temp) {
            System.out.println(e.toString());
        }

    }

    /**
     * printout all events this attendee can signed up for. That is, events that are not full, the rooms where those
     * events take place haven't reach the the rooms' maximum capacity, and this attendee hasn't sign up for the event,
     * yet.
     */
    public static void getAvailableEvent(RoomManager rm, String username, String sort,
                                         DiscussionManager dm, TalkManager tm, PartyManager pm){
        ArrayList<Event> all = availableEvent(rm,username,dm,tm,pm);
        sortEvents(all, sort);
        for(Event e: all) {
            System.out.println(e.toString());}
    }

    public static void getAllSelectedEvents(String sort, Map<String, String> filter, DiscussionManager dm, TalkManager tm,
                                    PartyManager pm) throws ParseException {
        filterAndSort(sort, filter, getAllEvents(dm,tm,pm));
    }

    private static void filterAndSort(String sort, Map<String, String> filter, ArrayList<Event> lst) throws ParseException {
        for (String f: filter.keySet()) {
            lst = filterEvents(lst, f, filter.get(f));
        }
        sortEvents(lst,sort);
        for(Event e: lst) {
            System.out.println(e.toString());
        }
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

    // pass in a copy of lst
    private static ArrayList<Event> filterEvents(ArrayList<Event> lst, String filter, String restriction) throws ParseException {
        if(filter.equals("Day")) {
            GetScheduleByDay gs = new GetScheduleByDay();
            return gs.getSchedule(restriction, lst);
        } else if (filter.equals("Speaker")) {
            GetScheduleBySpeaker gs = new GetScheduleBySpeaker();
            return gs.getSchedule(restriction, lst);
        } else {
            GetScheduleByTime gs = new GetScheduleByTime();
            return gs.getSchedule(restriction, lst);
        }
    }

    private static void sortEvents(ArrayList<Event> lst, String sort){
        switch (sort) {
            case "eventId": {
                EventIdSorter sorter = new EventIdSorter();
                sorter.sort(lst);
                break;
            }
            case "RoomId": {
                EventRoomSorter sorter = new EventRoomSorter();
                sorter.sort(lst);
                break;
            }
            case "Speaker": {
                EventSpeakerSorter sorter = new EventSpeakerSorter();
                sorter.sort(lst);
                break;
            }
            case "Title": {
                EventTitleSorter sorter = new EventTitleSorter();
                sorter.sort(lst);
                break;
            }
            case "": {
                // by default, it is sorted by time.
                EventTimeSorter sorter = new EventTimeSorter();
                sorter.sort(lst);
                break;
            }
        }
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
