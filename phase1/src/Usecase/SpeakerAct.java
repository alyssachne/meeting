package Usecase;

import Entity.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class SpeakerAct extends Act implements Serializable {

    public HashMap<String,Speaker> speakerMap;
    public SpeakerAct(){
        speakerMap = new HashMap<>();
    }

    @Override
    public boolean createUser(String name, String username, String password) {
        if(speakerMap.containsKey(username)) {
            return false;
        }
        Speaker speaker = new Speaker(name, username, password);
        speakerMap.put(speaker.getUsername(),speaker);
        return true;
    }

    @Override
    public User getUser(String username) {
        return speakerMap.get(username);
    }

    @Override
    public boolean signUp(String username, Integer eventId) {
        ArrayList<Integer> given = getUser(username).getSignUp();
        if(getUser(username).eventList.contains(eventId) || given.contains(eventId)) {
            return false;
        }
        getUser(username).eventList.add(eventId);
        return true;
    }

    public ArrayList<Integer> availableTime(String username){
        Speaker speaker = (Speaker) getUser(username);
        return speaker.getAvailable();
    }

    public ArrayList<Integer> eventList(String username){
        Speaker speaker = (Speaker) getUser(username);
        return speaker.getGiveEvents();
    }

    public void speakerList(){
        for (String username:speakerMap.keySet()){
            System.out.println(username);
            for (Integer time: availableTime(username)){
                System.out.println(time);
            }
        }
    }

    public boolean giveEvent(String username,Integer eventId, Integer time){
        Speaker speaker = (Speaker) getUser(username);
        if(speaker.getAvailable().contains(time)) {
            speaker.events.put(time, eventId);
            return true;
        }
        return false;
    }

    public boolean cancelEvent(String username, Integer eventId) {
        Speaker speaker = (Speaker) getUser(username);
        Set<Integer> temp = speaker.events.keySet();
        for(Integer time: temp) {
            if(speaker.events.get(time).equals(eventId)) {
                speaker.events.replace(time, null);
                return true;
            }
        }
        return false;
    }

}
