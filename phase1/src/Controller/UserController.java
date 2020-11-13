package Controller;

import Entity.Organizer;
import Entity.User;
import Usecase.*;
import com.sun.tools.corba.se.idl.constExpr.Or;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.HashMap;

public class UserController {

    RoomManager rm = new RoomManager();
    EventManager em = new EventManager();
    SpeakerAct sa = new SpeakerAct();
    OrganizerAct oa = new OrganizerAct();
    AttendeeAct aa = new AttendeeAct();
    String username;

    public boolean login(String username, String password, String userType){
        if (userType.equalsIgnoreCase("Organizer")){
            //go to OrganizerAct to check
            if (oa.login(username,password)){
                this.username = username;
                return true;
            }
        }else if(userType.equalsIgnoreCase("Speaker")){
            //go to SpeakerAct to check
            if(sa.login(username,password)){
                this.username = username;
                return true;
            }
        }else if(userType.equalsIgnoreCase("Attendee")){
            //go to AttendeeAct to check
            if (aa.login(username,password)){
                this.username = username;
                return true;
            }

        }
        return false;
    }

    public void createRoom(int id,int capacity){
        rm.createRoom(id,capacity);
    }

    public void createSpeaker(String name, String username, String password){
        sa.createSpeaker(name,username,password);
    }

    public boolean createEvent(String username, int eventId, String title, int time, int roomId){
        ArrayList<Integer> spTime = sa.availableTime(username);
        ArrayList<Integer> roomTime = rm.availableTime(roomId);
        if (spTime.contains(time) && roomTime.contains(time)) {
            em.createEvent(eventId,title,time,roomId);
            sa.giveEvent(username,eventId,time);
            rm.book(roomId,eventId,time);
            return true;
        }
        //        for (Integer time : spTime){
//            if (roomTime.contains(time)){
//                em.createEvent(eventId,title,roomId);
//                em.setTime(eventId,time);//eventTime
//                sa.giveEvent(username,eventId,time);//speakerTime
//
//                return true;
//            }
//
//        }
        return false;
    }

    public void speakerList(){
        sa.speakerList();
    }

    public void roomList(){
        rm.roomList();
    }
}
