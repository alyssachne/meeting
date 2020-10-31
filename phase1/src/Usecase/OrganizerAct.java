package Usecase;
import Entity.*;

public class OrganizerAct {

    public void createOrganizer(String name, String username, String password){
        Organizer organizer = new Organizer(name, username, password);
    }

    public void createSpeaker(String name, String username, String password){
        Speaker speaker = new Speaker(name, username, password);
    }


}
