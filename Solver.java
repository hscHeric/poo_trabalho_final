import java.util.*;

public class Solver {
    public static void main(String[] args) {
        Session session = new Session();
        Scanner scanner = new Scanner(System.in);
        
        OutputManagement.cleanTerminal();

        System.out.println("Bem vindo ao sistema HSC");
        while (true) {
            if(!session.isLoggedIn()){
                System.out.println("1 - Login");
                System.out.println("2 - Cadastrar usuário");
                System.out.println("0 - Sair");

                System.out.printf("Digite o comando desejado: ");
                int option = scanner.nextInt();
                

                switch (option) {
                    case 1:
                        System.out.print("Digite o nome de usuário(Sem espaços):");
                        String username = scanner.next();
                        System.out.print("Digite a senha(Sem espaços):");
                        String password = scanner.next();
                        session.login(username, password);
                        break;
                    case 2:
                        System.out.print("Digite o nome de usuário(Sem espaços):");
                        String username2 = scanner.next();
                        System.out.print("Digite a senha(Sem espaços):");
                        String password2 = scanner.next();
                        session.createAccount(username2, password2);
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Comando inválido");
                        break;
                }
                OutputManagement.cleanTerminal();
            }
            else{
                System.out.println("Bem vindo " + session.getAccount().getUsername()+ "!\n");
                System.out.println("1 - Criar um arquivo");             //Create
                System.out.println("2 - Listar arquivos");              //Read
                System.out.println("3 - Ver conteúdo de um arquivo");   //READ
                System.out.println("4 - Alterar um arquivo");           //UPDATE
                System.out.println("5 - Concatenar conteúdo em um arquivo"); //Adiciona uma nova linha ao arquivo
                System.out.println("6 - Deletar um arquivo");           //DELETE
                System.out.println("7 - Listar usuários");
                System.out.println("8 - Listar arquivos de um usuário");
                System.out.println("9 - Olhar feed");
                System.out.println("10 - Postar");
                System.out.println("0 - Sair");

                System.out.printf("Digite o comando desejado: ");
                int option = scanner.nextInt();
                
                switch (option) {
                    case 1:
                        scanner.nextLine(); //Limpa o buffer do scanner
                        System.out.println("Digite o nome do arquivo (Sem espaços):");
                        String filename = scanner.nextLine();
                
                        System.out.println("Digite o conteúdo do arquivo (Sem quebras de linha):");
                        String content = scanner.nextLine();
                        session.createArchive(filename, content);
                        break;

                    case 2:
                        OutputManagement.cleanTerminal();
                        session.showUserFiles();
                        System.out.println("\nDigite qualquer caractere e aperte enter para sair...");
                        scanner.next().charAt(0);
                        break;
                    
                    case 3:
                        OutputManagement.cleanTerminal();
                        session.showUserFiles();
                        System.out.println("\nDigite o nome do arquivo que deseja ver o conteúdo:");
                        String filename2 = scanner.next();
                        session.showFileContent(filename2);
                        System.out.println("\nDigite qualquer caractere e aperte enter para sair...");
                        scanner.next().charAt(0);
                        break;

                    case 4:
                        OutputManagement.cleanTerminal();
                        session.showUserFiles();
                        System.out.println("\nDigite o nome do arquivo que deseja alterar:");
                        String filename3 = scanner.next();
                        scanner.nextLine(); //Limpa o buffer do scanner
                        System.out.println("Digite o novo conteúdo do arquivo (Sem quebras de linha):");
                        String content2 = scanner.nextLine();
                        session.updateFileContent(filename3, content2);
                        break;

                    case 5:
                        OutputManagement.cleanTerminal();
                        session.showUserFiles();
                        System.out.println("\nDigite o nome do arquivo que deseja alterar:");
                        String filename4 = scanner.next();
                        scanner.nextLine(); //Limpa o buffer do scanner
                        System.out.println("Digite o novo conteúdo do arquivo (Sem quebras de linha):");
                        String content3 = scanner.nextLine();
                        content3 = "\n" + content3; //Adiciona uma nova linha ao arquivo
                        session.concatFileContent(filename4, content3);
                        break;

                    case 6:
                        OutputManagement.cleanTerminal();
                        session.showUserFiles();
                        System.out.println("\nDigite o nome do arquivo que deseja deletar:");
                        String filename5 = scanner.next();
                        session.deleteFile(filename5);
                        break;

                    case 7:
                        OutputManagement.cleanTerminal();
                        session.showUsers();
                        System.out.println("\nDigite qualquer caractere e aperte enter para sair...");
                        scanner.next().charAt(0);
                        break;

                    case 8:
                        OutputManagement.cleanTerminal();
                        session.showUsers();
                        System.out.println("\nDigite o nome do usuário que deseja ver os arquivos:");
                        String username = scanner.next();
                        session.showOtherUserFiles(username);
                        System.out.println("\nDigite qualquer caractere e aperte enter para sair...");
                        scanner.next().charAt(0);
                        break;

                    case 9:
                        OutputManagement.cleanTerminal();
                        session.printFeed();
                        System.out.println("\nDigite qualquer caractere e aperte enter para sair...");
                        scanner.next().charAt(0);
                        break;
                    
                    case 10:
                        OutputManagement.cleanTerminal();
                        scanner.nextLine(); //Limpa o buffer do scanner
                        System.out.println("Digite o título do post (Sem espaços):");
                        String title = scanner.nextLine();

                        System.out.println("Digite o conteúdo do post (Sem quebras de linha):");
                        String content4 = scanner.nextLine();
                        session.addPost(content4, title);
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Comando inválido");
                        break;
                }
                OutputManagement.cleanTerminal();
            }
        }
    }
}
