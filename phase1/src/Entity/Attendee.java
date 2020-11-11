package Entity;

public class Attendee extends User {

    public Attendee(String name, String username, String password) {
        super(name, username, password);
    }

    @Override
    public String typeGetter(){return "Attendee";};
}
