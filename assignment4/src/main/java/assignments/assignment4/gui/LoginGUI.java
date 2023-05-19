package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.EmployeeSystem;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.AbstractMemberGUI;
import assignments.assignment4.gui.member.employee.EmployeeSystemGUI;
import assignments.assignment4.gui.member.member.MemberSystemGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JPanel {
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private LoginManager loginManager;

    public LoginGUI(LoginManager loginManager) {
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
        
        idLabel = new JLabel("Masukkan ID Anda: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;        
        mainPanel.add(idLabel, gbc);

        idTextField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(idTextField, gbc);

        passwordLabel = new JLabel("Masukkan password Anda: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(passwordField, gbc);

        loginButton = new JButton("login");
        loginButton.addActionListener(e -> handleLogin());
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(40, 10, 5, 10);
        mainPanel.add(loginButton, gbc);

        backButton = new JButton("Kembali");
        backButton.addActionListener(e -> handleBack());
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 10, 10);
        mainPanel.add(backButton, gbc);
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna men ekan "backButton"
     * */
    private void handleBack() {
        //mengarahkan ke home gui
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
        //set kembali menjadi ""
        passwordField.setText("");
        idTextField.setText("");

        
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        // TODO
        //mengambil nilai dari id dan password
        String id = idTextField.getText();
        String password = new String(passwordField.getPassword());
        //mengecek apakah ada atau tidak
        SystemCLI member = this.loginManager.getSystem(id);
        //jika member tidak ada
        if (member == null){
            JOptionPane.showMessageDialog(mainPanel, "login gagal");
            //set kembali menajdi ""
            idTextField.setText("");
            passwordField.setText("");
        //apabila member ada
        } else {
            //membuat member yang memastikan bahwa id dan passwrod benar
            Member member2 = member.authUser(id, password);
            //jika tidak benar
            if (member2 == null){
                JOptionPane.showMessageDialog(mainPanel, "login gagal");
                idTextField.setText("");
                passwordField.setText("");
            } else {
                //apakah member adalah instance of employeesystem
                if (member instanceof EmployeeSystem){
                    MainFrame.getInstance().login(id, password);
                    idTextField.setText("");
                    passwordField.setText("");
                }
                else {
                    MainFrame.getInstance().login(id, password);
                    idTextField.setText("");
                    passwordField.setText("");
                }
            }
        } 
    }
}

