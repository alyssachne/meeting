package Controller;

import Controller.getSchedule;
import Controller.Sorter.*;
import Entity.Event;
import Usecase.*;
import java.text.ParseException;
import java.util.*;

public class ScheduleGetter {

    /**
     * printout all events this speaker is going to give. This schedule is only shown to the speaker.
     */
    public static void speakerSchedule(SpeakerAct sa, String username, EventFactory ef) {
        for (Date date : sa.allEventList(username).keySet()) {
            System.out.println("On " + date);
            for (int id : sa.allEventList(username).get(date)) {
                //sa.eventList(username) is arraylist of eventIds
                System.out.println(ef.getEvent(id).toString());
            }
        }
    }

    /**
     * printout selected events this attendee signed up for in the order this person wants. T
     * his schedule is only shown to this attendee.
     */
    public static void attendeeSchedule(AttendeeAct aa, String username, EventFactory ef, String sort,
                                        Map<String, String> filter) throws ParseException {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int id : aa.getEvents(username)){
            temp.add(id);
        }
        filterAndSort(sort, filter, temp, ef);
        for(int i: temp) {
            System.out.println(ef.getEvent(i).toString());
        }

    }

    /**
     * printout all events this attendee can signed up for. That is, events that are not full, the rooms where those
     * events take place haven't reach the the rooms' maximum capacity, and this attendee hasn't sign up for the event,
     * yet.
     */
    public static void getAvailableEvent(RoomManager rm, String username, String sort,EventFactory ef){
        ArrayList<Integer> all = availableEvent(rm,username,ef);
        sortEvents(all, sort);
        for(Integer i: all) {
            System.out.println(ef.getEvent(i).toString());}
    }

    public static void getAllSelectedEvents(String sort, Map<String, String> filter, EventFactory ef) throws ParseException {
        filterAndSort(sort, filter, ef.getAllEvents(),ef);
    }

    private static void filterAndSort(String sort, Map<String, String> filter, ArrayList<Integer> lst, EventFactory ef) throws ParseException {
        for (String f: filter.keySet()) {
            lst = filterEvents(lst, f, filter.get(f),ef);
        }
        sortEvents(lst,sort);
        for(Integer i: lst) {
            System.out.println(ef.getEvent(i).toString());
        }
    }
    private static ArrayList<Integer> availableEvent(RoomManager rm, String username, EventFactory ef){
        //events that are not full
        ArrayList<Integer> temp = new ArrayList<>();
        for (Integer i: ef.getAllEvents()){
            //check if the attendee has signed up the event or not and if the event reaches its room's maxCapacity
            if ((!ef.getEvent(i).getAttendees().contains(username))&&
                    rm.getMaxCapacity(ef.getEvent(i).getRoom())>ef.getEvent(i).getAttendees().size()){
                temp.add(i);
            }
        }
        return temp;
    }

    // pass in a copy of lst
    private static ArrayList<Integer> filterEvents(ArrayList<Integer> lst, String filter, String restriction, EventFactory ef) throws ParseException {
        getSchedule gs = new getSchedule();
        if(filter.equals("Day")) {
            return gs.getScheduleByDay(restriction, lst, ef);
        } else if (filter.equals("Speaker")) {
            return gs.getScheduleBySpeaker(restriction, lst, ef);
        } else {
            return gs.getScheduleByTime(restriction, lst, ef);
        }
    }

    private static void sortEvents(ArrayList<Integer> lst, String sort){
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

}
