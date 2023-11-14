import java.util.*;

public class Program {
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

                    if (session.Login(username, password)) {
                        System.out.print("Login realizado com sucesso\n");
                    } else {
                        System.out.print("Não foi possivel realizar o login\n");
                    }
                    break;
                case 2:
                    System.out.print("Digite seu nome de usuário: ");
                    username = scanner.next();
                    System.out.print("Digite sua senha: ");
                    password = scanner.next();
                    if(session.Register(username, password)){
                        System.out.print("Usuário criado com sucesso\n");
                    } else {
                        System.out.print("Usuário já existe\n");
                    }
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

                    case 4:
                        files = stdUserRepository.getFileList(session);
                        System.out.println("Seus arquivos: ");
                        for (String file : files) {
                            System.out.println(file);
                        }
                        System.out.printf("Digite o nome do arquivo: ");
                        filename = scanner.next();
                        System.out.printf("Digite o novo conteúdo do arquivo: ");
                        //buffer do nextLine
                        scanner.nextLine();
                        content = scanner.nextLine();
                        stdUserRepository.updateFile(session, filename, content);
                        OutputManagement.cleanTerminal();
                        break;

                    case 5:
                        files = stdUserRepository.getFileList(session);
                        System.out.println("Seus arquivos: ");
                        for (String file : files) {
                            System.out.println(file);
                        }
                        System.out.printf("Digite o nome do arquivo: ");
                        filename = scanner.next();
                        System.out.printf("Digite o conteúdo a ser adicionado ao arquivo: ");
                        scanner.nextLine();
                        content = scanner.nextLine();
                        content = "\n" + content;
                        stdUserRepository.concatenateFileContent(session, filename, content);
                        OutputManagement.cleanTerminal();
                        break;      
                        
                    case 6:
                        files = stdUserRepository.getFileList(session);
                        System.out.println("Seus arquivos: ");
                        for (String file : files) {
                            System.out.println(file);
                        }
                        System.out.printf("Digite o nome do arquivo: ");
                        filename = scanner.next();
                        stdUserRepository.deleteFile(session, filename);
                        OutputManagement.cleanTerminal();
                        break;

                    case 7:
                        ArrayList<String> users = stdUserRepository.getUserList(session);
                        System.out.println("Usuários: ");
                        for (String user : users) {
                            System.out.println(user);
                        }
                        System.out.println("Digite qualquer caractere para continuar e pressione enter");
                        scanner.next();
                        OutputManagement.cleanTerminal();
                        break;

                    case 8:
                        //Imprimir lista de usuários
                        users = stdUserRepository.getUserList(session);
                        System.out.println("Usuários: ");
                        for (String user : users) {
                            System.out.println(user);
                        }
                        System.out.printf("Digite o nome do usuário: ");
                        String username = scanner.next();
                        files = stdUserRepository.getUserFileList(session, username);
                        System.out.println("Arquivos de " + username + ": ");
                        for (String file : files) {
                            System.out.println(file);
                        }

                        System.out.println("Digite qualquer caractere para continuar e pressione enter");
                        scanner.next();
                        OutputManagement.cleanTerminal();
                        break;
                    case 9:
                        ArrayList<Post> posts = stdUserRepository.getFeed(session);
                        for (Post post : posts) {
                            System.out.printf("Título: %s\n", post.getTitle());
                            System.out.printf("Autor: %s\n", post.getUser().getUsername());
                            System.out.printf("Conteúdo: %s\n", post.getContent());
                            System.out.printf("Data: %s\n", post.getDate());
                            System.out.println();
                        }
                        System.out.println("Digite qualquer caractere para continuar e pressione enter");
                        scanner.next();
                        OutputManagement.cleanTerminal();

                        break;
                    case 10:
                        System.out.printf("Digite o título do post: ");
                        String title = scanner.next();
                        System.out.printf("Digite o conteúdo do post: ");
                        scanner.nextLine();
                        content = scanner.nextLine();
                        stdUserRepository.createPost(session, title, content);
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


        if (session.getSessionAccountUserType() == UserType.USER_MOD) {
            StdUserRepository stdUserRepository = new ModUserRepository();
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
                System.out.println("11 - TODO");
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

                    case 4:
                        files = stdUserRepository.getFileList(session);
                        System.out.println("Seus arquivos: ");
                        for (String file : files) {
                            System.out.println(file);
                        }
                        System.out.printf("Digite o nome do arquivo: ");
                        filename = scanner.next();
                        System.out.printf("Digite o novo conteúdo do arquivo: ");
                        //buffer do nextLine
                        scanner.nextLine();
                        content = scanner.nextLine();
                        stdUserRepository.updateFile(session, filename, content);
                        OutputManagement.cleanTerminal();
                        break;

                    case 5:
                        files = stdUserRepository.getFileList(session);
                        System.out.println("Seus arquivos: ");
                        for (String file : files) {
                            System.out.println(file);
                        }
                        System.out.printf("Digite o nome do arquivo: ");
                        filename = scanner.next();
                        System.out.printf("Digite o conteúdo a ser adicionado ao arquivo: ");
                        scanner.nextLine();
                        content = scanner.nextLine();
                        content = "\n" + content;
                        stdUserRepository.concatenateFileContent(session, filename, content);
                        OutputManagement.cleanTerminal();
                        break;      
                        
                    case 6:
                        files = stdUserRepository.getFileList(session);
                        System.out.println("Seus arquivos: ");
                        for (String file : files) {
                            System.out.println(file);
                        }
                        System.out.printf("Digite o nome do arquivo: ");
                        filename = scanner.next();
                        stdUserRepository.deleteFile(session, filename);
                        OutputManagement.cleanTerminal();
                        break;

                    case 7:
                        ArrayList<String> users = stdUserRepository.getUserList(session);
                        System.out.println("Usuários: ");
                        for (String user : users) {
                            System.out.println(user);
                        }
                        System.out.println("Digite qualquer caractere para continuar e pressione enter");
                        scanner.next();
                        OutputManagement.cleanTerminal();
                        break;

                    case 8:
                        //Imprimir lista de usuários
                        users = stdUserRepository.getUserList(session);
                        System.out.println("Usuários: ");
                        for (String user : users) {
                            System.out.println(user);
                        }
                        System.out.printf("Digite o nome do usuário: ");
                        String username = scanner.next();
                        files = stdUserRepository.getUserFileList(session, username);
                        System.out.println("Arquivos de " + username + ": ");
                        for (String file : files) {
                            System.out.println(file);
                        }

                        System.out.println("Digite qualquer caractere para continuar e pressione enter");
                        scanner.next();
                        OutputManagement.cleanTerminal();
                        break;
                    case 9:
                        ArrayList<Post> posts = stdUserRepository.getFeed(session);
                        for (Post post : posts) {
                            System.out.printf("Título: %s\n", post.getTitle());
                            System.out.printf("Autor: %s\n", post.getUser().getUsername());
                            System.out.printf("Conteúdo: %s\n", post.getContent());
                            System.out.printf("Data: %s\n", post.getDate());
                            System.out.println();
                        }
                        System.out.println("Digite qualquer caractere para continuar e pressione enter");
                        scanner.next();
                        OutputManagement.cleanTerminal();

                        break;
                    case 10:
                        System.out.printf("Digite o título do post: ");
                        String title = scanner.next();
                        System.out.printf("Digite o conteúdo do post: ");
                        scanner.nextLine();
                        content = scanner.nextLine();
                        stdUserRepository.createPost(session, title, content);
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

        scanner.close();
    }
}
