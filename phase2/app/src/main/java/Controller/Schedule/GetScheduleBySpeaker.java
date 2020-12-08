package Controller.Schedule;

import Entity.*;
import Usecase.DiscussionManager;
import Usecase.PartyManager;
import Usecase.TalkManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetScheduleBySpeaker {

    public ArrayList<Event> getSchedule (String speaker, ArrayList<Event> lst) {
        lst.removeIf(e -> !(e.getSpeaker().contains(speaker)));
        return lst;
    }
}
