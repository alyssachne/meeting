package Entity;

import java.util.ArrayList;

public class Message {
    private User sender;
    private ArrayList<User> receivers;
    private Object message;

    public Message(User sender, ArrayList<User> receivers, Object message){
        this.sender = sender;
        this.receivers = receivers;
        this.message = message;
    }

    public Object getMessage (){
        return this.message;
    }

    public User getSender() {
        return this.sender;
    }

    public ArrayList<User> getReceiver(){
        return this.receivers;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender +
                ", receiver=" + receivers +
                ", message=" + message +
                '}';
    }
}