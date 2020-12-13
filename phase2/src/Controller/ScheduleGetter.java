package Controller;

import Controller.Sorter.*;
import Usecase.ActFactory;
import Usecase.CalendarManager;
import Usecase.EventFactory;
import Usecase.RoomManager;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * The Controller class that can get the schedule of different users in different conditions.
 */
public class ScheduleGetter implements Serializable {

    /**
     * Print out all events this speaker is going to give. This schedule is only shown to the speaker.
     * @param af ActFactory in Use case.
     * @param username The username of the speaker.
     * @param ef EventFactory in Use case.
     */
    public static void speakerSchedule(ActFactory af, String username, EventFactory ef) {
        for (String date : af.allEventList(username).keySet()) {
            System.out.println("On " + date);
            for (int id : af.allEventList(username).get(date)) {
                System.out.println(ef.getEvent(id).toString());
            }
        }
    }

    /**
     * Print out selected events this attendee signed up for in the order this person wants.
     * This schedule is only shown to this attendee.
     * @param af ActFactory in Use case.
     * @param username The username of the attendee.
     * @param ef EventFactory in Use case.
     * @param strategy The sorter strategy.
     * @param filter The map of the filter of the schedule.
     * @throws ParseException Throw an exception if the required format of input is not followed.
     */
    public static void attendeeSchedule(ActFactory af, String username, EventFactory ef, String strategy,
                                        Map<String, String> filter) throws ParseException {
        ArrayList<Integer> temp = new ArrayList<>(af.getEvents(username));
        sortAndFilter(strategy, temp, filter, ef);
        int i = 0;
        while (i < temp.size()) {
            int j = ef.getEvent(i).getDuration();
            while (j > 1) {
                j--;
                i++;
            }
            System.out.println(ef.getEvent(i).toString());
        }
    }

    /**
     * Print out all events this attendee can signed up for. That is, events that are not full, the rooms where those
     * events take place haven't reach the the rooms' maximum capacity, and this attendee hasn't sign up for the event,
     * yet.
     * @param rm RoomManager in Use case.
     * @param username The username of the attendee.
     * @param strategy The sorter strategy.
     * @param filter The map of the filter of the schedule.
     * @param ef EventFactory in Use case.
     * @param af ActFactory in Use case.
     * @throws ParseException Throw an exception if the required format of input is not followed.
     */
    public static void getAvailableEvent(RoomManager rm, String username, String strategy,Map<String, String> filter,
                                         EventFactory ef, ActFactory af) throws ParseException {
        ArrayList<Integer> all = availableEvent(rm,username,ef,af);
        int i = 0;
        while(i< sortAndFilter(strategy,all,filter,ef).size()){
//            int j = ef.getEvent(all.get(i)).getDuration();
//            while(j>1){
//                j--;
//                i++;
//            }
            System.out.println(ef.getEvent(all.get(i)).toString());
            i++;
        }
    }

    /**
     * Get all the selected events.
     * @param strategy The sorter strategy.
     * @param filter The map of the filter of the schedule.
     * @param ef EventFactory in Use case.
     * @throws ParseException Throw an exception if the required format of input is not followed.
     */
    public static void getAllSelectedEvents(String strategy, Map<String, String> filter, EventFactory ef) throws ParseException {
        sortAndFilter(strategy,ef.getAllEvents(), filter, ef);
    }

    /**
     * Get the events the user liked.
     * @param username The username of the attendee.
     * @param strategy The sorter strategy.
     * @param filter The map of the filter of the schedule.
     * @param af ActFactory in Use case.
     * @param ef EventFactory in Use case.
     * @throws ParseException Throw an exception if the required format of input is not followed.
     */
    public static void getLikedEvents(String username, String strategy, Map<String, String> filter, ActFactory af,
                                      EventFactory ef) throws ParseException {
        sortAndFilter(strategy, af.checkLikedEvents(username),filter,ef);
    }

    /**
     * Check whether there are available events for the user.
     * @param ef EventFactory in Use case.
     * @param username The username of the attendee.
     * @param rm RoomManager in Use case.
     * @param af ActFactory in Use case.
     * @return Whether there are available events for the user.
     */
    public static boolean hasAvailableEvent(EventFactory ef, String username, RoomManager rm, ActFactory af){
        return !availableEvent(rm,username, ef, af).isEmpty();
    }

    /**
     * Sort the events according to different strategies.
     * @param strategy The sorter strategy.
     * @param lst The list of event ids.
     * @param ef EventFactory in Use case.
     */
    private static ArrayList<Integer> sortEvent(String strategy, ArrayList<Integer> lst, EventFactory ef){
        if(strategy.equalsIgnoreCase("EventId")){
            SorterStrategy sorter = new EventIdSorter();
            sorter.sort(lst,ef);
        } else if (strategy.equalsIgnoreCase("RoomId")){
            SorterStrategy sorter = new EventRoomSorter();
            sorter.sort(lst,ef);
        } else if(strategy.equalsIgnoreCase("Speaker")){
            SorterStrategy sorter = new EventSpeakerSorter();
            sorter.sort(lst,ef);
        }else if(strategy.equalsIgnoreCase("Time")){
            SorterStrategy sorter = new EventTimeSorter();
            sorter.sort(lst,ef);
        }else if(strategy.equalsIgnoreCase("Title")){
            SorterStrategy sorter = new EventTitleSorter();
            sorter.sort(lst,ef);
        }
        return lst;
//        for(Integer event: lst) {
//            System.out.println(ef.getEvent(event).toString());
//        }
    }

    /**
     * Return the ids of the events available for the user.
     * @param rm RoomManager in Use case.
     * @param username The username of the attendee.
     * @param ef EventFactory in Use case.
     * @param af ActFactory in Use case.
     * @return The ids of the events available for the user.
     */
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

    /**
     * Sort and filter the events.
     * @param strategy The sorter strategy.
     * @param lst The list of event ids.
     * @param filter The map of the filter of the schedule.
     * @param ef EventFactory in Use case.
     * @throws ParseException Throw an exception if the required format of input is not followed.
     */
    private static ArrayList<Integer> sortAndFilter(String strategy, ArrayList<Integer> lst, Map<String, String> filter, EventFactory ef) throws ParseException {
        getSchedule gs = new getSchedule();
        for (String f: filter.keySet()) {
            if(f.equalsIgnoreCase("Date")) {
                return sortEvent(strategy, gs.getScheduleByDay(filter.get(f), lst, ef), ef);
            } else if (f.equalsIgnoreCase("Speaker")) {
                return sortEvent(strategy, gs.getScheduleBySpeaker(filter.get(f), lst, ef), ef);
            } else {
                return sortEvent(strategy, gs.getScheduleByTime(filter.get(f), lst, ef), ef);
            }
        }
        return sortEvent(strategy,lst,ef);
    }


}
