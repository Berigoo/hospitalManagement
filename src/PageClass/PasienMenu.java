package PageClass;

import misc.AButton;
import misc.ALabel;
import misc.APanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PasienMenu extends JFrame {
    private APanel main;
    protected ALabel title;
    protected ALabel header;
    private AButton dataDiri;
    private AButton janjiTemu;
    private AButton logout;

    public PasienMenu(){
        ImageIcon icon = new ImageIcon(new ImageIcon("images/img1.jpg").getImage().getScaledInstance(1080/4, 720/4, Image.SCALE_SMOOTH));
        GridBagConstraints c = new GridBagConstraints();

        main = new APanel(new GridBagLayout());
        title = new ALabel("Menu Utama Pasien", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(24.0f));
        header = new ALabel(icon);
        header.setHorizontalAlignment(SwingConstants.CENTER);
        dataDiri = new AButton("Edit Data Diri");
        janjiTemu = new AButton("Tambah Janji Temu");
        logout = new AButton("Log Out");

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = .4;
        c.weightx = 1;
        c.insets = new Insets(10, 5, 10 , 5);
        main.add(title, c);

        c.gridy = 1;
        c.weighty = .5;
        c.weightx = .5;
        main.add(header, c);

        c.gridy = 2;
        c.weighty = .1;
        c.weightx = .1;
        main.add(dataDiri, c);

        c.gridy = 3;
        main.add(janjiTemu, c);

        c.gridy = 4;
        main.add(logout, c);

        main.setBorder(new EmptyBorder(5, 5, 5, 5));
        pack();
        add(main);
    }

    public AButton getDataDiri () {
        return dataDiri;
    }

    public AButton getJanjiTemu () {
        return janjiTemu;
    }

    public AButton getLogout () {
        return logout;
    }
}
