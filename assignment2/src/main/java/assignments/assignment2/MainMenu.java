package assignments.assignment2;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import javax.swing.LayoutFocusTraversalPolicy;

import assignments.assignment1.NotaGenerator;

import static assignments.assignment1.NotaGenerator.*;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static ArrayList<Nota> listNota = new ArrayList<Nota>();
    private static ArrayList<Member> listMember = new ArrayList<Member>();
    private static int idNota = 0;
    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = input.nextLine();
            System.out.println("================================");
            switch (command){
                case "1" -> handleGenerateUser();
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser();
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                case "0" -> isRunning = false;
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    private static void handleGenerateUser() {
        // TODO: handle generate user
        System.out.println("Masukan nama Anda:");
        String nama = input.nextLine();
        System.out.println("Masukkan nomor handphone Anda: ");
        String noHp = "";
        boolean b = true;
        while (b) {
            noHp = input.nextLine();
            for (int j = 0; j < noHp.length(); j++) {
                char hurufNohp = noHp.charAt(j);
                if (!Character.isDigit(hurufNohp)) {
                    System.out.println("Nomor hp hanya menerima digit");
                    break;
                } else if ( j == noHp.length()-1) {
                    b = false;
                }
            }
        }
        Member member = new Member(nama, noHp);

        if (listMember.size()==0){
            listMember.add(member);
            System.out.println("Berhasil membuat member dengan ID "+ member.getId());
        } else{
            for (int i = 0; i < listMember.size(); i++){
                if(listMember.get(i).getId().equals(member.getId())){
                    System.out.println("Member dengan nama "+nama+ " dan nomor hp "+noHp + " sudah ada!");
                    break;
                } else if (i == listMember.size()-1){
                    listMember.add(member);
                    System.out.println("Berhasil membuat member dengan ID "+member.getId());
                    break;
                }
            }
        }
        
    } 

    private static void handleGenerateNota() {
        // TODO: handle ambil cucian
        System.out.println("Masukan ID member:");
        String id = input.nextLine();

        if (listMember.size() == 0){
            System.out.println("Member dengan ID " + id + " tidak ditemukan!");
        } else {
            for (int i = 0; i < listMember.size(); i++) {
                if((i==listMember.size()-1) && (!listMember.get(i).getId().equals(id))) {
                    System.out.println("Member dengan ID " + id + " tidak ditemukan!");
                } else if (listMember.get(i).getId().equals(id)) {
                    String paket;
                    while (true) {
                        System.out.println("Masukkan paket laundry: ");
                        paket = input.next();
                        if ("EXPRESS".equals(paket.toUpperCase()) || "FAST".equals(paket.toUpperCase()) || "REGULER".equals(paket.toUpperCase())) {
                            break;
                        } else if (paket.equals("?")) {
                            NotaGenerator.showPaket();
                        } else {
                            System.out.println("paket " + paket + " tidak diketahui");
                            System.out.println(("[ketik ? untuk mencari tahu jenis paket]"));
                        }
    
                    }

                    String berat=""; 
                    boolean a = true;
                    System.out.println("Masukkan berat cucian Anda [Kg}: ");
                    while (a) {
                        berat = input.nextLine();
                        // jika input berat adalah huruf
                        for (int b = 0; b < berat.length(); b++) {
                            char beratBaju = berat.charAt(b);
                            if (Character.isLetter(beratBaju)) {
                                System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                            } else {
                                int beratInt = Integer.parseInt(berat);
                                if (beratInt < 0) {
                                    System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif");
                                } else if (beratInt < 2) {
                                    System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                                    berat = "2";
                                }
                                a = false;
                            }
                        }
                    }

                    int beratInt2 = Integer.parseInt(berat);
                    System.out.println("Berhasil menambahkan nota!");
                    System.out.println("[ID Nota = " + idNota + "]");
                    String tanggalMasuk = fmt.format(cal.getTime());
                    Member member = listMember.get(i);
                    int bonus = member.setBonusCounter();
                    System.out.println(bonus);

                    //String nota = NotaGenerator.generateNota(id, paket, beratInt2, tanggalMasuk);

                    member.dicount(bonus, paket, tanggalMasuk, beratInt2);

                    boolean status = false;
                    System.out.println("Status      	: Belum bisa diambil :(");
                    int sisaHariPengerjaan = 0;
                    if (paket.toUpperCase().equals("EXPRESS")) {
                        sisaHariPengerjaan = 1;
                    } else if (paket.toUpperCase().equals("FAST")) {
                        sisaHariPengerjaan = 2;
                    } else if (paket.toUpperCase().equals("REGULER")) {
                        sisaHariPengerjaan = 3;
                    }
                    Nota nota2 = new Nota(idNota, member, paket, beratInt2, tanggalMasuk, sisaHariPengerjaan, status);
                    listNota.add(nota2);
                    idNota += 1;
                    break;
                }   
            }
        }
    }

    private static void handleListNota() {
        // TODO: handle list semua nota pada sistem
        System.out.println("Terdaftar " + listNota.size() + " nota dalam sistem.");
        String status;
        for (int i = 0; i<listNota.size(); i++){
            if (listNota.get(i).getIsReady() == true){
                status = "Sudah dapat diambil!";
            } else {
                status = "Belum bisa diambil :(";
            }
            System.out.println("- [" + listNota.get(i).getId() + "] Status      	: " + status);
        }
    }

    private static void handleListUser() {
        // TODO: handle list semua user pada sistem
        // System.out.println("Terdaftar "+ listMember.size()+ " member dalam sistem.");
        if (listMember.size() == 0){
            System.out.println("Terdaftar 0 member dalam sistem.");
        } else {
            System.out.println("Terdaftar "+ listMember.size()+ " member dalam sistem.");
            for (int i = 0; i < listMember.size(); i++) {
                //System.out.println("Terdaftar "+ listMember.size()+ " member dalam sistem.");
                System.out.println("- " + listMember.get(i).getId() + " : " + listMember.get(i).getNama());
            }
        }
    }

    private static void handleAmbilCucian() {
        // TODO: handle ambil cucian
        System.out.println("Masukan ID nota yang akan diambil:");
        String id = "";
        boolean b = true;
        while (b){
            id = input.nextLine();
            for (int a = 0; a<id.length(); a++){
            char idChar = id.charAt(a);
            if (!Character.isDigit(idChar)){
                System.out.println("ID nota berbentuk angka!");
                break;
            } else if ((a==id.length()-1) && (Character.isDigit(idChar))){
                int idInt = Integer.parseInt(id);
                for (int i = 0; i < listNota.size(); i++){
                    if((i==listNota.size()-1) && (listNota.get(i).getId() != (idInt))) {
                        System.out.println("Nota dengan ID " + id + " tidak ditemukan!");
                        b = false;
                        break;
                    } else if (listNota.get(i).getId() == (idInt)){
                        if (listNota.get(i).getIsReady() == false){
                            System.out.println("Nota dengan ID " + id + " gagal diambil!");
                            b = false;
                            break;
                        } else if (listNota.get(i).getIsReady() == true){
                            System.out.println("Nota dengan ID " + id + " berhasil diambil!");
                            Nota nota = listNota.get(i);
                            listNota.remove(nota);
                            b = false;
                            break;
                        }
                    }
                }
            }
            }
            
        }
        // for (int i = 0; i < listMember.size(); i++){
        //     if((i==listMember.size()-1) && (!listMember.get(i).getId().equals(id))) {
        //         System.out.println("Member dengan ID " + id + " tidak ditemukan!");
        //     } else if (listMember.get(i).getId().equals(id)){
        //         if (listNota.get(i).getIsReady() == false){
        //             System.out.println("Nota dengan ID" + id + " gagal diambil!");
        //         } else if (listNota.get(i).getIsReady() == true){
        //             System.out.println("Nota dengan ID" + id + " berhasil diambil!");
        //             Nota nota = listNota.get(i);
        //             listNota.remove(nota);
        //         }
        //     }
        // }

    }

    private static void handleNextDay() {
        // TODO: handle ganti hari
        System.out.println("Dek Depe tidur hari ini... zzz...");
        cal.add(Calendar.DATE, 1);
        for (Nota nota : listNota){
            nota.setSisaHari();
            nota.setIsReady(nota.getSisaHariPengerjaan());
            if (nota.getIsReady() == true){
                System.out.println("Laundry dengan nota ID " + nota.getId() +  " sudah dapat diambil!");
            } 
        }
        System.out.println("Selamat pagi dunia!");
        System.out.println("Dek Depe: It's CuciCuci Time.");
    }


    private static void printMenu() {
        System.out.println("\nSelamat datang di CuciCuci!");
        System.out.printf("Sekarang Tanggal: %s\n", fmt.format(cal.getTime()));
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate User");
        System.out.println("[2] Generate Nota");
        System.out.println("[3] List Nota");
        System.out.println("[4] List User");
        System.out.println("[5] Ambil Cucian");
        System.out.println("[6] Next Day");
        System.out.println("[0] Exit");
    }

}
