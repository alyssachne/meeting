package Controller;

import Controller.Sorter.*;
import Usecase.ActFactory;
import Usecase.EventFactory;
import Usecase.RoomManager;

import java.io.Serializable;
import java.util.ArrayList;
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
            int i = 0;
            while (i < af.allEventList(username).get(date).size()) {
                int j = ef.getEvent(af.allEventList(username).get(date).get(i)).getDuration();
                while (j > 1) {
                    j--;
                    i++;
                }
                System.out.println(ef.getEvent(af.allEventList(username).get(date).get(i)).toString());
                i++;
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
     */
    public static void attendeeSchedule(ActFactory af, String username, EventFactory ef, String strategy,
                                        Map<String, String> filter){
        ArrayList<Integer> temp = new ArrayList<>(af.getEvents(username));
        sortAndFilter(strategy, temp, filter, ef);
        int i = 0;
        while (i < temp.size()) {
            System.out.println(ef.getEvent(temp.get(i)).toString());
            i++;
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
     */
    public static void getAvailableEvent(RoomManager rm, String username, String strategy,Map<String, String> filter,
                                         EventFactory ef, ActFactory af){
        ArrayList<Integer> all = availableEvent(rm,username,ef,af);
        int i = 0;
        while(i< sortAndFilter(strategy,all,filter,ef).size()){
            System.out.println(ef.getEvent(all.get(i)).toString());
            i++;
        }
    }

    /**
     * Get the events the user liked.
     * @param username The username of the attendee.
     * @param strategy The sorter strategy.
     * @param filter The map of the filter of the schedule.
     * @param af ActFactory in Use case.
     * @param ef EventFactory in Use case.
     */
    public static void getLikedEvents(String username, String strategy, Map<String, String> filter, ActFactory af,
                                      EventFactory ef){
        for(int i:sortAndFilter(strategy, af.checkLikedEvents(username),filter,ef)){
            System.out.println(ef.getEvent(i));
        }

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
        } else{
            System.out.println("Invalid input");
        }
        return lst;
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
     */
    private static ArrayList<Integer> sortAndFilter(String strategy, ArrayList<Integer> lst, Map<String, String> filter, EventFactory ef){
        getSchedule gs = new getSchedule();
        for (String f: filter.keySet()) {
            if(f.equalsIgnoreCase("Date")) {
                return sortEvent(strategy, gs.getScheduleByDay(filter.get(f), lst, ef), ef);
            } else if (f.equalsIgnoreCase("Speaker")) {
                return sortEvent(strategy, gs.getScheduleBySpeaker(filter.get(f), lst, ef), ef);
            } else if(f.equalsIgnoreCase("Time")){
                return sortEvent(strategy, gs.getScheduleByTime(filter.get(f), lst, ef), ef);
            } else{
                System.out.println("Invalid input");
            }
        }
        return sortEvent(strategy,lst,ef);
    }


}
