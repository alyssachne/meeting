package Usecase;
import Entity.*;
import org.omg.CORBA.Any;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class OrganizerAct implements Usable, Serializable {

    HashMap<String,Organizer> organizerMap;

    public OrganizerAct(){
        organizerMap = new HashMap<>();
    }

    public void createOrganizer(String name, String username, String password){
        Organizer organizer = new Organizer(name,username,password);
        organizerMap.put(organizer.getUsername(),organizer);
    }

    public boolean login(String username, String password){
        if (password.equals(getOrganizer(username).getPassword())){
            return true;
        }
        return false;
    }

    public Organizer getOrganizer(String username){
        return organizerMap.get(username);
    }

    @Override
    public boolean signUp(String username, int eventId) {
        return getOrganizer(username).signUp(eventId);
    }

    @Override
    public boolean cancelSpot(String username, int eventId) {
        return getOrganizer(username).cancelSpot(eventId);
    }

//    public boolean setSpeaker(Event event, Speaker speaker){
//        ArrayList<Integer> temp = speaker.available();
//        if(temp.size() == 0) {
//            return false;
//        }
//        Integer time = temp.get(0);
//        event.setTime(time);
//        return true;
//    }
//
//    public boolean setTime(Event event, Integer time, Speaker speaker){
//        if(time.equals(event.time)) {
//            return false;
//        }
//        ArrayList<Integer> temp = speaker.available();
//        if(temp.contains(time))
//        event.setTime(time);
//        return true;
//    }
//
//    public boolean setLocation(Event event, Room room){
//        ArrayList<Integer> temp = room.getAvailableTime();
//        return temp.contains(event.time);
//    }
//
//    public void setTitle(Event event, String title) {
//        event.setTitle(title);
//    }

//    public boolean sendMessage(Organizer sender,ArrayList<User> receiver, Object content){
//        Message newMessage = new Message(sender,receiver,content);
//        sender.SentBox.add(newMessage);
//        for (int i = 0; i < receiver.size(); i++){
//            receiver.get(i).InBox.add(newMessage);
//        }
//        return true;
//    }

//    public void sentMessage(Organizer organizer, ArrayList<User> receivers, Any content){
//        //Can we import Userlist from UserOragnizer(Use case)？
//        Message message = new Message(organizer,receivers,content);
//        for (User receiver: receivers){
//            receiver.receiveMessage(message);
//        }
//        organizer.sendMessage(message);
//    }

}
