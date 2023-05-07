package assignments.assignment3.user;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.menu.SystemCLI;
public class Member {
    protected String id;
    protected String password;
    protected String nama;
    protected Nota[] notaList = new Nota[0];

    public Member(String nama, String id, String password) {
        this.nama = nama;
        this.id = id;
        this.password = password;
    }

    /**
     * Method otentikasi member dengan ID dan password yang diberikan.
     *
     * @param id -> ID anggota yang akan diautentikasi.
     * @param password -> password anggota untuk mengautentikasi.
     * @return true jika ID dan password sesuai dengan instance member, false jika tidak.
     */
    public boolean login(String id, String password) {
        //memastikan apakah password yang diinput adalah password yang telah didaftarkan saat register (member)
        return id.equals(this.id) && authenticate(password);
    }

    /**
     * Menambahkan nota baru ke NotaList instance member.
     *
     * @param nota Nota object untuk ditambahkan.
     */
    public void addNota(Nota nota) {
        // TODO
        //menambahkan nota ke notaList
        Nota[] newNotaList = new Nota[notaList.length+1];
        for (int i = 0; i<notaList.length; i++){
            newNotaList[i] = notaList[i];
        }
        newNotaList[notaList.length] = nota;
        notaList = newNotaList;
    }

    /**
     * Method otentikasi member dengan password yang diberikan.
     *
     * @param password -> sandi password anggota untuk mengautentikasi.
     * @return true jika ID dan password sesuai dengan instance member, false jika tidak.
     */
    protected boolean authenticate(String password) {
        // TODO
        //apkaah password yang diinput sama dengan password yang telah didaftarkan saat register (member)
        if (password.equals(this.password)){
            return true;
        }
        return false;
    }

    // Dibawah ini adalah getter

    public String getNama() {
        return nama;
    }

    public String getId() {
        return id;
    }

    public Nota[] getNotaList() {
        return notaList;
    }

}