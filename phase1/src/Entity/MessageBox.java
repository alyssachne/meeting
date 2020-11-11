package Entity;

import java.util.ArrayList;

public class MessageBox {
    private  ArrayList<Message> messages;

    public void addMessage(Message newMessage){
        this.messages.add(
                newMessage
        );
    }

    public String toString(){
        StringBuilder all = new StringBuilder();
        for (int i = 0; i <= this.messages.size(); i++){
            all.append(String.valueOf(i)).append(this.messages.get(i));
        }
        return all.toString();
    }


}
