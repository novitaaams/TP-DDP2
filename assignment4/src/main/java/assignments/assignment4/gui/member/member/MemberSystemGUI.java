package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MemberSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "MEMBER";

    public MemberSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }

    @Override
    public String getPageName(){
        return KEY;
    }

    public Member getLoggedInMember(){
        return loggedInMember;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements MemberSystem.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        // TODO
        //membuat button
        JButton createButton = new JButton("Saya ingin laundry"); 
        JButton  showDetailButton = new JButton("Lihat detail Nota saya");
        return new JButton[]{
            createButton,
            showDetailButton
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
                e -> createNota(),
                e -> showDetailNota(),
        };
    }

    /**
     * Menampilkan detail Nota milik loggedInMember.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void showDetailNota() {
        // TODO
        //membuat detail nota dengan memnaggil dari tp3
        String paketInfo = "";
        //apabila nota list member ini masih kosong maka belum pernah laundry sebelumnya
        if (loggedInMember.getNotaList().length == 0){
            paketInfo += "Belum pernah laundry di CuciCuci";
        } else {
        Nota[] loopNota = loggedInMember.getNotaList();
            for (int i=0; i<NotaManager.notaList.length; i++){
                    for (int j = 0; j<loopNota.length; j++){
                    //apabila list nota ke i dalam Notamanager sama dengan listnota ke j dalam list nota permember maka panggil to string listNota notamanager ke i
                    if (NotaManager.notaList[i].equals(loopNota[j])){
                        paketInfo += NotaManager.notaList[i].toString() +"\n";
                    }
                }
            }
        }
        //membuat teks nya 
        JTextArea label = new JTextArea(paketInfo);
        JScrollPane pane = new JScrollPane(label);
        pane.setPreferredSize(new Dimension(400, 400));
        JOptionPane.showMessageDialog(this, pane,"Detail Nota", JOptionPane.INFORMATION_MESSAGE);


    }

    /**
     * Pergi ke halaman CreateNotaGUI.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void createNota() {
        // TODO
        //mengarahkan ke creatnotagui
        MainFrame.getInstance().navigateTo(CreateNotaGUI.KEY);
    }

}
