import java.util.ArrayList;

public class StdUserRepository{

    protected SocialRepository _sRepository;

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

    public ArrayList<String> getUserFileList(Session session, String username){
        if (session.isLoggedIn()) {
            return this._sRepository.getFilesFromUserFolderByUsername(username);
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

    public void updateFile(Session session, String filename, String content){
        if (session.isLoggedIn()) {
            Account account = session.getAccount();
            this._sRepository.updateFileContent(account, filename, content);
            return;
        }
        throw new RuntimeException("O usuário não está logado");
    }

    public void concatenateFileContent(Session session, String filename, String content){
        String fileContent = this.getFileContent(session, filename);
        String newContent = fileContent + "\n" + content;
        this.updateFile(session, filename, newContent);
    }

    public void deleteFile( Session session, String filename){
        if (session.isLoggedIn()) {
            Account account = session.getAccount();
            this._sRepository.deleteFile(account, filename);
            return;
        }
        throw new RuntimeException("O usuário não está logado");
    }

    public ArrayList<String> getUserList(Session session){
        if (session.isLoggedIn()) {
            ArrayList<Account> users = this._sRepository.getUsers();
            ArrayList<String> usernames = new ArrayList<String>();
            
            for (Account user : users) {
                usernames.add(user.getUsername());
            }

            return usernames;
        }
        throw new RuntimeException("O usuário não está logado");
    }

    public ArrayList<Post> getFeed(Session session){
        //Retorna uma arraylist de posts
        if (session.isLoggedIn()) {
            return this._sRepository.getFeed();
        }
        throw new RuntimeException("O usuário não está logado");
    }

    public void createPost(Session session, String content, String title){
        if (session.isLoggedIn()) {
            Account account = session.getAccount();
            this._sRepository.addPostOnFeed(account, content, title);
            return;
        }
        throw new RuntimeException("O usuário não está logado");
    }
}

