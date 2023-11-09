import java.util.*;

public class Solver {
    public static void main(String[] args) {
        Session session = new Session();
        Scanner scanner = new Scanner(System.in);

        while (!session.isLoggedIn()) {
            OutputManagement.cleanTerminal();
            System.out.print("Bem vindo ao HSC\n");
            System.out.print("1 - Login\n");
            System.out.print("2 - Registrar\n");
            System.out.print("0 - Sair\n");

            System.out.printf("Digite o comando desejado: ");
            int option = scanner.nextInt();
            OutputManagement.cleanTerminal();

            switch (option) {
                case 1:
                    System.out.print("Digite seu nome de usuário: ");
                    String username = scanner.next();
                    System.out.print("Digite sua senha: ");
                    String password = scanner.next();
                    session.Login(username, password);
                    break;
                case 2:
                    System.out.print("Digite seu nome de usuário: ");
                    username = scanner.next();
                    System.out.print("Digite sua senha: ");
                    password = scanner.next();
                    session.Register(username, password);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.print("Opção inválida\n");
                    break;
            }
            OutputManagement.cleanTerminal();
        }
        OutputManagement.cleanTerminal();

        if (session.getSessionAccountUserType() == UserType.USER_STD) {
            StdUserRepository stdUserRepository = new StdUserRepository();
            while (true) {
                System.out.println("Bem vindo " + session.sessionGetAccountUsername() + "!");
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
                OutputManagement.cleanTerminal();

                switch(option) {
                    case 1:
                        System.out.printf("Digite o nome do arquivo: ");
                        String filename = scanner.next();
                        System.out.printf("Digite o conteúdo do arquivo: ");
                        String content = scanner.nextLine();
                        stdUserRepository.createFile(session, filename, content);
                        OutputManagement.cleanTerminal();
                        break;

                    case 2:
                        ArrayList<String> files = stdUserRepository.getFileList(session);
                        System.out.println("Seus arquivos: ");
                        for (String file : files) {
                            System.out.println(file);
                        }
                        System.out.println("Digite qualquer caractere para continuar e pressione enter");
                        scanner.next();
                        OutputManagement.cleanTerminal();
                        break;

                    case 3:
                        files = stdUserRepository.getFileList(session);
                        System.out.println("Seus arquivos: ");
                        for (String file : files) {
                            System.out.println(file);
                        }
                        System.out.printf("Digite o nome do arquivo: ");
                        filename = scanner.next();
                        System.out.println("\n" + stdUserRepository.getFileContent(session, filename) + "\n");
                        System.out.println("Digite qualquer caractere para continuar e pressione enter");
                        scanner.next();
                        OutputManagement.cleanTerminal();
                        break;

                    case 0:
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
            }
        }

        if (session.getSessionAccountUserType() == UserType.USER_ADM) {
            
        }

        if (session.getSessionAccountUserType() == UserType.USER_MOD) {
            
        }

        scanner.close();
    }
}
