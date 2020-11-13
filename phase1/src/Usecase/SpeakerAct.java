package Usecase;

import Entity.*;

public class SpeakerAct implements Usable{

    public void createSpeaker(String name, String username, String password) {
        Speaker speaker = new Speaker(name, username, password);
    }

    @Override
    public boolean signUp(User attendee, Event event) {
        return attendee.signUp(event.id);
    }

    @Override
    public boolean cancelSpot(User attendee, Event event) {
        return attendee.cancelSpot(event.id);
    }

    public boolean giveEvent(Event event, Integer time, Speaker speaker){
        return speaker.giveEvent(event.id, time);
    }

    public boolean cancelEvent(Event event, Speaker speaker) {
        return speaker.cancelEvent(event.id);
    }

//    public boolean sentMessageToAttendee(Speaker speaker, ArrayList<Event> speaks, Message message){
//        // Exception needed here
//        speaker.sendMessage(message);
//        for (Event speak : speaks){
//            if (speaker.getSpeaks().contains(speak)){
//                return false;
//            }
//            for (String user :speak.ListOfAttendees){
//                // user receive messages
//
//            }
//        }
//        return true;
//    }

//    private void sentMessage(User user, ArrayList<User> receivers, Message message){
//        //Can we import Userlist from UserOragnizer(Use case)？
//        for (User receiver: receivers){
//            receiver.receiveMessage(message);
//        }
//        user.sendMessage(message);
//    }

}
