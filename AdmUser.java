public class AdmUser extends Account {
    public AdmUser(String username, String password) {
        super(username, password, UserType.USER_ADM);
    }
}
