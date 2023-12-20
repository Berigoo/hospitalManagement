package PageClass;

import misc.ALabel;
import misc.APanel;
import misc.ATextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

abstract public class BaseAddPage extends JFrame {
    protected APanel main;
    protected GridBagConstraints c;
    private ALabel title;
    private ALabel lUsername;
    private ATextField username;
    private ALabel lPasswd;
    private ATextField passwd;
    private ALabel lNama;
    private ATextField nama;
    private ALabel lAlamat;
    private ATextField alamat;
    private ALabel lEmail;
    private ATextField email;
    private ALabel lTelp;
    private ATextField telp;

    public BaseAddPage(String title){
        this.title = new ALabel(title, SwingConstants.CENTER);
        this.title.setFont(this.title.getFont().deriveFont(24.0f));
        lUsername = new ALabel("Username: ", SwingConstants.LEFT);
        lPasswd = new ALabel("Password: ", SwingConstants.LEFT);
        lNama = new ALabel("Nama: ", SwingConstants.LEFT);
        lAlamat = new ALabel("Alamat: ", SwingConstants.LEFT);
        lEmail = new ALabel("E-mail: ", SwingConstants.LEFT);
        lTelp = new ALabel("No Telp: ", SwingConstants.LEFT);
        username = new ATextField();
        passwd = new ATextField();
        nama = new ATextField();
        alamat = new ATextField();
        email = new ATextField();
        telp = new ATextField();

        main = new APanel(new GridBagLayout());
        c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = .8;
        c.weightx = 1;
        c.gridwidth = 2;
        c.insets = new Insets(10, 5, 10 , 5);
        main.add(this.title, c);

        c.gridwidth = 1;
        c.gridy = 1;
        c.weighty = 1;
        c.weightx = .1;
        main.add(lUsername, c);
        c.gridx = 1;
        c.weightx = 1;
        main.add(username, c);

        c.gridy = 2;
        c.gridx = 0;
        c.weighty = 1;
        c.weightx = .1;
        main.add(lPasswd, c);
        c.gridx = 1;
        c.weightx = 1;
        main.add(passwd, c);

        c.gridwidth = 1;
        c.gridy = 3;
        c.gridx = 0;
        c.weighty = 1;
        c.weightx = .1;
        main.add(lNama, c);
        c.gridx = 1;
        c.weightx = 1;
        main.add(nama, c);

        c.gridy = 4;
        c.gridx = 0;
        c.weightx = .1;
        main.add(lAlamat, c);
        c.gridx = 1;
        c.weightx = 1;
        main.add(alamat, c);

        c.gridy = 5;
        c.gridx = 0;
        c.weightx = .1;
        main.add(lEmail, c);
        c.gridx = 1;
        c.weightx = 1;
        main.add(email, c);

        c.gridy = 6;
        c.gridx = 0;
        c.weightx = .1;
        main.add(lTelp, c);
        c.gridx = 1;
        c.weightx = 1;
        main.add(telp, c);

        main.setBorder(new EmptyBorder(100, 100, 100, 100));
    }

    public ATextField getNama () {
        return nama;
    }

    public ATextField getAlamat () {
        return alamat;
    }

    public ATextField getEmail () {
        return email;
    }

    public ATextField getTelp () {
        return telp;
    }

    public ATextField getUsername () {
        return username;
    }

    public ATextField getPasswd () {
        return passwd;
    }
}
