package Entity;

public class Message {
    private User sender;
    private User receiver;
    private Object message;

    Message (User sender, User receiver, Object message){
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public Object getMessage (){
        return this.message;
    }

    public User getSender() {
        return this.sender;
    }

    public User getReceiver(){
        return this.receiver;
    }
}