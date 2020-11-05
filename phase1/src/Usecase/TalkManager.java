package Usecase;

import Entity.*;

import java.util.ArrayList;

public class TalkManager {

    ArrayList<Talk> allTalks;

    /**
     * Creates an empty talk manager.
     */
    public TalkManager() {
        allTalks = new ArrayList<>();
    }
    /**
     * Adds a given talk to this talk manager.
     * @param t: the talk being added to the ArrayList.
     */
    public void addTalk(Talk t) {
        allTalks.add(t);
    }


    public void createTalk(int id, double duration){
        Talk talk = new Talk(id, duration);
    }

    public Talk getTalk(int id) {
        try {
            for (Talk t: allTalks) {
                if(t.getId() == id) {
                    return t;
                }
            }
        }
        catch (Exception e) {
            System.out.println("This talk does not exist.");
        }
        return null;
    }

    public boolean addAttendee(int talkId, Attendee attendee) {
        Talk talk = getTalk(talkId);
        return talk.addAttendee(attendee);}

    public boolean cancelSpot(int talkId, Attendee attendee) {
        Talk talk = getTalk(talkId);
        return talk.removeAttendee(attendee);
    }

}
