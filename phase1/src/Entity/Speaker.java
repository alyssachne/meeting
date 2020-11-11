package Entity;

public class Speaker extends User {

    public Speaker(String name, String username, String password) {
        super(name, username, password);
    }

    @Override
    public String typeGetter(){return "Speaker";};
}
