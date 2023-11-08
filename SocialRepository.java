import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SocialRepository {
    private ArrayList<Account> users;

    public SocialRepository(){
        this.users = new ArrayList<Account>();
        ReadUsersFromCSV();
    }

    public ArrayList<Account> getUsers(){
        return this.users;
    }

    private void ReadUsersFromCSV(){
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

    private void WriteUsersToCSV(){
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

    private void deleteUsersCSV() {
        try {
            File file = new File("users.csv");
            file.delete();
        } catch (Exception e) {
            System.err.println("Erro ao deletar o arquivo CSV: " + e.getMessage());
        }
    }

    private void addAccount(Account account){
        this.users.add(account);
        WriteUsersToCSV();
    }

    
}
