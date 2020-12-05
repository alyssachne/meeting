package Usecase.Schedule;

import Entity.*;
import Usecase.EventManager;

import java.util.ArrayList;

public class GetScheduleSignUp {

    public ArrayList<Event> getSchedule(User user, EventManager eventManager){
        ArrayList<Event> scheduleList = new ArrayList<>();
        ArrayList<Integer> eventIdList = user.eventList;
        // Using a method from class User to get the list of event ids that this user has signed up for
        for (Integer integer : eventIdList) {
            Event e = eventManager.getEvent(integer);
            scheduleList.add(e);
            // Get the event with corresponding ids in the eventIdList and add it to the scheduleList
        }
        return scheduleList;
    }
}
