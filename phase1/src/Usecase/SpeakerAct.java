package Usecase;

import Entity.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class SpeakerAct implements Usable, Serializable {

    HashMap<String,Speaker> speakerMap;
    public SpeakerAct(){
        speakerMap = new HashMap<>();
    }
    public void createSpeaker(String name, String username, String password) {
        Speaker speaker = new Speaker(name, username, password);
        speakerMap.put(speaker.getUsername(),speaker);
    }

    public Speaker getSpeaker(String username){
        return speakerMap.get(username);
    }

    public ArrayList<Integer> availableTime(String username){
        Speaker speaker = getSpeaker(username);
        return speaker.available();
    }

    public ArrayList<Integer> eventList(String username){
        Speaker speaker = getSpeaker(username);
        return speaker.eventList();
    }

    public boolean login(String username, String password){
        if (password.equals(getSpeaker(username).getPassword())){
            return true;
        }
        return false;
    }

    public void speakerList(){
        for (String username:speakerMap.keySet()){
            System.out.println(username);
            for (Integer time: availableTime(username)){
                System.out.println(time);
            }
        }
    }
    @Override
    public boolean signUp(String username, int eventId) {
        return getSpeaker(username).signUp(eventId);
    }

    @Override
    public boolean cancelSpot(String username, int eventId) {
        return getSpeaker(username).cancelSpot(eventId);
    }

    public void giveEvent(String username,Integer eventId, Integer time){
        speakerMap.get(username).giveEvent(eventId,time);
    }

    public boolean cancelEvent(Event event, Speaker speaker) {
        return speaker.cancelEvent(event.id);
    }

//    public boolean sentMessageToAttendee(Speaker speaker, ArrayList<Event> speaks, Message message){
//        // Exception needed here
//        speaker.sendMessage(message);
//        for (Event speak : speaks){
//            if (speaker.getSpeaks().contains(speak)){
//                return false;
//            }
//            for (String user :speak.ListOfAttendees){
//                // user receive messages
//
//            }
//        }
//        return true;
//    }

//    private void sentMessage(User user, ArrayList<User> receivers, Message message){
//        //Can we import Userlist from UserOragnizer(Use case)？
//        for (User receiver: receivers){
//            receiver.receiveMessage(message);
//        }
//        user.sendMessage(message);
//    }

}
