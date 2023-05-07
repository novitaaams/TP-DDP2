package assignments.assignment3.nota.service;

import assignments.assignment3.nota.Nota;

public class AntarService implements LaundryService{
    private boolean status = false;
    @Override
    public String doWork() {
        // TODO
        status = true;
        //mengembalika string seusai dengan ketentuan nota
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {
        // TODO
        return status;
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        int harga = 0;
        //apabila harga kurang dari 2000 maka dianggap 2000
        harga = berat * 500;
        if (harga < 2000){
            harga = 2000;
        }
        return harga;
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
