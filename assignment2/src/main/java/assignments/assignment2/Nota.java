package assignments.assignment2;

import assignments.assignment1.NotaGenerator;


public class Nota {
    private Member member;
    private String paket;
    private int berat;
    private String tanggalMasuk;
    private String Nota;
    private int  id;
    private int sisaHariPengerjaan;
    private boolean isReady;

    // TODO: tambahkan attributes yang diperlukan untuk class ini
    public Nota(int id, Member member, String paket, int berat, String tanggalMasuk, int sisaHariPengerjaan, boolean isReady) {
        this.id = id;
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        this.sisaHariPengerjaan = sisaHariPengerjaan;
        this.isReady = isReady;

        
        // TODO: buat constructor untuk class ini
    }
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
    
    // public int menambahhari(int sisaHariPengerjaan, boolean isReady){
    //     sisaHariPengerjaan -= 1;
    //     return sisaHariPengerjaan;
    // }

    // TODO: tambahkan methods yang diperlukan untuk class ini
}
