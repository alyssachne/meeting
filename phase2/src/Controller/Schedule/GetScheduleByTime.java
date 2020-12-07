package Controller.Schedule;

import Entity.*;
import Usecase.EventManager;

import java.util.ArrayList;
import java.util.Date;

public class GetScheduleByTime {

    public ArrayList<Event> getSchedule(Integer time, ArrayList<Event> lst){
        ArrayList<Event> scheduleList = new ArrayList<>();
        for (Event e: lst) {
            if (e.getTime() == time) {
                scheduleList.add(e);
            }
        }
        return scheduleList;
    }
}
