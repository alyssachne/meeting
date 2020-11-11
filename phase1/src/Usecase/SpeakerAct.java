package Usecase;

import Entity.Speaker;

public class SpeakerAct {
    public void createSpeaker(String name, String username, String password){
        Speaker speaker = new Speaker(name, username, password);
    }
}
