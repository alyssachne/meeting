package Controller;

import Controller.Schedule.GetScheduleBySpeaker;
import Entity.Event;
import Entity.Talk;
import Usecase.*;

import java.util.ArrayList;
import java.util.List;

public class EventDealer {

    /**
     * Signup this attendee to the event.
     * @param eventId The Id of the event this attendee wants to sign up for.
     */
    public static void signUp(int eventId,AttendeeAct aa,EventManager em,String username){
        if (aa.checkAccess(username).equals(em.checkAccess(eventId))) {
            aa.signUp(username,eventId);
            em.addAttendee(username,eventId);
        } else {
            System.out.println("This event is not open to you.");
        }

    }
//
//    public static void seeEvents_Filter(String filter, PartyManager pm,TalkManager tm,DiscussionManager dm) {
//        if (filter == "Speaker") {
//            List<Integer> scheduleList = new ArrayList<Integer>();
//
//            for (int i = 0; i < tm.allTalks.size(); i++){
//                Event e = tm.allTalks.get(i);
//                List<String> speakerList = e.getSpeaker();
//                if (speakerList.size() != 0){
//                    for (String s : speakerList) {
//                        if (s.equals(speaker)) {
//                            scheduleList.add(e);
//                        }
//                    }
//                }
//            }
//            // Adding the talks with the speaker
//
//            for (int i = 0; i < discussionManager.allDiscussions.size(); i++){
//                Event e = discussionManager.allDiscussions.get(i);
//                List<String> speakerList = e.getSpeaker();
//                if (speakerList.size() != 0){
//                    for (String s : speakerList) {
//                        if (s.equals(speaker)) {
//                            scheduleList.add(e);
//                        }
//                    }
//                }
//            }
//            // Adding the discussions with the speaker
//
//            return scheduleList;
//        }
//    }

    /**
     * Remove this attendee from the event.
     * @param eventId The Id of the event this attendee wants to be removed from.
     */
    public static void cancelSpot(int eventId, AttendeeAct aa, EventManager em, String username){
        aa.removeFromEvent(username,eventId);
        em.cancelSpot(username,eventId);
    }

    public static void cancelEvent(int eventId, EventManager em, SpeakerAct sa, AttendeeAct aa, RoomManager rm) {
        em.cancelEvent(eventId);
        for(String s: em.getEvent(eventId).getSpeaker()) {
            sa.cancelEvent(s, eventId);
        }
        for(String a: em.getEvent(eventId).getAttendees()) {
            aa.removeFromEvent(a, eventId);
        }
        rm.cancel(em.getEvent(eventId).getRoom(), eventId);
    }

    public static void changeAccess(int eventId, EventManager em, String access) {
        if(!em.changeAccess(eventId,access)){
            System.out.println("This is access is the same as the current access.");
        }
    }
}
