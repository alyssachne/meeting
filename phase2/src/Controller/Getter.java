package Controller;

import Controller.Sorter.*;
import Entity.*;
import Usecase.*;
import java.util.*;

public class Getter {

    /**
     * printout all speakers and their available times on the selected date.
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
     * printout all rooms and their available times.
     */
    public static void roomList(Date date, RoomManager rm, int maxCapacity, List<String> constraints,
                                CalendarManager cm){
        for(int id: rm.suggestedRooms(maxCapacity, constraints)) {
            System.out.println("Room " + id + " ：");
            cm.getAvailable(date, rm.seeSchedule(id, date));
        }
    }

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

    public static void enrollmentStatistics(EventFactory ef,RoomManager rm){
        ArrayList<Integer> eventArrayList = ef.getAllEvents();
        PercentageSorter a = new PercentageSorter();
        a.sort(eventArrayList,ef,rm);
        System.out.println("most Attendee chose" + eventArrayList.get(0) );
    }
}
