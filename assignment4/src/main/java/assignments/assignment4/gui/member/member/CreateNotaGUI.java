package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.HomeGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNotaGUI extends JPanel {
    public static final String KEY = "CREATE_NOTA";
    private JLabel paketLabel;
    private JComboBox<String> paketComboBox;
    private JButton showPaketButton;
    private JLabel beratLabel;
    private JTextField beratTextField;
    private JCheckBox setrikaCheckBox;
    private JCheckBox antarCheckBox;
    private JButton createNotaButton;
    private JButton backButton;
    private final SimpleDateFormat fmt;
    private final Calendar cal;
    private final MemberSystemGUI memberSystemGUI;

    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {
        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt;
        this.cal = NotaManager.cal;

        // Set up main panel, Feel free to make any changes
        initGUI();
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // TODO
        //membuat tampilan pada create nota
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        
        //label paket laundry
        paketLabel = new JLabel("Paket laundry: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;        
        add(paketLabel, gbc);

        //label berat cucian
        beratLabel = new JLabel("Berat cucian(kg): ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;        
        add(beratLabel, gbc);

        //field berat
        beratTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        add(beratTextField, gbc);

        //combobox paket
        String[] paketItems = {"Express", "Fast", "Reguler"};
        paketComboBox = new JComboBox<>(paketItems);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(paketComboBox, gbc);

        //show paket
        showPaketButton = new JButton("Show Paket");
        showPaketButton.addActionListener(e -> showPaket());
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        add(showPaketButton, gbc);

        //checkbos setrika
        setrikaCheckBox = new JCheckBox("Tambah setrika service (1000/kg)");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(setrikaCheckBox, gbc);

        //checkbox service
        antarCheckBox  = new JCheckBox("Tambah antar service (2000/4kg pertama, kemudian 500/kg)");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(antarCheckBox, gbc);

        //button membuat nota
        createNotaButton = new JButton("Buat Nota");
        //mengarahkan ke createnota
        createNotaButton.addActionListener(e -> createNota());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        add(createNotaButton, gbc);

        //button kembali
        backButton = new JButton("Kembali");
        //mengarahkan ke handleback
        backButton.addActionListener(e -> handleBack());
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        add(backButton, gbc);

    }

    /**
     * Menampilkan list paket pada user.
     * Akan dipanggil jika pengguna menekan "showPaketButton"
     * */
    private void showPaket() {
        String paketInfo = """
                        <html><pre>
                        +-------------Paket-------------+
                        | Express | 1 Hari | 12000 / Kg |
                        | Fast    | 2 Hari | 10000 / Kg |
                        | Reguler | 3 Hari |  7000 / Kg |
                        +-------------------------------+
                        </pre></html>
                        """;

        JLabel label = new JLabel(paketInfo);
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, label, "Paket Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method untuk melakukan pengecekan input user dan mendaftarkan nota yang sudah valid pada sistem.
     * Akan dipanggil jika pengguna menekan "createNotaButton"
     * */
    private void createNota() {
        // TODO
        Member loginMember = memberSystemGUI.getLoggedInMember();
        String berat = beratTextField.getText();
        int beratInt = 0;
        //apabi;a isi berat kosong
        if (berat.length() == 0){
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "Cucian harus angka");
            return;
        }
        //looping untuk validasi berat
        for (int b = 0; b < berat.length(); b++) {
            char beratBaju = berat.charAt(b);
            //apabila bukan digit
            if (!Character.isDigit(beratBaju)) {
                JOptionPane.showMessageDialog(MainFrame.getInstance(), "Cucian harus angka");
                beratTextField.setText("");
                return;
            }
        }
        //mengubah berat menjadi int
        beratInt = Integer.parseInt(berat);
        //jika kurang dari 0
        if (beratInt < 0) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(),"Harap masukkan berat cucian Anda dalam bentuk bilangan positif");
            beratTextField.setText("");
            return;
        //jika kurang dari 2
        } else if (beratInt < 2) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(),"Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
            beratInt = 2;
        }
        //mengambil nilai dari combobox dan tanggal masuk
        String paket = (String) paketComboBox.getSelectedItem();
        String tanggalMasuk = NotaManager.fmt.format(NotaManager.cal.getTime());

        Nota nota = new Nota(loginMember, beratInt, paket, tanggalMasuk);

        //apabila menceklis checkbox setrika
        SetrikaService setrikaService = new SetrikaService();
        if (setrikaCheckBox.isSelected()){
            nota.addService(setrikaService);
        }

        //apabila menceklis checkbox antar
        AntarService antarService = new AntarService();
        if (antarCheckBox.isSelected()){
            nota.addService(antarService);
        }
        //add nota ke list
        loginMember.addNota(nota);
        NotaManager.addNota(nota);

        JOptionPane.showMessageDialog(MainFrame.getInstance(), "nota berhasil dibuat");
        beratTextField.setText("");

        //set kembali
        paketComboBox.setSelectedIndex(0);
        setrikaCheckBox.setSelected(false);
        antarCheckBox.setSelected(false);
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        // TODO
        //jika kembali maka kembali ke page membersystemgui
        MainFrame.getInstance().navigateTo(MemberSystemGUI.KEY);
    }
}
