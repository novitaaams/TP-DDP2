package assignments.assignment3.user.menu;
import java.text.SimpleDateFormat;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Member;

public class MemberSystem extends SystemCLI {
    /**
     * Memproses pilihan dari Member yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        // TODO
        //apabila memilih 3 maka logout
        if (choice == 3){
            logout = true;
        } else if (choice == 1){
            //show paket
            NotaGenerator.showPaket();
            //input paket
            String paket; 
            while (true){
                paket = in.nextLine();
                //urusin kalau pketnya salah bro tpi nanti ajah wkwkkww 
                if ("EXPRESS".equals(paket.toUpperCase()) || "FAST".equals(paket.toUpperCase()) || "REGULER".equals(paket.toUpperCase())){
                    break;
                } else {
                    System.out.println("paket " + paket + " tidak diketahui");
                }
            }
            //input dan validasi berat, apabila berat tidak integer maka user akan menginput kembali
            String berat = ""; 
            boolean a = true;
            System.out.println("Masukkan berat cucian Anda [Kg]: ");
            while (a) {
                berat = in.nextLine();
                // jika input berat adalah huruf
                for (int b = 0; b < berat.length(); b++) {
                    char beratBaju = berat.charAt(b);
                    if (!Character.isDigit(beratBaju)) {
                        System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                    } else {
                        int beratInt = Integer.parseInt(berat);
                        if (beratInt < 0) {
                            //validasi untuk input agar lebih dari 0
                            System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif");
                        } else if (beratInt < 2) {
                            //validasi input berat jika kurang dari 2 maka berat cucian dianggap 2 kg
                            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                            berat = "2";
                        }
                        a = false;
                    }
                }
            }
            int beratInt = Integer.parseInt(berat);
            //tanggal masuk
            String tanggalMasuk = NotaManager.fmt.format(NotaManager.cal.getTime());
            //membuat objek nota baru
            Nota nota = new Nota(loginMember, beratInt, paket, tanggalMasuk);
            System.out.println("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?");
            System.out.println("Hanya tambah 1000 / kg :0");
            System.out.print("[Ketik x untuk tidak mau]:");
            String setrika = in.nextLine();
            //membuat objek setrika service baru
            SetrikaService setrikaService = new SetrikaService();
            //apabila user memilih selain x
            if (!"x".equals(setrika.toLowerCase())){
                //maka memasukkan objek setrika service kedalam list service 
                nota.addService(setrikaService);
            }
            System.out.println("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!");
            System.out.println("Cuma 2000 / 4kg, kemudian 500 / kg");
            System.out.print("[Ketik x untuk tidak mau]:");
            String antar = in.nextLine();
            //membuat objek baru
            AntarService antarService = new AntarService();
            if (!"x".equals(antar.toLowerCase())){
                //jika memilih selain x maka objek service dimasukkan ke dalam list services
                nota.addService(antarService);
            }
            //menambahkan ke listNota per member
            loginMember.addNota(nota);
            //menambahkan ke listNota dalam nota manager (all nota)
            NotaManager.addNota(nota);
            System.out.println("Nota berhasil dibuat!");

        } else if (choice == 2){
            //mendapatkan listnota per member
            Nota[] loopNota = loginMember.getNotaList();
            for (int i=0; i<NotaManager.notaList.length; i++){
                    for (int j = 0; j<loopNota.length; j++){
                    //apabila list nota ke i dalam Notamanager sama dengan listnota ke j dalam list nota permember maka panggil to string listNota notamanager ke i
                    if (NotaManager.notaList[i].equals(loopNota[j])){
                        System.out.println(NotaManager.notaList[i].toString());
                    }
                }
            }
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Member biasa.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. Saya ingin laundry");
        System.out.println("2. Lihat detail nota saya");
        System.out.println("3. Logout");
    }

    /**
     * Menambahkan Member baru ke sistem.
     *
     * @param member -> Member baru yang akan ditambahkan.
     */
    public void addMember(Member member) {
        // TODO
        //menambahkan member ke dalam member ke dalam Memberlist
        Member[] newMemberList = new Member[memberList.length+1];

        for(int i = 0; i<memberList.length; i++){
            newMemberList[i] = memberList[i];
            //System.out.println(memberList[i].getNama());
        }
        newMemberList[memberList.length] = member;
        memberList = newMemberList;

    }
}