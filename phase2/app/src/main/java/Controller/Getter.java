package Controller;

import Controller.Sorter.*;
import Entity.*;
import Usecase.*;
import java.util.*;

public class Getter {

    /**
     * printout all speakers and their available times on the selected date.
     */
    public static void speakerAvailable(Usecase.SpeakerAct sa, Date date, Usecase.CalendarManager cm){
        for(String s: sa.speakerList()) {
            System.out.println(s);
            for(Integer time: cm.getAvailable(date, sa.getEvents(s))) {
                System.out.println(time);
            }
        }
    }

    /**
     * printout all rooms and their available times.
     */
    public static void roomList(RoomManager rm, int maxCapacity, List<String> constraints){
        rm.suggestedRooms(maxCapacity, constraints);
    }

    public static void getTopFiveEvents(DiscussionManager dm , TalkManager tm, PartyManager pm){
        ArrayList<Event> eventArrayList = getAllEvents(dm,tm,pm);
        EventEnrollmentSorter a = new EventEnrollmentSorter();
        a.sort(eventArrayList);
        while (eventArrayList.size() > 5){
            eventArrayList.remove(-1);
        }
        HashMap<String,Integer> hashMap = new HashMap<>();
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
        HashMap<String, Float> TrafficMap= new HashMap<>();
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
    }

    private static ArrayList<Event> getAllEvents(DiscussionManager dm , TalkManager tm, PartyManager pm){
        ArrayList<Event> eventArrayList = new ArrayList<>();
        eventArrayList.addAll(dm.allDiscussions);
        eventArrayList.addAll(tm.allTalks);
        eventArrayList.addAll(pm.allParties);
        return eventArrayList;
    }
}
