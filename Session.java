import java.util.ArrayList;

public class Session {
    private Account account;

    Session(){
        account = null;
    }

    public boolean isLoggedIn(){
        return this.account != null;
    }
    public boolean Login(String username, String password){
        if (!isLoggedIn()) {
            SocialRepository _sRepository = new SocialRepository();
            ArrayList<Account> users = _sRepository.getUsers();
            for (Account userAccount : users) {
                if(userAccount.getUsername().equals(username) && userAccount.getPassword().equals(password)){
                    this.account = userAccount;
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean Register(String username, String password){
        if (!isLoggedIn()) {
            SocialRepository _sRepository = new SocialRepository();
            ArrayList<Account> users = _sRepository.getUsers();
            for (Account userAccount : users) {
                if(userAccount.getUsername().equals(username)){
                    System.out.println("Esse usuário já existe");
                    return false;
                }
            }

            _sRepository.createAccount(username, password);
            users = _sRepository.getUsers();
            for (Account userAccount : users) {
                if(userAccount.getUsername().equals(username) && userAccount.getPassword().equals(password)){
                    this.account = userAccount;
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public UserType getSessionAccountUserType(){
        if (!isLoggedIn()) {
            System.out.println("O usuário não está logado");
        }
        return this.account.getUserType();
    }

    public String sessionGetAccountUsername(){
        if (!isLoggedIn()) {
            System.out.println("O usuário não está logado");
            return null;
        }
        return this.account.getUsername();
    }

    public Account getAccount(){
        if (isLoggedIn()) {
            return this.account;
        }else{
            throw new RuntimeException("O usuário não está logado");
        }
    }
}
