package Usecase;
import Entity.*;
import org.omg.CORBA.Any;

import java.util.ArrayList;
import java.util.Calendar;

public class OrganizerAct {

    public void creatOrganizer(String name, String username, String password, String type){
        new Organizer(name,username,password);
    }

    public boolean setSpeaker(Event talk, String userName, String name){
        talk.setSpeaker(userName, name);
        return true;
    }

    public boolean setTime(Event talk, Integer time){
        talk.setTime(time);
        return true;
    }

    public boolean setLocation(Event talk, int roomId){
        talk.setRoom(roomId);
        return true;
    }

    public boolean sendMessage(Organizer sender,ArrayList<User> receiver, Object content){
        Message newMessage = new Message(sender,receiver,content);
        sender.SentBox.add(newMessage);
        for (int i = 0; i < receiver.size(); i++){
            receiver.get(i).InBox.add(newMessage);
        }
        return true;
    }

//    public void sentMessage(Organizer organizer, ArrayList<User> receivers, Any content){
//        //Can we import Userlist from UserOragnizer(Use case)？
//        Message message = new Message(organizer,receivers,content);
//        for (User receiver: receivers){
//            receiver.receiveMessage(message);
//        }
//        organizer.sendMessage(message);
//    }

}
