


public class Account {
    private String username;
    private String password;
    private UserType userType;

    public Account(String username, String password, UserType userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public UserType getUserType() {
        return this.userType;
    }
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}

enum UserType {
    USER_MOD,
    USER_STD,
    USER_ADM
}
