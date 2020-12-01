package Usecase;

import Entity.*;
import Usecase.Sorter.EventSpeakerSorter;

import java.util.ArrayList;
import java.util.List;

public class Schedule {

    // This class basically returns a schedule (an Arraylist of events that we want to display to users)
    // according to different restrictions

    // Need another class to print out these events ... maybe in the Presenter layer?

    // Probably want to use Strategy pattern here again if that would be easier for printing

//    public ArrayList<Event> getScheduleByDay(){
//
//    }
    // need different days of the events

    public ArrayList<Event> getScheduleBySpeaker(String speaker, EventManager eventManager,
                                                 EventSpeakerSorter speakerSorter){
        speakerSorter.sort(eventManager.allEvents);
        ArrayList<Event> scheduleList = new ArrayList<>();
        for (int i = 0; i < eventManager.allEvents.size(); i++){
            Event e = eventManager.allEvents.get(i);
            List<String> speakerList = e.getSpeaker();
            if (speakerList.size() != 0){
                for (String s : speakerList) {
                    if (s.equals(speaker)) {
                        scheduleList.add(e);
                    }
                }
            }
        }
        return scheduleList;
    }

//    public ArrayList<Event> getScheduleByTime(){
//
//    }
    // need different days of the events

    public ArrayList<Event> getScheduleSignedUp(User user, EventManager eventManager){
        ArrayList<Event> scheduleList = new ArrayList<>();
        ArrayList<Integer> eventIdList = user.eventList;
        // Using a method from class User to get the list of event ids that this user has signed up for
        for (int i = 0; i < eventManager.allEvents.size(); i++){
            Event e = eventManager.allEvents.get(i);
            for (Integer integer : eventIdList) {
                if (e.getId() == integer) {
                    scheduleList.add(e);
                }
            }
        }
        return scheduleList;
    }

}
