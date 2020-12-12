package Controller;

import Usecase.EventFactory;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
     * The class contains methods that get schedule according to different conditions.
     */

public class getSchedule implements Serializable {

        /**
         * Class constructor.
         */
    public getSchedule() {

    }

        /**
         * Get the list of ids of the events that contain certain speaker.
         * @param speaker The speaker's name.
         * @param lst The arraylist of ids of all the events.
         * @param ef EventFactory in use case.
         * @return The list of ids of the events that contain certain speaker.
         */
    public ArrayList<Integer> getScheduleBySpeaker (String speaker, ArrayList<Integer> lst, EventFactory ef) {
        ArrayList<Integer> scheduleList = new ArrayList<>();
        for (Integer i:lst) {
            if (ef.containSpeaker(i,speaker)) {
                scheduleList.add(i);
            }
        }
        return scheduleList;
    }

        /**
         * Get the list of ids of the events that take place on certain day.
         * @param date The date when the event takes place.
         * @param lst The arraylist of ids of all the events.
         * @param ef EventFactory in use case.
         * @return The list of ids of the events that take place on certain day.
         * @throws ParseException Thrown when the input string does not follow the specific format of the date.
         */
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

        /**
         * Get the list of ids of the events that starts at certain time (can be on different days).
         * @param time The time when the events start.
         * @param lst The arraylist of ids of all the events.
         * @param ef EventFactory in use case.
         * @return The list of ids of the events that starts at certain time.
         */
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
