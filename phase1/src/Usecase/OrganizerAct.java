package Usecase;
import Entity.*;
import org.omg.CORBA.Any;

import java.util.ArrayList;

public class OrganizerAct {

    public void creatUser(String name, String Username, String password, String type){
        if (type.equals('Attendee')){
            new Attendee();
        }
        if(type.equals('Speaker')){
            new Speaker();
        }
        if (type.equals('Organizer')){
            new Organizer();
        }
        else{
            new User();
        }
    }

    public boolean setSpeaker(Event talk, Speaker speaker){
        talk.setSpeaker(speaker);
        return True;
    }

    public boolean setTime(Event talk, Calendar time){
        talk.setTime(time);
        return Ture;
    }

    public boolean setLocation(Event talk, int roomId){
        talk.setRoom(roomId);
        return True;
    }

    public boolean sendMessage(ArrayList<User> receiver, Object content){
        newMessage = new Message(this,receiver,content);
        this.outbox.add(newMessage);
        for (int i = 0; i < receiver.length; i++){
            receiver[i].inbox.add(newMessage);
        }
        return True;
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
