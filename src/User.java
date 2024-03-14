// Class representing a user in the system
public class User {
    private String userName;
    private String password;

    // Constructor to initialize user attributes
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    // Getter methods for accessing user attributes
    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }
    // Setter methods to update user attributes
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
