package Usecase;

import Entity.*;
import org.omg.CORBA.Any;

import java.util.ArrayList;

public class SpeakerAct {
    public void createSpeaker(String name, String username, String password, ArrayList<Event> speaks){
        Speaker speaker = new Speaker(name, username, password);
    }
    public boolean sentMessageToAttendee(Speaker speaker, ArrayList<Event> speaks, Message message){
        // Exception needed here
        speaker.sendMessage(message);
        for (Event speak : speaks){
            if (speaker.getSpeaks().contains(speak)){
                return false;
            }
            for (String user :speak.ListOfAttendees){
                // user receive messages

            }
        }
        return true;
    }

//    private void sentMessage(User user, ArrayList<User> receivers, Message message){
//        //Can we import Userlist from UserOragnizer(Use case)？
//        for (User receiver: receivers){
//            receiver.receiveMessage(message);
//        }
//        user.sendMessage(message);
//    }

    public void createSpeaker(String name, String username, String password) {
        Speaker speaker = new Speaker(name, username, password);
    }
}
