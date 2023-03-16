package assignments.assignment1;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            // mengeprint menu
            printMenu();
            // input pilihan
            System.out.print("Pilihan: ");
            String pilihan = input.next();

            // jika pilihan == 1
            if ("1".equals(pilihan)) {
                System.out.println("Masukkan nama Anda: ");
                String nama = input.nextLine().toUpperCase();
                nama += input.nextLine().toUpperCase();
                System.out.println("Masukkan nomor handphone Anda: ");
                String noHp = "";
                // membuat loop maka jika inputan salah, user akan diminta menginput kembali
                boolean b = true;
                while (b) {
                    noHp = input.next();
                    for (int j = 0; j < noHp.length(); j++) {
                        char hurufNohp = noHp.charAt(j);
                        if (Character.isLetter(hurufNohp)) {
                            System.out.println("Nomor hp hanya menerima digit");
                            break;
                        } b = false;
                    }
                }
                // memanggil method generateid dan memprint id
                String id = generateId(nama, noHp);
                System.out.println(id);

                // jika pilihan == 2
            } else if ("2".equals(pilihan)) {
                System.out.println("Masukkan nama Anda: ");
                String nama2 = input.nextLine();
                // membuat looping nomer hp
                System.out.println("Masukkan nomor handphone Anda: ");
                String nomor2 = "";
                boolean c = true;
                while (c) {
                    nomor2 = input.next();
                    for (int j = 0; j < nomor2.length(); j++) {
                        char hurufNohp = nomor2.charAt(j);
                        if (Character.isLetter(hurufNohp)) {
                            System.out.println("Nomor hp hanya menerima digit");
                            break;
                        } c = false;
                    }
                }
                System.out.println("Masukkan tanggal terima: ");
                String tanggalTerima = input.next();
                // looping untuk jenis paket, apabila input user masih salah maka user akan diminta untuk input kembali
                String paket;
                while (true) {
                    System.out.println("Masukkan paket laundry: ");
                    paket = input.next();
                    if ("EXPRESS".equals(paket.toUpperCase()) || "FAST".equals(paket.toUpperCase()) || "REGULER".equals(paket.toUpperCase())) {
                        break;
                    } else if (paket.equals("?")) {
                        showPaket();
                    } else {
                        System.out.println("paket " + paket + " tidak diketahui");
                        System.out.println(("[ketik ? untuk mencari tahu jenis paket]"));
                    }

                }
                // looping untuk berat, apabila inputan salah, akan diminta untuk input kembali
                String berat = "";
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
                // memanggil method generate nota dan generate id untuk dijadikan parameter di generate nota
                String id = generateId(nama2, nomor2);
                String nota = generateNota(id, paket, beratInt2, tanggalTerima);
                // jika input == 0 maka break
            } else if ("0".equals(pilihan)){
                break;
            } else {
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    public static String generateId(String nama, String nomorHP) {
        String namaUpperCase = nama.toUpperCase();
        int indeks = namaUpperCase.indexOf(" ");
        //System.out.println(indeks);
        // membuat id sementara
        String idSementara;
        if (namaUpperCase.contains(" ")) {
            idSementara = namaUpperCase.substring(0, indeks) + "-" + nomorHP;
        } else {
            idSementara = namaUpperCase + "-" + nomorHP;
        }
        int total = 0;
        // menotal jumlah id sementara
        for (int i = 0; i < idSementara.length(); i++) {
            char huruf = idSementara.charAt(i);
            if (Character.isDigit(huruf)) {
                int angkaHurufNoHp = (int) huruf - (int) '0';
                total += angkaHurufNoHp;
            } else if (Character.isLetter(huruf)) {
                int angkaHuruf = (int) huruf - (int) 'A' + 1;
                total += angkaHuruf;
            } else {
                int tambahTotal = 7;
                total += tambahTotal;
            }
        }
        // membuat id hanya 2 digit
        int totalPotong = total % 100;
        // TODO: Implement generate ID sesuai soal.
        //return null;
        return idSementara + "-" + String.format("%02d", totalPotong);
    }

    public static String generateNota(String id, String paket, int berat, String tanggalTerima) {
        // TODO: Implement generate nota sesuai soal.
        System.out.println("Nota Laundry");
        System.out.println("ID    : " + id);
        System.out.println("Paket : " + paket);
        int harga = 0;
        // membuat conditional pada harga dengan melihat jenis paket
        String hargaPaket = "";
        if (paket.toUpperCase().equals("EXPRESS")) {
            harga = berat * 12000;
            hargaPaket = "12000";
        } else if (paket.toUpperCase().equals("FAST")) {
            harga = berat * 10000;
            hargaPaket = "10000";
        } else if (paket.toUpperCase().equals("REGULER")) {
            harga = berat * 7000;
            hargaPaket = "7000";
        }
        System.out.println("Harga : ");
        System.out.println(berat + " kg x " + hargaPaket + " = " + harga);
        System.out.println("Tanggal Terima  : " + tanggalTerima);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate tanggalAwal = LocalDate.parse(tanggalTerima, formatter);

        int tambahanHari = 0;
        if (paket.toUpperCase().equals("EXPRESS")){
            tambahanHari = 1;
        } else if (paket.toUpperCase().equals("FAST")){
            tambahanHari = 2;
        } else if (paket.toUpperCase().equals("REGULER")){
            tambahanHari = 3;
        }

        LocalDate tanggalAkhir = tanggalAwal.plusDays(tambahanHari);
        String tanggalAkhirStr = tanggalAkhir.format(formatter);

        System.out.println("Tanggal Selesai : " + tanggalAkhirStr);
        return tanggalAkhirStr;
    }
    public static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }
}



