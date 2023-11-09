import java.util.ArrayList;

public class StdUserRepository{

    private SocialRepository _sRepository;

    public StdUserRepository() {
        this._sRepository = new SocialRepository();
    }

    public void createFile(Session session, String filename, String content){
        if (session.isLoggedIn()) {
            Account account = session.getAccount();
            this._sRepository.createArchiveInUserFolder(account, filename, content);
            return;
        }
        throw new RuntimeException("O usuário não está logado");
    }

    public ArrayList<String> getFileList(Session session){
        if (session.isLoggedIn()) {
            Account account = session.getAccount();
            return this._sRepository.getFilesFromUserFolder(account);
        }
        throw new RuntimeException("O usuário não está logado");
    }

    public String getFileContent(Session session, String filename){
        if (session.isLoggedIn()) {
            Account account = session.getAccount();
            return this._sRepository.getFileContent(account, filename);
        }
        throw new RuntimeException("O usuário não está logado");
    }
}

