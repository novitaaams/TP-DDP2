package assignments.assignment4.gui;

import assignments.assignment3.nota.NotaManager;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import static assignments.assignment3.nota.NotaManager.toNextDay;

public class HomeGUI extends JPanel {
    public static final String KEY = "HOME";
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton toNextDayButton;

    public HomeGUI(){
        super(new BorderLayout()); // Setup layout, Feel free to make any changes

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        //manggunakan gbc untuk membuat tampilan
        GridBagConstraints gbc = new GridBagConstraints();
    
        //label selamat datang
        titleLabel = new JLabel("Selamat datang di CuciCuci System!");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;        
        mainPanel.add(titleLabel, gbc);

        //button login
        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> handleToLogin());
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        mainPanel.add(loginButton, gbc);

        //button register
        registerButton = new JButton("Register");
        registerButton.addActionListener(e -> handleToRegister());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        mainPanel.add(registerButton, gbc);

        //button next day
        toNextDayButton = new JButton("Next Day");
        toNextDayButton.addActionListener(e -> handleNextDay());
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        mainPanel.add(toNextDayButton, gbc);

        //label tanggal masuk
        String tanggalMasuk = NotaManager.fmt.format(NotaManager.cal.getTime());
        dateLabel = new JLabel("Hari ini:" + tanggalMasuk);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;        
        mainPanel.add(dateLabel, gbc);

    }

    /**
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     * */
    private static void handleToRegister() {
        MainFrame.getInstance().navigateTo(RegisterGUI.KEY);
    }

    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private static void handleToLogin() {
        //memanggil atau mengarahkan ke page logingui
        MainFrame.getInstance().navigateTo(LoginGUI.KEY);
    }

    /**
     * Method untuk skip hari.
     * Akan dipanggil jika pengguna menekan "toNextDayButton"
     * */
    private void handleNextDay() {
        //memanggil method next day
        NotaManager.toNextDay();

        //membuat tanggal masuk
        String tanggalMasuk = NotaManager.fmt.format(NotaManager.cal.getTime());

        //set label tanggal masuk
        dateLabel.setText("Hari ini:" + tanggalMasuk);

        //menampilkan pesan
        JOptionPane.showMessageDialog(mainPanel, "Kamu tidur hari ini zzzzz.....");
    }
}
