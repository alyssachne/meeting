package Controller;

import Controller.getSchedule;
import Controller.Sorter.*;
import Entity.Event;
import Usecase.*;

import java.io.Serializable;
import java.text.ParseException;
import java.util.*;

public class ScheduleGetter implements Serializable {

    /**
     * printout all events this speaker is going to give. This schedule is only shown to the speaker.
     */
    public static void speakerSchedule(ActFactory af, String username, EventFactory ef) {
        for (Date date : af.allEventList(username).keySet()) {
            System.out.println("On " + date);
            for (int id : af.allEventList(username).get(date)) {
                //sa.eventList(username) is arraylist of eventIds
                System.out.println(ef.getEvent(id).toString());
            }
        }
    }

    /**
     * printout selected events this attendee signed up for in the order this person wants. T
     * his schedule is only shown to this attendee.
     */
    public static void attendeeSchedule(ActFactory af, String username, EventFactory ef, String strategy,
                                        Map<String, String> filter) throws ParseException {
        ArrayList<Integer> temp = new ArrayList<>(af.getEvents(username));
        sortAndFilter(strategy,temp,filter,ef);
        for(int i: temp) {
            System.out.println(ef.getEvent(i).toString());
        }

    }

    /**
     * printout all events this attendee can signed up for. That is, events that are not full, the rooms where those
     * events take place haven't reach the the rooms' maximum capacity, and this attendee hasn't sign up for the event,
     * yet.
     */
    public static void getAvailableEvent(RoomManager rm, String username, String strategy,Map<String, String> filter,
                                         EventFactory ef, ActFactory af) throws ParseException {
        ArrayList<Integer> all = availableEvent(rm,username,ef,af);
        sortAndFilter(strategy,all,filter,ef);
        for(Integer i: all) {
            System.out.println(ef.getEvent(i).toString());}
    }


    public static void getAllSelectedEvents(String strategy, Map<String, String> filter, EventFactory ef) throws ParseException {
        sortAndFilter(strategy,ef.getAllEvents(), filter, ef);
    }

    public static void getLikedEvents(String username, String strategy, Map<String, String> filter, ActFactory af,
                                      EventFactory ef) throws ParseException {
        sortAndFilter(strategy, af.checkLikedEvents(username),filter,ef);
    }

    public static boolean hasAvailableEvent(EventFactory ef, String username, RoomManager rm, ActFactory af){
        return !availableEvent(rm,username, ef, af).isEmpty();
    }

    private static void sortEvent(String strategy, ArrayList<Integer> lst, EventFactory ef){
        if(strategy.equals("EventId")){
            SorterStrategy sorter = new EventIdSorter();
            sorter.sort(lst,ef);
        } else if (strategy.equals("RoomId")){
            SorterStrategy sorter = new EventRoomSorter();
            sorter.sort(lst,ef);
        } else if(strategy.equals("Speaker")){
            SorterStrategy sorter = new EventSpeakerSorter();
            sorter.sort(lst,ef);
        }else if(strategy.equals("Time")){
            SorterStrategy sorter = new EventTimeSorter();
            sorter.sort(lst,ef);
        }else if(strategy.equals("Title")){
            SorterStrategy sorter = new EventTitleSorter();
            sorter.sort(lst,ef);
        }
        for(Integer event: lst) {
            System.out.println(ef.getEvent(event).toString());
        }
    }
    private static ArrayList<Integer> availableEvent(RoomManager rm, String username, EventFactory ef, ActFactory af){
        //events that are not full
        ArrayList<Integer> temp = new ArrayList<>();
        for (Integer i: ef.getAllEvents()){
            //check if the attendee has signed up the event or not and if the event reaches its room's maxCapacity
            if ((!ef.getEvent(i).getAttendees().contains(username))&&
                    rm.getMaxCapacity(ef.getEvent(i).getRoom())>ef.getEvent(i).getNumOfAttendees()&&
            af.checkAccess(username).equals(ef.checkAccess(i))){
                temp.add(i);
            }
        }
        return temp;
    }

    // pass in a copy of lst
    private static void sortAndFilter(String strategy, ArrayList<Integer> lst, Map<String, String> filter, EventFactory ef) throws ParseException {
        getSchedule gs = new getSchedule();
        for (String f: filter.keySet()) {
            if(f.equals("Day")) {
                sortEvent(strategy, gs.getScheduleByDay(filter.get(f), lst, ef), ef);
            } else if (f.equals("Speaker")) {
                sortEvent(strategy, gs.getScheduleBySpeaker(filter.get(f), lst, ef), ef);
            } else {
                sortEvent(strategy, gs.getScheduleByTime(filter.get(f), lst, ef), ef);
            }
        }
    }


}
