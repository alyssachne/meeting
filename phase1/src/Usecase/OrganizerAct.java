package Usecase;
import Entity.*;
import org.omg.CORBA.Any;

import java.util.ArrayList;

public class OrganizerAct {

    public void creatOrganizer(String name, String username, String password, String type){
        new Organizer(name,username,password);
    }

    public boolean setSpeaker(Event talk, Speaker speaker){
        talk.setSpeaker(speaker.username, speaker.name);
        return true;
    }

    public boolean setTime(Event talk, Calendar time){
        talk.setTime(time);
        return true;
    }

    public boolean setLocation(Event talk, int roomId){
        talk.setRoom(roomId);
        return true;
    }

    public boolean sendMessage(ArrayList<User> receiver, Object content){
        newMessage = new Message(this,receiver,content);
        this.outbox.add(newMessage);
        for (int i = 0; i < receiver.length; i++){
            receiver[i].inbox.add(newMessage);
        }
        return true;
    }

    public void sentMessage(Organizer organizer, ArrayList<User> receivers, Any content){
        //Can we import Userlist from UserOragnizer(Use case)？
        Message message = new Message(organizer,receivers,content);
        for (User receiver: receivers){
            receiver.receiveMessage(message);
        }
        organizer.sendMessage(message);
    }

}
