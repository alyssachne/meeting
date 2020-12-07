package Controller;

import Entity.Event;
import Usecase.DiscussionManager;
import Usecase.PartyManager;
import Controller.Sorter.EventEnrollmentSorter;
import Controller.Sorter.PercentageSorter;
import Usecase.TalkManager;

import java.util.ArrayList;
import java.util.HashMap;

public class StatisticGetter {

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

    private static ArrayList<Event> getAllEvents(DiscussionManager dm , TalkManager tm, PartyManager pm){
        ArrayList<Event> eventArrayList = new ArrayList<>();
        eventArrayList.addAll(dm.allDiscussions);
        eventArrayList.addAll(tm.allTalks);
        eventArrayList.addAll(pm.allParties);
        return eventArrayList;
    }
}
