import java.util.ArrayList;

public class Session {
    private Account account;
    private SocialMediaSystem system;

    public Session() {
        this.account = null;
        this.system = new SocialMediaSystem();
    }

    public boolean isLoggedIn() {
        return this.account != null;
    }

    public Account getAccount() {
        return this.account;
    }

    public void login(String username, String password) {
        if(!isLoggedIn()){
            Account account = this.system.getUserByUsername(username);
            if (account != null && account.getPassword().equals(password)) {
                this.account = account;
            } else {
                System.out.println("Usuário ou senha incorretos");
            }
        }
    }

    public void logout() {
        this.account = null;
    }

    public void createAccount(String username, String password){
        if (!isLoggedIn()) {
            if(this.system.getUserByUsername(username) != null){
                System.out.println("Usuário já existe");
            }else{
                this.system.createAccount(username, password);
            }
        }
    }

    public void showUserFiles(){
        if (isLoggedIn()) {
            ArrayList<String> filenames = this.system.getUserFilesnames(this.account);
            System.out.println("Arquivos: ");
            for(var filename : filenames){
                System.out.println(filename);
            }
        }
    }

    public void showOtherUserFiles(String username){
        if (isLoggedIn()) {
            ArrayList<String> filenames = this.system.getUserFilesnames(this.system.getUserByUsername(username));
            System.out.println("Arquivos: ");
            for(var filename : filenames){
                System.out.println(filename);
            }
        }
    }

    public void showUsers(){
        if (isLoggedIn()) {
            var users = this.system.getUsers();
            for(var user : users){
                System.out.println("Usuário: " + user.getUsername() + " Tipo: " + user.getUserType());
            }
        }
    }

    public void createArchive(String filename, String content){
        if (isLoggedIn()) {
            this.system.createArchive(this.account, filename, content);
        }
    }

    public void showFileContent(String filename){
        if (isLoggedIn()) {
            String content = this.system.getFileContentFromUser(account, filename);
            System.out.println(content);
        }
    }

    void updateFileContent(String filename, String content){
        if (isLoggedIn()) {
            this.system.updateFileContentFromUser(account, filename, content);
        }
    }

    void concatFileContent(String filename, String content){
        if (isLoggedIn()) {
            this.system.concatFileContentFromUser(account, filename, content);
        }
    }

    void deleteFile(String filename){
        if (isLoggedIn()) {
            this.system.deleteFileFromUser(account, filename);
        }
    }

    void addPost (String content, String title){
        if (isLoggedIn()) {
            this.system.addPostOnFeed(account, content, title);
        }
    }

    void printFeed(){
        if (isLoggedIn()) {
            var feed = this.system.getFeed();
            System.out.println("Feed: ");
            System.out.println(feed);
        }
    }
}
