package Controller;

import Entity.*;
import Usecase.*;
import java.io.Serializable;
import java.util.ArrayList;

public class ControllerFacade implements Serializable {

    RoomManager rm = new RoomManager();
    EventManager em = new EventManager();
    SpeakerAct sa = new SpeakerAct();
    OrganizerAct oa = new OrganizerAct();
    AttendeeAct aa = new AttendeeAct();
    Act act = new Act() {
        @Override
        public void createUser(String name, String username, String password) {

        }

        @Override
        public User getUser(String username) {
            return null;
        }

        @Override
        public boolean signUp(String username, Integer eventId) {
            return false;
        }
    };
    String username;
    String type;

    public ControllerFacade(){
        oa.createUser("admin","admin","admin");
    }

    public boolean login(String username, String password, String userType){
        if (userType.equalsIgnoreCase("Organizer")){
            //go to OrganizerAct to check
            if (oa.login(username,password)){
                this.username = username;
                type = "Organizer";
                return true;
            }
        }else if(userType.equalsIgnoreCase("Speaker")){
            //go to SpeakerAct to check
            if(sa.login(username,password)){
                this.username = username;
                type = "Speaker";
                return true;
            }
        }else if(userType.equalsIgnoreCase("Attendee")){
            //go to AttendeeAct to check
            if (aa.login(username,password)){
                this.username = username;
                type = "Attendee";
                return true;
            }

        }
        return false;
    }

    public void logout(){
        username = null;
        type = null;
    }

    public void createRoom(int id,int capacity){
        rm.createRoom(id,capacity);
    }

    public void createSpeaker(String name, String username, String password){
        sa.createUser(name,username,password);
    }

    public void createAttendee(String name, String username, String password){
        aa.createUser(name,username,password);
    }

    public boolean createEvent(String username, int eventId, String title, int time, int roomId){
        ArrayList<Integer> spTime = sa.availableTime(username);
        ArrayList<Integer> roomTime = rm.availableTime(roomId);
        if (spTime.contains(time) && roomTime.contains(time)) {
            em.createEvent(eventId,title,time,roomId,username);
            sa.giveEvent(username,eventId,time);
            rm.book(roomId,eventId,time);
            return true;
        }
        return false;
    }

    public void speakerList(){
        sa.speakerList();
    }

    public void roomList(){
        rm.roomList();
    }

    public void speakerSchedule(){
//        System.out.println(em.getEvent(1).toString());
        for (int id : sa.eventList(username)){
            //sa.eventList(username) is arraylist of eventIds
            System.out.println(em.getEvent(id).toString());
        }
    }

    public void attendeeSchedule(){
        for (int id : aa.getEvents(username)){
            System.out.println(em.getEvent(id).toString());
        }
    }
    public void getAvailableEvent(){
        //events that are not full
        for (Event event: em.allEvents){
           if ((!event.getAttendees().contains(username))&&rm.getMaxCapacity(event.getRoom())>event.getAttendees().size()){
               //check if the attendee has signed up the event or not and if the event reaches its room's maxCapacity
               System.out.println(em.getEvent(event.getId()).toString());
           }
        }
    }

    public void signUp(int eventId){
        aa.signUp(username,eventId);
        em.addAttendee(username,eventId);
    }

    public void cancelSpot(int eventId){
        aa.cancelSpot(username,eventId);
        em.cancelSpot(username,eventId);
    }

    public void groupMessageTo(String message,String userType){
        if (userType.equals("Speaker")){
            for(String username: sa.speakerMap.keySet()){
                sa.addMessage(username,this.username,message);
            }
        }else if (userType.equals("Attendee")){
            for(String username: aa.attendeeMap.keySet()){
                aa.addMessage(username,this.username,message);
            }
        }

    }

    public void eventMessage_Attendee(String message, Integer eventId){
        for (String username: em.getEvent(eventId).getAttendees()) {
            aa.addMessage(username,this.username,message);
        }
    }

    public void privateMessageTo(String receiver, String userType, String message){
        aa.addMessage(receiver,this.username,message);
//        if (userType.equals("Speaker")){
//            sa.addMessage(receiver,username,message);
//        }else if (userType.equals("Attendee")){
//            aa.addMessage(receiver,username,message);
//        }
    }

    public void checkContacts(){
        for(String name : act.getContacts(username)){
            System.out.println(name);
//        if (type.equals("Organizer")){
//            for(String name : oa.getContacts(username)){
//                System.out.println(name);
//            }
//        }else if (type.equals("Speaker")){
//            for(String name : sa.getContacts(username)){
//                System.out.println(name);
//            }
//        }else if (type.equals("Attendee")){
//            for(String name : aa.getContacts(username)){
//                System.out.println(name);
//            }
        }
    }

    public void getMessage(String sender){
        for (String message : act.getMessage(username,sender)) {
            System.out.println(message);
        }
//        if (type.equals("Organizer")){
//            for (String message : oa.getMessage(username,sender)){
//                System.out.println(message);
//            }
//        }else if (type.equals("Speaker")){
//            for (String message : sa.getMessage(username,sender)){
//                System.out.println(message);
//            }
//        }else if (type.equals("Attendee")){
//            for (String message : aa.getMessage(username,sender)){
//                System.out.println(message);
//            }
//        }
    }

    public void checkAudiences(Integer eventId){
        for(String name : em.getEvent(eventId).getAttendees()){
            System.out.println(name);
        }
    }

    public void checkSpeakers() {
        for(Integer eventId: aa.getEvents(username)) {
            System.out.println(em.getEvent(eventId).getSpeaker());
        }
    }
}
