package assignments.assignment3.nota;

import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.user.Member;
public class Nota {
    private Member member;
    private String paket;
    private LaundryService[] services;
    private long baseHarga;
    private int sisaHariPengerjaan;
    private  int berat;
    private int id;
    private String tanggalMasuk;
    private boolean isDone;
    static public int totalNota;
    private String tanggalSelesai;
    private int hariKompensasi;
    //int kerjakan;

    public Nota(Member member, int berat, String paket, String tanggal) {
        //TODO
        this.member = member;
        this.berat = berat;
        this.paket = paket;
        this.tanggalMasuk = tanggal;
        this.id = totalNota;
        this.isDone = false;
        this.services = new LaundryService[0];
        CuciService cuciService = new CuciService();
        addService(cuciService);
        totalNota++;
        this.sisaHariPengerjaan = getHariPaket(paket);
    }

    public void addService(LaundryService service){
        //TODO
        //menambhakan service ke dalam array services
        LaundryService[] newServices = new LaundryService[services.length+1];
        for (int i=0; i<services.length; i++){
            newServices[i] = services[i];
        }
        newServices[services.length] = service;
        services = newServices;
    }

    public String kerjakan(){
        // TODO
        //memnaggil do work jika isDone pada setiap service dalam services belum bernilai true
        String status = "";
        for (int i=0; i<services.length; i++){
            if (!services[i].isDone()){
                status = "Nota " + this.id + " : " + services[i].doWork();
                break;
                //return status;
            } 
            else {
                //jika setiap status is done dalam steiap service di services bernilai true
                status = "Nota " + this.id + " : Sudah selesai.";
            }
        }
        for (int i = 0; i<services.length; i++){
            if(!services[i].isDone()){
                return status;
            }
        }
        return status;
    }
    public void toNextDay() {
        // TODO
        //looping setiap service dalam services
        for (int i=0; i<services.length; i++){
            //apabila isDone pada setiap srvice tidak true
            if (!services[i].isDone()){
                //apabila sisa hari pekerjaan sudah 0
                if (sisaHariPengerjaan == 0){
                    //maka penambahkan hari kompensasi
                    hariKompensasi++;
                } else {
                    //maka pengungarangan sisahari pengerjaan
                    sisaHariPengerjaan--;
                }
                break;
            }
        }
    }

    public long calculateHarga(){
        // TODO
        //melakukan perhitungan harga akhir
        int hargaAkhir = berat*hargaPaket();
        //looping service dan menambhakn ke harga akhir
        for (int i = 0; i<services.length; i++){
            hargaAkhir += services[i].getHarga(berat);
        }
        if (sisaHariPengerjaan == 0){
            //jika terjadi keterlambatan (sisa hari pengerjaan sudah 0) maka pengurangan kompensasi
            hargaAkhir -= 2000*hariKompensasi;
            //jika harga akhirnya kurang dari 0 maka dianggap 0
            if (hargaAkhir < 0){
                hargaAkhir = 0;
            }
        }
        return hargaAkhir;
    }

    public String getNotaStatus(){
        // TODO
        // melakukan loop dan me return status nota
        String status = "";
        for (int i = 0; i<services.length; i++){
            if ((services[i].isDone() == true) && (i==services.length-1)){
                status = "Nota " + this.id + " : Sudah selesai.";
            } else {
                status = "Nota " + this.id + " : Belum selesai.";
            }
        }
        return status;
    }

    @Override
    public String toString(){
        // TODO
        //melakukan penulisan nota 
        String nota = "";
        nota += "\n[ID Nota = " + this.id + "]";
        nota += "\nID    : " + member.getId();
        nota += "\nPaket : " + paket;
        nota += "\nHarga :\n";
        nota += berat + " kg x " + hargaPaket() + " = " + berat*hargaPaket();
        nota += "\ntanggal terima  : " + tanggalMasuk;
        tanggalSelesai = NotaGenerator.generateNota(member.getId(), paket, berat, tanggalMasuk);
        nota += "\ntanggal selesai : " + tanggalSelesai;
        nota += "\n--- SERVICE LIST ---";
        for (int i = 0; i<services.length; i++){
            nota += "\n-" + services[i].getServiceName() + " @ Rp." + services[i].getHarga(berat);
        }
        nota += "\nHarga Akhir:" +  calculateHarga();
        if (hariKompensasi > 0){
            nota += "\n Ada kompensasi keterlambatan " + hariKompensasi + " * 2000 hari";
        }
        return nota;
    }

    // Dibawah ini adalah getter

    private static int getHariPaket(String paket) {
        paket = paket.toLowerCase();
        if (paket.equals("express")) return 1;
        if (paket.equals("fast")) return 2;
        if (paket.equals("reguler")) return 3;
        return -1;
    }

    public String getPaket() {
        return paket;
    }

    public int getBerat() {
        return berat;
    }

    public String getTanggal() {
        return tanggalMasuk;
    }

    public int getSisaHariPengerjaan(){
        return sisaHariPengerjaan;
    }
    public boolean isDone() {
        for (int i=0; i<services.length; i++){
            if (!services[i].isDone()){
                isDone = false;
            } 
            else {
                isDone = true;
            }
        }
        return isDone;
    }

    public LaundryService[] getServices(){
        return services;
    }

    public int getId(){
        return id;
    }

    public int sisaHariPengerjaanInt(){
        int tambahanHari = 0;
        if (paket.toUpperCase().equals("EXPRESS")){
            tambahanHari = 1;
        } else if (paket.toUpperCase().equals("FAST")){
            tambahanHari = 2;
        } else if (paket.toUpperCase().equals("REGULER")){
            tambahanHari = 3;
        }

        return tambahanHari;
    }

    public int hargaPaket(){
        //String hargaPaket = "";
        int harga = 0;
        if (paket.toUpperCase().equals("EXPRESS")) {
            harga = 12000;
        } else if (paket.toUpperCase().equals("FAST")) {
            harga = 10000;
        } else if (paket.toUpperCase().equals("REGULER")) {
            harga = 7000;
        }
        return harga;
    }
}
