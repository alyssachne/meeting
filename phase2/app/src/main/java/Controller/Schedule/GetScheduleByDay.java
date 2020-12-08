package Controller.Schedule;

import Entity.*;
import Usecase.EventManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GetScheduleByDay {

    public ArrayList<Event> getSchedule(String date, ArrayList<Event> lst) throws ParseException {
        Date date1 =new SimpleDateFormat("dd/MM/yyyy").parse(date);
        lst.removeIf(e -> !(e.getDate() == date1));
        return lst;
    }

}
