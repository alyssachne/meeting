package Entity;

public class Organizer extends User {
    public Organizer(String name, String username, String password) {
        super(name, username, password);
    }

    @Override
    public String typeGetter(){return "Organizer";};
}
