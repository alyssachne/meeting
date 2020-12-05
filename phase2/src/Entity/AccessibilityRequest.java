package Entity;

public class AccessibilityRequest extends SpecialRequests {
    /**
     * An abstract constructor for the specialRequest class
     *
     * @param description : The description of the request submitted by the user
     * @param username    : The userName of the User that submitted the request
     *                    <p>
     *                    The constructor will constructor a request that has Id equal to the preexisting number of requests plus one and
     */
    public AccessibilityRequest(String description, String username) {
        super(description, username);
        this.type = "Accessibility Request";
    }

    // A method that helps user identify what type of request this is.
    public String getType(){
        return this.type;
    }
}
