package Usecase;

import Entity.*;

import java.util.ArrayList;

public class MessageHistory {
    private ArrayList<Message> messageHistory = new ArrayList<>();

    public void addMessage(Message m){
        messageHistory.add(m);
    }

    public void showMessage(){
        int size = messageHistory.size();
        for (int i = 0; i < size; i++){
            System.out.println(messageHistory.get(i).getMessage());
        }
    }
}