package Controller;

import Entity.Event;
import Usecase.EventFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class getSchedule {
    public getSchedule() {

    }

    public ArrayList<Integer> getScheduleBySpeaker (String speaker, ArrayList<Integer> lst, EventFactory ef) {
        ArrayList<Integer> scheduleList = new ArrayList<>();
        for (Integer i:lst) {
            if (ef.getEvent(i).getSpeaker().contains(speaker)) {
                scheduleList.add(i);
            }
        }
        return scheduleList;
    }

    public ArrayList<Integer> getScheduleByDay(String date, ArrayList<Integer> lst,EventFactory ef) throws ParseException {
        ArrayList<Integer> scheduleList = new ArrayList<>();
        Date date1 =new SimpleDateFormat("dd/MM/yyyy").parse(date);
        for (Integer i:lst) {
            if (ef.getEvent(i).getDate() == date1) {
                scheduleList.add(i);
            }
        }
        return scheduleList;
    }

    public ArrayList<Integer> getScheduleByTime (String time, ArrayList<Integer> lst, EventFactory ef) {
        ArrayList<Integer> scheduleList = new ArrayList<>();
        for (Integer i:lst) {
            if (ef.getEvent(i).getTime()==Integer.parseInt(time)) {
                scheduleList.add(i);
            }
        }
        return scheduleList;
    }

}
