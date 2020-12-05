package Entity;

import java.util.Observable;

public abstract class specialRequests extends Observable {
    private boolean addressed;
    private int requestId;
    private String description;
    private String type;

    // The method to address this Request
    public void address(){
        this.addressed= true;
        this.setChanged();
        this.notifyObservers("This Request has been successfully addressed!");
    }

    // An abstract constructor for the specialRequest class
    public void specialRequest(int id, String description) {
        this.addressed = false;
        this.requestId = id;
        this.description = description;
        //todo
    }

    public void setType(){

    }
}
