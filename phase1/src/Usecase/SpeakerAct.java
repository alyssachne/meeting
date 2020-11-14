package Usecase;

import Entity.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class SpeakerAct implements Usable, Serializable {

    public HashMap<String,Speaker> speakerMap;
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

    public void addMessage(String receiver, String sender, String message){
        getSpeaker(receiver).addMessage(sender,message);
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

    public ArrayList<String> getContacts(String username){
        return getSpeaker(username).getContacts();
    }

    public ArrayList<String> getMessage(String receiver, String sender){
        return getSpeaker(receiver).getMessage(sender);
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
        return speaker.cancelEvent(event.getId());
    }


}
