public class StdUser extends Account {
    public StdUser(String username, String password) {
        super(username, password, UserType.USER_STD);
    }
}