package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {
    //menambahkan atribut apasaja yang dibutuhkan pada class member
    private String nama;
    private String noHp;
    private String id;
    private int bonusCounter;

    public Member(String nama, String noHp) {
        // constructor kelas member
        this.nama = nama;
        this.noHp = noHp;
        this.id = NotaGenerator.generateId(nama, noHp);
        this.bonusCounter = 0;
    }
    
    //menambahkan setter getter
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

    //menambah 1 setiap dipanggil di generate nota
    public int setBonusCounter(){
        return bonusCounter++;
    }

}
