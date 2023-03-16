package assignments.assignment2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import assignments.assignment1.NotaGenerator;

public class Member {
    private String nama;
    private String noHp;
    private String id;
    private int bonusCounter;

    // TODO: tambahkan attributes yang diperlukan untuk class ini
    public Member(String nama, String noHp) {
        // TODO: buat constructor untuk class ini
        this.nama = nama;
        this.noHp = noHp;
        this.id = NotaGenerator.generateId(nama, noHp);
        this.bonusCounter = bonusCounter;
    }
    // TODO: tambahkan methods yang diperlukan untuk class ini
    public String getNama(){
        return nama;
    }
    public String getnoHp(){
        return noHp;
    }

    public String getId(){
        return id;
    }

    public int getbonusCounter(){
        return bonusCounter;
    }

    public int setBonusCounter(){
        return bonusCounter++;
    }

    public void dicount(int bonusCounter, String paket, String tanggalTerima, int berat){
        if ((bonusCounter+1) % 3 == 0) {
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
            System.out.println(berat + " kg x " + hargaPaket + " = " + harga  + " = " + (harga/2) + "(Discount member 50%!!!)");
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
        } else {
            String nota = NotaGenerator.generateNota(id, paket, berat, tanggalTerima);
            System.out.println(nota);
        }
    }
    // public void setbonusCounter(int bonusCounter){
    //     if (bonusCounter % 3 != 0) {
    //         bonusCounter++;
    //     } else {
    //         bonusCounter
    //     }
    // }

}
