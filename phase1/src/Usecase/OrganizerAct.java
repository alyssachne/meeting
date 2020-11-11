package Usecase;
import Entity.*;
import org.omg.CORBA.Any;

import java.util.ArrayList;

public class OrganizerAct {

    public void createOrganizer(String name, String username, String password){
        Organizer organizer = new Organizer(name, username, password);
    }

//    public void createSpeaker(String name, String username, String password){
//        Speaker speaker = new Speaker(name, username, password);
//    }

    public void sentMessage(Organizer organizer, ArrayList<User> receivers, Any content){
        //Can we import Userlist from UserOragnizer(Use case)？
        Message message = new Message(organizer,receivers,content);
        for (User receiver: receivers){
            receiver.receiveMessage(message);
        }
        organizer.sendMessage(message);
    }

}
