package Usecase.Schedule;

import Entity.*;
import Usecase.EventManager;

import java.util.ArrayList;
import java.util.List;

public class GetScheduleLiked {

    public ArrayList<Event> getSchedule(User user, EventManager eventManager){
        ArrayList<Event> scheduleList = new ArrayList<>();
        List<Integer> likedList = user.getLikedEvents();
        // Using a method from class User to get the list of event ids that this user has signed up liked
        // Want to make sure if list instead of arraylist in used here
        for (Integer integer : likedList) {
            Event e = eventManager.getEvent(integer);
            scheduleList.add(e);
            // Get the event with corresponding ids in the likedList and add it to the scheduleList
        }
        return scheduleList;
    }
}
