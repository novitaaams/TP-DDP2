package assignments.assignment3;


import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.EmployeeSystem;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment3.user.menu.SystemCLI;

public class LoginManager {
    private final EmployeeSystem employeeSystem;
    private final MemberSystem memberSystem;

    public LoginManager(EmployeeSystem employeeSystem, MemberSystem memberSystem) {
        this.employeeSystem = employeeSystem;
        this.memberSystem = memberSystem;
    }

    /**
     * Method mapping dari ke SystemCLI yang sesuai.
     *
     * @param id -> ID dari user yang akan menggunakan SystemCLI
     * @return SystemCLI object yang sesuai dengan ID, null if  ID tidak ditemukan.
     */
    public SystemCLI getSystem(String id){
        if(memberSystem.isMemberExist(id)){
            return memberSystem;
        }
        if(employeeSystem.isMemberExist(id)){
            return employeeSystem;
        } 
        return null;
    }

    /**
     * Mendaftarkan member baru dengan informasi yang diberikan.
     *
     * @param nama -> Nama member.
     * @param noHp -> Nomor handphone member.
     * @param password -> Password akun member.
     * @return Member object yang berhasil mendaftar, return null jika gagal mendaftar.
     */
    public Member register(String nama, String noHp, String password) {
        // TODO
        //membuat id member
        String namaUpperCase = nama.toUpperCase();
        int indeks = namaUpperCase.indexOf(" ");
        String idSementara;
        if (namaUpperCase.contains(" ")) {
            idSementara = namaUpperCase.substring(0, indeks) + "-" + noHp;
        } else {
            idSementara = namaUpperCase + "-" + noHp;
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
        String id = idSementara + "-" + String.format("%02d", totalPotong);
        //membuat objek member baru
        Member member = new Member(nama, id, password);
        //memanggil method getSystem untuk mengecek apakah member exist atau tidak dalam listmember
        SystemCLI cekMember = getSystem(id);
        //jika cek member me return null maka member tidak exist dan akan ditambahkan ke listmember
        if (cekMember == null){
            //menambagkan member ke list member
            memberSystem.addMember(member);
            return member;
        } else {
            return null;  
        }
    }
}