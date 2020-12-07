package Entity;

public class ConferenceMessageBox extends MessageBox {
    private int eventId;

    public ConferenceMessageBox(String username, int eventId) {
        super(username);
        this.eventId = eventId;
    }


}
