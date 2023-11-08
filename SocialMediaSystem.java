import java.io.*;
import java.util.*;

public class SocialMediaSystem {
    private List<Account> users;

    public SocialMediaSystem() {
        this.users = new ArrayList<>();
        readUsers();
    }

    
    public List<Account> getUsers() {
        return this.users;
    }

    
    public void writeUsers() {
        try {
            deleteUsersCSV();
            String csvFileName = "users.csv";
            BufferedWriter bw = new BufferedWriter(new FileWriter(csvFileName));
            for (Account user : this.users) {
                bw.write(user.getUsername() + "," + user.getPassword() + "," + user.getUserType());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo CSV: " + e.getMessage());
        }
    }

    
    public void addAccount(Account account) {
        if (getUserByUsername(account.getUsername()) != null) {
            return;
        } else {
            this.users.add(account);
            writeUsers();
        }
    }

    
    public void readUsers() {
        try {
            String csvFileName = "users.csv";
            BufferedReader br = new BufferedReader(new FileReader(csvFileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 3) {
                    String username = userData[0];
                    String password = userData[1];
                    UserType userType = UserType.valueOf(userData[2]);
                    Account account = new Account(username, password, userType);
                    this.users.add(account);
                }
            }
            br.close();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
    }

    
    public Account getUserByUsername(String username) {
        for (Account user : this.users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    
    public void deleteUsersCSV() {
        try {
            File file = new File("users.csv");
            file.delete();
        } catch (Exception e) {
            System.err.println("Erro ao deletar o arquivo CSV: " + e.getMessage());
        }
    }

    
    public void createAccount(String username, String password) {
        Account account = new Account(username, password, UserType.USER_STD);
        this.addAccount(account);
        
        File dir = new File("user_data");
        if (!dir.exists()) {
            if (dir.mkdir()) {
                //System.out.println("Diretório criado com sucesso: " + dir.getName());
            } else {
                System.err.println("Erro ao criar o diretório: " + dir.getName());
                return; // Retorna se não for possível criar o diretório
            }
        }

        File directory = new File("user_data/"+username);
        if (!directory.exists()) {
            if (directory.mkdir()) {
                System.out.println("Diretório criado com sucesso: " + directory.getName());
            } else {
                System.err.println("Erro ao criar o diretório: " + directory.getName());
                return;
            }
        }

        String content = "<!DOCTYPE html>\n" + //
                "<html lang=\"en\">\n" + //
                "<head>\n" + //
                "    <meta charset=\"UTF-8\">\n" + //
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + //
                "    <title>Document</title>\n" + //
                "</head>\n" + //
                "<body>\n" + //
                "    <h1>hello_world</h1>\n" + //
                "</body>\n" + //
                "</html>";
        try {
            String htmlFileName = directory + "/index.html";
            File file = new File(htmlFileName);
            if(!file.exists()) {
                file.createNewFile();
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(htmlFileName));
            bw.write(content);
            bw.close();
            //System.out.println("Arquivo HTML salvo com sucesso: " + htmlFileName);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo HTML: " + e.getMessage());
        }
    }

    public void createArchive(Account account, String filename, String content){
        if(account.getUserType() == UserType.USER_STD){
            File directory = new File("user_data/"+account.getUsername());
            if (!directory.exists()) {
                System.err.println("Erro ao criar o diretório: " + directory.getName());
                return;
            }
            try {
                String htmlFileName = directory + "/" + filename;
                File file = new File(htmlFileName);
                if(!file.exists()) {
                    file.createNewFile();
                }

                BufferedWriter bw = new BufferedWriter(new FileWriter(htmlFileName));
                bw.write(content);
                bw.close();
                //System.out.println("Arquivo HTML salvo com sucesso: " + htmlFileName);
            } catch (IOException e) {
                System.err.println("Erro ao criar arquivo: " + e.getMessage());
            }
        }
    }

    public ArrayList<String> getUserFilesnames(Account user){
        List<String> filenames = new ArrayList<>();
        File directory = new File("user_data/"+user.getUsername());
        if (!directory.exists()) {
            throw new RuntimeException("Erro: o usuário não tem diretório criado:  " + directory.getName());
        }
        File[] files = directory.listFiles();
        for(File file : files){
            filenames.add(file.getName());
        }

        return (ArrayList<String>) filenames;
    }

    String getFileContentFromUser(Account user, String filename){
        File directory = new File("user_data/"+user.getUsername());
        if (!directory.exists()) {
            throw new RuntimeException("Erro: o usuário não tem diretório criado:  " + directory.getName());
        }
        File[] files = directory.listFiles();
        for(File file : files){
            if(file.getName().equals(filename)){
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;
                    String content = "";
                    while ((line = br.readLine()) != null) {
                        content += line;
                    }
                    br.close();
                    return content;
                } catch (IOException e) {
                    System.err.println("Erro ao ler o arquivo: " + e.getMessage());
                }
            }
        }
        return null;
    }

    void updateFileContentFromUser(Account user, String filename, String content){
        File directory = new File("user_data/"+user.getUsername());
        if (!directory.exists()) {
            throw new RuntimeException("Erro: o usuário não tem diretório criado:  " + directory.getName());
        }
        File[] files = directory.listFiles();
        for(File file : files){
            if(file.getName().equals(filename)){
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    bw.write(content);
                    bw.close();
                } catch (IOException e) {
                    System.err.println("Erro ao ler o arquivo: " + e.getMessage());
                }
            }
        }
    }

    void concatFileContentFromUser(Account user, String filename, String content){
        File directory = new File("user_data/"+user.getUsername());
        if (!directory.exists()) {
            throw new RuntimeException("Erro: o usuário não tem diretório criado:  " + directory.getName());
        }
        File[] files = directory.listFiles();
        for(File file : files){
            if(file.getName().equals(filename)){
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
                    bw.write(content);
                    bw.close();
                } catch (IOException e) {
                    System.err.println("Erro ao ler o arquivo: " + e.getMessage());
                }
            }
        }
    }

    void deleteFileFromUser(Account user, String filename){
        File directory = new File("user_data/"+user.getUsername());
        if (!directory.exists()) {
            throw new RuntimeException("Erro: o usuário não tem diretório criado:  " + directory.getName());
        }
        File[] files = directory.listFiles();
        for(File file : files){
            if(file.getName().equals(filename)){
                file.delete();
            }
        }
    }

    void getFilesFromUser(Account user){
        File directory = new File("user_data/"+user.getUsername());
        if (!directory.exists()) {
            throw new RuntimeException("Erro: o usuário não tem diretório criado:  " + directory.getName());
        }
        File[] files = directory.listFiles();
        for(File file : files){
            System.out.println(file.getName());
        }
    }

    void addPostOnFeed(Account user, String content, String title){
        Post post = new Post(user, content, title);

        //Adiciona o post no feed.csv

        try {
            String csvFileName = "feed.csv";
            BufferedWriter bw = new BufferedWriter(new FileWriter(csvFileName, true));
            bw.write(post.getUser().getUsername() + "," + post.getTitle() + "," + post.getContent() + "," + post.getDate());
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo CSV: " + e.getMessage());
        }
    }

    public String getFeed() {
        String content = "";
        try {
            String csvFileName = "feed.csv";
            BufferedReader br = new BufferedReader(new FileReader(csvFileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 4) {
                    String username = userData[0];
                    String title = userData[1];
                    String postContent = userData[2];
                    String date = userData[3];
                    content += "Usuário: " + username + "\n";
                    content += "Título: " + title + "\n";
                    content += "Conteúdo: " + postContent + "\n";
                    content += "Data: " + date + "\n";
                    content += "\n";
                }
            }
            br.close();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
        return content;
    }    
}
