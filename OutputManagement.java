public class OutputManagement {
    static void cleanTerminal() {
        String os = System.getProperty("os.name").toLowerCase();

        try {
            Process process;

            if (os.contains("win")) {
                process = new ProcessBuilder("cmd", "/c", "cls").inheritIO().start();
            } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                process = new ProcessBuilder("clear").inheritIO().start();
            } else {
                System.out.println("Não é possível limpar o terminal neste sistema operacional.");
                return;
            }

            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
