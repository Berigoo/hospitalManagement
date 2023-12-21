package PageClass;

import misc.AButton;
import misc.ALabel;
import misc.APanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminMenu extends JFrame {
    private APanel main;
    private ALabel title;
    private ALabel header;
    private AButton doctorAdd;
    private AButton listPatients;
    private AButton about;
    private AButton logout;

    public AdminMenu(){
        ImageIcon icon = new ImageIcon(new ImageIcon("images/img1.jpg").getImage().getScaledInstance(100, 500, Image.SCALE_SMOOTH));
        GridBagConstraints c = new GridBagConstraints();

        main = new APanel(new GridBagLayout());
        APanel wrap = new APanel();
        wrap.setLayout(new BoxLayout(wrap, BoxLayout.Y_AXIS));
        title = new ALabel("Menu Utama Admin", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(24.0f));
        header = new ALabel(icon);
        doctorAdd = new AButton("Add Doctor");
        doctorAdd.setPreferredSize(new Dimension(150, 150));
        listPatients = new AButton("List Pasien");
        listPatients.setPreferredSize(new Dimension(150, 150));
        about = new AButton("About");
        about.setPreferredSize(new Dimension(150, 150));
        logout = new AButton("Log Out");
        logout.setPreferredSize(new Dimension(150, 150));

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = .8;
        c.weightx = 1;
        c.insets = new Insets(10, 5, 10 , 5);
        main.add(title, c);

//        c.gridy = 1;
//        c.weighty = 1;
//        main.add(header, c);

        c.gridy = 2;
        main.add(doctorAdd, c);

        c.gridy = 3;
        main.add(listPatients, c);

        c.gridy = 4;
        main.add(about, c);

        c.gridy = 5;
        main.add(logout, c);

        main.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(main);
        pack();
    }

    public AButton getDoctorAdd () {
        return doctorAdd;
    }
    public AButton getListPatients () {
        return listPatients;
    }

    public AButton getAbout () {
        return about;
    }

    public AButton getLogout () {
        return logout;
    }
}
