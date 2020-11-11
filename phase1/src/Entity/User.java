package Entity;

import Usecase.MessageHistory;

import java.util.HashMap;

public class User {
    private String name;
    private String username;
    private String password;
    private MessageBox InBox;
    private MessageBox SentBox;
    private HashMap<String, MessageHistory> messageList = new HashMap<>();

    public User(String name, String username, String password){
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void sendMessage(Message message){
        this.SentBox.addMessage(message);
    }

    public void receiveMessage(Message message){
        this.InBox.addMessage(message);
    }

    public void viewMessageIn() {
        System.out.println(this.InBox);
    }

    public String typeGetter(){return "User";};

}