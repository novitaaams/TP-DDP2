package assignments.assignment4.gui.member.employee;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;

import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.ActionListener;

public class EmployeeSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "EMPLOYEE";

    public EmployeeSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }


    @Override
    public String getPageName(){
        return KEY;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements Employee.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        // TODO
        JButton cuciButton = new JButton("It's nyuci time"); 
        JButton  displayNotaButton = new JButton("Display List Nota");
        return new JButton[]{
            cuciButton,
            displayNotaButton
        };
    }

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons()
     * sesuai dengan requirements MemberSystem.
     *
     * @return Array of ActionListener.
     * */
    @Override
    protected ActionListener[] createActionListeners() {
        return new ActionListener[]{
                e -> cuci(),
                e -> displayNota(),
        };
    }

    /**
     * Menampilkan semua Nota yang ada pada sistem.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void displayNota() {
        // TODO
        //mmebuat display nota dengan memanggil ke tp3
        String paketInfo = "";
        //jika tidak ada nota
        if (NotaManager.notaList.length == 0){
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "Belum ada nota", "List Nota", JOptionPane.INFORMATION_MESSAGE);
        } else {
            //looping memanggil ststus nota dan menambahkan ke paketinfo
            for (int i=0; i<NotaManager.notaList.length; i++){
                paketInfo += NotaManager.notaList[i].getNotaStatus() + "\n";
            } 
            JOptionPane.showMessageDialog(this, paketInfo, "List Nota", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Menampilkan dan melakukan action mencuci.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void cuci() {
        // TODO
        //melakukan cuci dengan memanggil tp 3
        JOptionPane.showMessageDialog(MainFrame.getInstance(), "Stand back! " + loggedInMember.getNama() + " beginning to nyuci!", "Nyuci result", JOptionPane.INFORMATION_MESSAGE);
        String paketInfo = "";

        //jika tidak ada cucian
        if (NotaManager.notaList.length == 0){
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "nothing to Cuci here", "Nyuci result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            //looping ke kerjakan dan menambhakn ke paket info
            for (int i=0; i<NotaManager.notaList.length; i++){
                paketInfo += NotaManager.notaList[i].kerjakan() + "\n";
            }

            JOptionPane.showMessageDialog(this, paketInfo, "Paket Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
