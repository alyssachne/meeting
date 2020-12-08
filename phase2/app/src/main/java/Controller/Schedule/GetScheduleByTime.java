package Controller.Schedule;

import Entity.*;
import Usecase.EventManager;

import java.util.ArrayList;
import java.util.Date;

public class GetScheduleByTime {

    public ArrayList<Event> getSchedule(String time, ArrayList<Event> lst){
        lst.removeIf(e -> !(e.getTime() == Integer.parseInt(time)));
        return lst;
    }
}
