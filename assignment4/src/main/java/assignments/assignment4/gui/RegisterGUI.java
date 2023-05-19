package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JPanel {
    public static final String KEY = "REGISTER";
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private LoginManager loginManager;
    private JButton backButton;

    public RegisterGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

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
        // TODO
        GridBagConstraints gbc = new GridBagConstraints();
        
        nameLabel = new JLabel("Masukkan nama Anda: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;        
        mainPanel.add(nameLabel, gbc);

        nameTextField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(nameTextField, gbc);

        phoneLabel = new JLabel("Masukkan nomor handphone Anda: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(phoneLabel, gbc);

        phoneTextField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(phoneTextField, gbc);

        passwordLabel = new JLabel("Masukkan password Anda: ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;        
        mainPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(passwordField, gbc);

        registerButton = new JButton("Register");
        registerButton.addActionListener(e -> handleRegister());
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 10, 5, 10);
        mainPanel.add(registerButton, gbc);

        backButton = new JButton("Kembali");
        backButton.addActionListener(e -> handleBack());
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        mainPanel.add(backButton, gbc);
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        //mengambil value dari text field nama dan no hp
        String nama = nameTextField.getText();
        String noHP = phoneTextField.getText();
        //validasi nomer hp
        //jika kosong
        if (noHP.length() == 0){
            JOptionPane.showMessageDialog(mainPanel, "Harus berupa angka");
            phoneTextField.setText("");
            return; 
        }   
        //looping untuk validasi no hp
        for (int j = 0; j < noHP.length(); j++) {
            char hurufNohp = noHP.charAt(j);
            //jika bukan angka
            if (!Character.isDigit(hurufNohp)) {
                JOptionPane.showMessageDialog(mainPanel, "Harus berupa angka");
                phoneTextField.setText("");
                return;
            }
        }
        String password = new String(passwordField.getPassword());
        //membuat member
        Member member = loginManager.register(nama, noHP, password);
        //jika member tidak terdapat sebelumnya
        if (member != null){
            JOptionPane.showMessageDialog(mainPanel, "Berhasil membuat ID: "+member.getId());
            //set kembali menjadi ""
            nameTextField.setText("");
            phoneTextField.setText("");
            passwordField.setText("");
            MainFrame.getInstance().navigateTo(HomeGUI.KEY);
        } else {
            //jika member telag terdapat dalam sistem
            JOptionPane.showMessageDialog(mainPanel, "Member" + nama + "dengan nomor hp " + noHP +  "sudah ada");
            //set kembali menjadi ""
            nameTextField.setText("");
            phoneTextField.setText("");
            passwordField.setText("");
            MainFrame.getInstance().navigateTo(HomeGUI.KEY);
        }
    }
}
