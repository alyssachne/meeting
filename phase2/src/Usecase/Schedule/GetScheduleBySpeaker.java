package Usecase.Schedule;

import Entity.*;
import Usecase.DiscussionManager;
import Usecase.PartyManager;
import Usecase.TalkManager;

import java.util.ArrayList;
import java.util.List;

public class GetScheduleBySpeaker {

    public ArrayList<Event> getSchedule (String speaker, PartyManager partymanager, TalkManager talkmanager, DiscussionManager discussionManager){

        ArrayList<Event> scheduleList = new ArrayList<>();

        if (speaker.equals("")){
            scheduleList.addAll(partymanager.allParties);
        }
        // Parties do not have speakers, so we want to return all the parties if and only if the input is
        // an empty string

        for (int i = 0; i < talkmanager.allTalks.size(); i++){
            Event e = talkmanager.allTalks.get(i);
            List<String> speakerList = e.getSpeaker();
            if (speakerList.size() != 0){
                for (String s : speakerList) {
                    if (s.equals(speaker)) {
                        scheduleList.add(e);
                    }
                }
            }
        }
        // Adding the talks with the speaker

        for (int i = 0; i < discussionManager.allDiscussions.size(); i++){
            Event e = discussionManager.allDiscussions.get(i);
            List<String> speakerList = e.getSpeaker();
            if (speakerList.size() != 0){
                for (String s : speakerList) {
                    if (s.equals(speaker)) {
                        scheduleList.add(e);
                    }
                }
            }
        }
        // Adding the discussions with the speaker

        return scheduleList;
    }

}
