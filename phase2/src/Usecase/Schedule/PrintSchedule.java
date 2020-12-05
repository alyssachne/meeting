package Usecase.Schedule;

import Usecase.DiscussionManager;
import Usecase.EventManager;
import Usecase.PartyManager;
import Usecase.TalkManager;

import Entity.Event;
import Entity.User;

import java.util.ArrayList;

public class PrintSchedule {
    // This class should actually be in the Presenter layer or something similar since its function is to print
    // the information of meetings in the list of meetings to the screen

    public void printScheduleBySpeaker(GetScheduleBySpeaker speakerSchedule, String speaker, PartyManager partymanager,
                                       TalkManager talkmanager, DiscussionManager discussionmanager){
        ArrayList<Event> scheduleList = speakerSchedule.getSchedule(speaker, partymanager,
                talkmanager, discussionmanager);
        for (Event e : scheduleList) {
            System.out.println("Id" + e.getId() + "Title" + e.getTitle() + "Time" + e.getTime() +
                    "Duration" + e.getDuration() + "Speaker(s)" + e.getSpeaker());
        }
    }

    public void printScheduleSignUp(GetScheduleSignUp signUpSchedule, User user, EventManager eventManager){
        ArrayList<Event> scheduleList = signUpSchedule.getSchedule(user, eventManager);
        for (Event e : scheduleList) {
            System.out.println("Id" + e.getId() + "Title" + e.getTitle() + "Time" + e.getTime() +
                    "Duration" + e.getDuration() + "Speaker(s)" + e.getSpeaker());
        }
    }

    public void printScheduleLiked(GetScheduleLiked likedSchedule, User user, EventManager eventManager){
        ArrayList<Event> scheduleList = likedSchedule.getSchedule(user, eventManager);
        for (Event e : scheduleList) {
            System.out.println("Id" + e.getId() + "Title" + e.getTitle() + "Time" + e.getTime() +
                    "Duration" + e.getDuration() + "Speaker(s)" + e.getSpeaker());
        }
    }
}
