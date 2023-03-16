package assignments.assignment2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import assignments.assignment1.NotaGenerator;


public class Nota {
    //atribut pada class dibuat private
    private Member member;
    private String paket;
    private int berat;
    private String tanggalMasuk;
    private int  id;
    private int sisaHariPengerjaan;
    private boolean isReady;

    // TODO: tambahkan attributes yang diperlukan untuk class ini
    public Nota(int id, Member member, String paket, int berat, String tanggalMasuk, int sisaHariPengerjaan, boolean isReady) {
        //constructor kelas Nota
        this.id = id;
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        this.sisaHariPengerjaan = sisaHariPengerjaan;
        this.isReady = isReady;

    }

    //menambagkan setter getter
    public int getId(){
        return id;
    }
    public int getSisaHariPengerjaan(){
        return sisaHariPengerjaan;
    }
    public boolean getIsReady(){
        return isReady;
    }

    public Member getMember(){
        return member;
    }

    public void setSisaHari(){
        this.sisaHariPengerjaan--;
    }

    public void setIsReady(int sisaHariPengerjaan){
        if (sisaHariPengerjaan <= 0){
            this.isReady = true;
        }
    }

    //menghitung diskon 
    public void discount(){
        //apabila counter habis dibagi 3 maka kelipatan 3 dan mendapat diskon
        if (member.getbonusCounter() % 3 == 0) {
            System.out.println("Nota Laundry");
            System.out.println("ID    : " + member.getId());
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
            System.out.println(berat + " kg x " + hargaPaket + " = " + harga  + " = " + (harga/2) + " (Discount member 50%!!!)");
            System.out.println("Tanggal Terima  : " + tanggalMasuk);

            //tanggal masuk
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate tanggalAwal = LocalDate.parse(tanggalMasuk, formatter);

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
            System.out.println("Status      	: Belum bisa diambil :(");
        } else {
            //apabila bukan kelipatan tiga
            String idMember = member.getId();
            String nota = NotaGenerator.generateNota(idMember, paket, berat, tanggalMasuk);
            System.out.println(nota);
            System.out.println("Status      	: Belum bisa diambil :(");
        }
    }

    

    // TODO: tambahkan methods yang diperlukan untuk class ini
}
