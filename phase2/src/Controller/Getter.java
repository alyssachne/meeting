package Controller;

import Controller.Sorter.*;
import Entity.*;
import Usecase.*;

import java.io.Serializable;
import java.util.*;

/**
 * This class get various information for different features of the program.
 */

public class Getter implements Serializable {

    /**
     * Print out all speakers and their available times on the selected date.
     * @param af ActFactory in Use case.
     * @param date The date to check.
     * @param cm CalendarManager in Use case.
     */
    public static void speakerAvailable(ActFactory af, Date date, Usecase.CalendarManager cm){
        for(String s: af.speakerList()) {
            System.out.println(s);
            for(Integer time: cm.getAvailable(date, af.getEvents(s))) {
                System.out.println(time);
            }
        }
    }

    /**
     * Print out all rooms and their available times.
     * @param date The date to check.
     * @param rm RoomManager in Use case.
     * @param maxCapacity The max capacity of the room.
     * @param constraints The constraints of the room.
     * @param cm CalendarManager in Use case.
     */
    public static void roomList(Date date, RoomManager rm, int maxCapacity, List<String> constraints,
                                CalendarManager cm){
        for (int id : rm.suggestedRooms(maxCapacity, constraints)) {
            System.out.println("Room " + id + " ：");
            for (Integer time : cm.getAvailable(date, rm.seeSchedule(id, date))) {
                System.out.println(time);
            }
        }
    }

    /**
     * Check whether there are rooms available for the event.
     * @param rm RoomManager in Use case.
     * @param maxCapacity The max capacity of the room.
     * @param constraints The constraints of the room.
     * @return The boolean value of whether there are rooms available for the event.
     */
    public static boolean hasAvailableRoom(RoomManager rm, int maxCapacity, List<String> constraints){
        return !rm.suggestedRooms(maxCapacity, constraints).isEmpty();
    }

    /**
     * Get the top five events according to the number of enrollment.
     * @param ef EventFactory in Use case.
     */
    public static void getTopFiveEvents(EventFactory ef){
        ArrayList<Integer> eventArrayList = ef.getAllEvents();
        EventEnrollmentSorter a = new EventEnrollmentSorter();
        a.sort(eventArrayList,ef);
        while (eventArrayList.size() > 5){
            eventArrayList.remove(-1);
        }
        HashMap<String,Integer> hashMap = new HashMap<>();
        for (int event: eventArrayList){
            hashMap.put(ef.getEvent(event).getId()+ ". " + ef.getEvent(event).getTitle(), ef.getEvent(event).getNumOfAttendees());
        }
        if(eventArrayList.size()<5){
            System.out.println("There is only" + eventArrayList.size() +"events and the top-list is" );
        }
        System.out.println(hashMap);

    }

    /**
     * Get the traffic of the app.
     * @param ef EventFactory in Use case.
     * @param rm RoomManager in Use case.
     */
    public static void getAppTraffic(EventFactory ef, RoomManager rm){
        ArrayList<Integer> eventArrayList = ef.getAllEvents();
        PercentageSorter a = new PercentageSorter();
        a.sort(eventArrayList, ef, rm);
        HashMap<String, Float> TrafficMap= new HashMap<>();
        for (int event: eventArrayList){
            TrafficMap.put(ef.getEvent(event).getId()+ ". " + ef.getEvent(event).getTitle(),
                    (float)ef.getEvent(event).getNumOfAttendees()/rm.getRoom(ef.getEvent(event).getRoom()).getMaxCapacity());
        }
        System.out.println(TrafficMap);
    }

    /**
     * Get the enrollment statistics of the events.
     * @param ef EventFactory in Use case.
     * @param rm RoomManager in Use case.
     */
    public static void enrollmentStatistics(EventFactory ef,RoomManager rm){
        ArrayList<Integer> eventArrayList = ef.getAllEvents();
        PercentageSorter a = new PercentageSorter();
        a.sort(eventArrayList,ef,rm);
        System.out.println("most Attendee chose" + eventArrayList.get(0) );
    }
}
