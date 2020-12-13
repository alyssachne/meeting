package Controller;

import Controller.Sorter.EventEnrollmentSorter;
import Controller.Sorter.PercentageSorter;
import Usecase.ActFactory;
import Usecase.CalendarManager;
import Usecase.EventFactory;
import Usecase.RoomManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
    public static void speakerAvailable(ActFactory af, String date, Usecase.CalendarManager cm){
        for(String s: af.speakerList()) {
            System.out.println(s);
            for(Date time: cm.getAvailable(date, af.getEvents(s))) {
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
    public static void roomList(String date, RoomManager rm, int maxCapacity, List<String> constraints,
                                CalendarManager cm){
        for (int id : rm.suggestedRooms(maxCapacity, constraints)) {
            System.out.println("Room " + id + " ：");
            for (Date time : cm.getAvailable(date, rm.seeSchedule(id, date))) {
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
        int i = 1;
        for (int event: eventArrayList){
            hashMap.put(i + ". event " + ef.getEvent(event).getId() + " [" +ef.getEvent(event).getTitle() + "] ",
                    ef.getEvent(event).getNumOfAttendees());
        }
        if(eventArrayList.isEmpty()){
            System.out.println("There is no event in the system.");
        }else if(eventArrayList.size()<5){
            System.out.println("There is only " + eventArrayList.size() +" events in the system and the top-list is" );
            System.out.println(hashMap);
        }else{
            System.out.println(hashMap);
        }
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
        int i = 1;
        for (int event: eventArrayList){
            TrafficMap.put(i + ". event " + ef.getEvent(event).getId() + " [" + ef.getEvent(event).getTitle() + "] ",
                    (float) (ef.getEvent(event).getNumOfAttendees()/rm.getRoom(ef.getEvent(event).getRoom()).getMaxCapacity()));
            i++;
        }
        if(TrafficMap.isEmpty()){
            System.out.println("There is no app traffic data in the system.");
        }else{
            System.out.println(TrafficMap);
        }
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
        if(eventArrayList.isEmpty()){
            System.out.println("There is no event in the system");
        }else{
            System.out.println("Most user chose" + ef.getEvent(eventArrayList.get(0)) );
        }
    }
}
