package PageClass;

import misc.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class PasienJanjiTemu extends JFrame {
    private APanel main;
    private ALabel title;
    private ALabel lDokter;
    private AComboBox<String> dokter;
    private ALabel lTanggal;
    private AFormattedTextField tanggal;
    private ALabel lWaktu;
    private AFormattedTextField waktu;
    private ALabel lDesc;
    private ATextField desc;
    private ATable table;
    private AButton submit;
    private AButton back;

    public PasienJanjiTemu(){
        main = new APanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        title = new ALabel("Set Janji Temu", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(24.0f));
        lDokter = new ALabel("Dokter: ", SwingConstants.LEFT);
        dokter = new AComboBox();
        lTanggal = new ALabel("Tanggal: ", SwingConstants.LEFT);
        try {
            tanggal = new AFormattedTextField(new MaskFormatter("####-##-##"));
            waktu = new AFormattedTextField(new MaskFormatter("##:##"));
        }catch (ParseException e){
            System.out.println(e);
        }
        lWaktu = new ALabel("Waktu: ", SwingConstants.LEFT);
        lDesc = new ALabel("Deskripsi: ", SwingConstants.LEFT);
        desc = new ATextField();
        table = new ATable();
        submit = new AButton("Submit");
        back = new AButton("Back");

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = .8;
        c.weightx = 1;
        c.gridwidth = 2;
        c.insets = new Insets(10, 5, 10 , 5);
        main.add(title, c);

        c.gridwidth = 1;
        c.gridy = 1;
        c.gridx = 0;
        c.weightx = .1;
        main.add(lDokter, c);
        c.weightx = 1;
        c.gridx = 1;
        main.add(dokter, c);

        c.gridy = 2;
        c.gridx = 0;
        c.weightx = .1;
        main.add(lTanggal, c);
        c.weightx = 1;
        c.gridx = 1;
        main.add(tanggal, c);

        c.gridy = 3;
        c.gridx = 0;
        c.weightx = .1;
        main.add(lWaktu, c);
        c.gridx = 1;
        c.weightx = 1;
        main.add(waktu, c);

        c.gridy = 4;
        c.gridx = 0;
        c.weightx = .1;
        main.add(lDesc, c);
        c.gridx = 1;
        c.weightx = 1;
        main.add(desc, c);

        c.gridy = 5;
        c.gridx = 0;
        c.gridwidth = 2;
        main.add(table.getPane(), c);

        c.gridwidth = 1;
        c.gridy = 6;
        c.gridx = 0;
        c.weightx = .1;
        c.weighty = .1;
        main.add(back, c);
        c.gridx = 1;
        main.add(submit, c);


        main.setBorder(new EmptyBorder(5, 5, 5, 5));
        pack();
        add(main);
    }

    public AComboBox<String> getDokter () {
        return dokter;
    }

    public AFormattedTextField getTanggal () {
        return tanggal;
    }

    public AFormattedTextField getWaktu () {
        return waktu;
    }

    public ATextField getDesc () {
        return desc;
    }

    public AButton getSubmit () {
        return submit;
    }

    public AButton getBack () {
        return back;
    }

    public ATable getTable () {
        return table;
    }
}
