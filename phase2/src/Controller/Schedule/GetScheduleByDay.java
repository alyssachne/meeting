package Controller.Schedule;

import Entity.*;
import Usecase.EventManager;

import java.util.*;

public class GetScheduleByDay {

    public ArrayList<Event> getSchedule(Date date, ArrayList<Event> lst){
        ArrayList<Event> scheduleList = new ArrayList<>();
        for (Event e:lst) {
            if (e.getDate() == date) {
                scheduleList.add(e);
            }
        }
        return scheduleList;
    }

}
