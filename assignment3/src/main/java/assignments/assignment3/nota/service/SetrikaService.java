package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    private boolean status = false;
    @Override
    public String doWork() {
        status = true;
        // TODO
        //mengembalika string sesuai ketentuan soal saat menyetrika
        return "Sedang menyetrika...";
    }

    @Override
    public boolean isDone() {
        // TODO
        return status;
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        //melakukan perhitungan harga 
        return berat*1000;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
