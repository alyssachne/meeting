package Usecase;

import Entity.Talk;

import java.util.ArrayList;

public class TalkManager {

    private RoomManager roomManager;
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
        Room room = roomManager.getRoom(talk.roomId);
        return talk.addAttendee(attendee) & room.addAttendee();}

    public boolean cancelSpot(int talkId, Attendee attendee) {
        Talk talk = getTalk(talkId);
        Room room = roomManager.getRoom(talk.roomId);
        return talk.removeAttendee(attendee) & room.removeAttendee();
    }

}
