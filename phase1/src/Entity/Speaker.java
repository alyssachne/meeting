package Entity;

import java.util.ArrayList;

public class Speaker extends User {
    private ArrayList<Event> speaks;

    public Speaker(String name, String username, String password) {
        super(name, username, password);
    }

    public void addSpeak(Event speak){
        this.speaks.add(speak);
    }

    public ArrayList<Event> getSpeaks() {
        return speaks;
    }


    @Override
    public String typeGetter(){return "Speaker";};
}
