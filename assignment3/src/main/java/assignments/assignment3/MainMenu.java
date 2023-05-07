package assignments.assignment3;

import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.EmployeeSystem;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment3.user.menu.SystemCLI;

import java.util.Scanner;

import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.fmt;

public class MainMenu {
    private final Scanner in;
    private final LoginManager loginManager;

    /**
     * Entry point for the CuciCuci System application.
     *
     * @param args command line arguments, bisa kalian ignore.
     */
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu(new Scanner(System.in), new LoginManager(new EmployeeSystem(), new MemberSystem()));
        mainMenu.run();
    }

    public MainMenu(Scanner in, LoginManager loginManager) {
        this.in = in;
        this.loginManager = loginManager;
    }

    /**
     * Menjalankan main menu.
     */
    public void run() {
        boolean exit = false;
        while (!exit) {
            //menampilkan pilihan menu
            displayMenu();
            //input pilihan user
            int choice = in.nextInt();
            in.nextLine();
            switch (choice) {
                case 1 -> login();
                case 2 -> register();
                case 3 -> toNextDay();
                case 4 -> exit = true;
                default -> System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
        }

        in.close();
    }

    /**
     * Skips ke hari selanjutnya dan mengupdate sistem.
     */
    private void toNextDay() {
        System.out.println("Kamu tidur hari ini... zzz...");
        //memanggil tonextday pada NotaManager
        NotaManager.toNextDay();
    }

    /**
     * Mendaftarkan user pada sistem.
     */
    void register() {
        //input nama user
        System.out.println("Masukan nama Anda: ");
        String nama = in.nextLine();
        //input noHp user
        System.out.println("Masukan nomor handphone Anda: ");
        String noHp = in.nextLine();
        //input password user
        System.out.println("Masukan password Anda: ");
        String password = in.nextLine();

        //membuat variabel bernama registeredMember bertipe Member yang memanggil method register pada loginMeneger
        Member registeredMember = loginManager.register(nama, noHp, password);
        //jika registeredMember mereturn null
        if(registeredMember == null){
            System.out.printf("User dengan nama %s dan nomor hp %s sudah ada!\n", nama, noHp);
            return;
        }
        //print jika user berhasil di register 
        System.out.printf("Berhasil membuat user dengan ID %s!\n", registeredMember.getId());
    }

    /**
     * Meminta user untuk login dan memulai SystemCLI yang sesuai.
     */
    private void login() {

        System.out.print("Masukan ID Anda: ");
        String inputId = in.nextLine();
        System.out.print("Masukan password Anda: ");
        String inputPassword = in.nextLine();
        //membuat variabel yang memanggil getSystem untuk mengecek apakah member terdaftar dalam listMember
        SystemCLI systemCLI = loginManager.getSystem(inputId);
        //apabila me return null maka member tidak berada dalam listMember
        if(systemCLI == null){
            System.out.println("ID atau password invalid.");
            return;
        }
        systemCLI.login(in, inputId, inputPassword);
    }

    /**
     * Menampilkan menu
     */
    private void displayMenu() {
        System.out.println("Selamat datang di CuciCuci System!");
        System.out.printf("Sekarang tanggal %s\n", fmt.format(cal.getTime()));
        System.out.println("1. Login");
        System.out.println("2. Register Member");
        System.out.println("3. Tidur (Skip hari)");
        System.out.println("4. Exit");
        System.out.print("Apa yang ingin Anda lakukan hari ini? ");
    }
}