package assignments.assignment3.nota;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NotaManager {
    public static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    public static Calendar cal = Calendar.getInstance();
    static public Nota[] notaList = new Nota[0];

    /**
     * Skips ke hari berikutnya dan update semua entri nota yang sesuai.
     */
    public static void toNextDay(){
        //TODO: implement skip hari
        //looping dan melakukan atau memanggil method tonextday untuk setiap nota di notalist
        NotaManager.cal.add(Calendar.DAY_OF_MONTH, 1);
        for (int i = 0; i<notaList.length; i++){
            notaList[i].toNextDay();;
        }
    }

    /**
     * Menambahkan nota baru ke NotaList.
     *
     * @param nota Nota object untuk ditambahkan.
     */
    public static void addNota(Nota nota){
        //TODO: implement add nota
        //menambahkan nota ke notaList di Notamanager
        Nota[] newNotaList = new Nota[notaList.length+1];
        for (int i = 0; i<notaList.length; i++){
            newNotaList[i] = notaList[i];
        }
        newNotaList[notaList.length] = nota;
        notaList = newNotaList;
    }
}
