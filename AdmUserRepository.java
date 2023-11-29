import java.util.*;

public class AdmUserRepository extends ModUserRepository {
    public List<Account> getStdUsers() {
        return this._sRepository.getStdUsers();
    }

    public List<Account> getModUsers() {
        return this._sRepository.getModUsers();
    }

    public void setUserToMod(Session session, String username) {
        if (session.isLoggedIn()) {
            if (session.getSessionAccountUserType() == UserType.USER_ADM) {
                this._sRepository.changeUserType(username,  UserType.USER_MOD);
                return;
            }
            throw new RuntimeException("O usuário não é um administrador");
        }
        throw new RuntimeException("O usuário não está logado");
    }

    public void setUserToStd(Session session, String username) {
        if (session.isLoggedIn()) {
            if (session.getSessionAccountUserType() == UserType.USER_ADM) {
                this._sRepository.changeUserType(username,  UserType.USER_STD);
                return;
            }
            throw new RuntimeException("O usuário não é um administrador");
        }
        throw new RuntimeException("O usuário não está logado");
    }
}
