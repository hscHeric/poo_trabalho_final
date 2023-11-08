public class ModUser extends Account {
    public ModUser(String username, String password) {
        super(username, password, UserType.USER_MOD);
    }
}