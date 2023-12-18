package PageClass;

import misc.AButton;
import misc.AComboBox;
import misc.ALabel;
import misc.ATable;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class DokterAdd extends BaseAddPage{
    private ATable dataTable;
    private ALabel lDept;
    private ALabel lSpecialist;
    protected DefaultComboBoxModel<String> deptModel;
    protected DefaultComboBoxModel<String> specialistModel;
    private AComboBox<String> dept;
    private AComboBox<String> specialist;
    private AButton tambah;
    private AButton edit;
    private AButton hapus;
    private AButton simpan;
    private AButton back;
    public DokterAdd(){
        super("CRUD Dokter");
        deptModel = new DefaultComboBoxModel<>(new Vector<String>());
        specialistModel = new DefaultComboBoxModel<>(new Vector<String>());

        dataTable = new ATable();
        dept = new AComboBox<>(deptModel);
        specialist = new AComboBox<>(specialistModel);

        lDept = new ALabel("Department: ", SwingConstants.LEFT);
        lSpecialist = new ALabel("Specialist: ", SwingConstants.LEFT);
        tambah = new AButton("Tambah");
        edit = new AButton("Edit");
        hapus = new AButton("Hapus");
        simpan = new AButton("Simpan");
        back = new AButton("Back");

        c.gridwidth = 1;
        c.gridy = 7;
        c.gridx = 0;
        c.weightx = .1;
        main.add(lDept, c);
        c.gridx = 1;
        c.weightx = 1;
        main.add(dept, c);

        c.gridy = 8;
        c.gridx = 0;
        c.weightx = .1;
        main.add(lSpecialist, c);
        c.gridx = 1;
        c.weightx = 1;
        main.add(specialist, c);

        c.gridy = 9;
        c.gridx = 0;
        c.gridwidth = 2;
        main.add(new JScrollPane(dataTable), c);

        c.gridwidth = 1;
        c.gridy = 10;
        c.weightx = .5;
        c.weighty = .5;
        main.add(tambah, c);
        c.gridx = 1;
        main.add(hapus, c);

        c.gridy = 11;
        c.gridx = 0;
        main.add(edit, c);
        c.gridx = 1;
        main.add(simpan, c);

        c.gridy = 12;
        c.weightx = .1;
        c.weighty = .1;
        c.ipadx = -100;
        main.add(back, c);

//        add(back, BorderLayout.BEFORE_LINE_BEGINS);
        add(main);
    }

    public ATable getDataTable () {
        return dataTable;
    }

    public AButton getTambah () {
        return tambah;
    }

    public AButton getSimpan () {
        return simpan;
    }

    public AButton getHapus () {
        return hapus;
    }

    public AButton getEdit () {
        return edit;
    }

    public AButton getBack () {
        return back;
    }

    public AComboBox<String> getDept () {
        return dept;
    }

    public AComboBox<String> getSpecialist () {
        return specialist;
    }

    public DefaultComboBoxModel<String> getDeptModel () {
        return deptModel;
    }

    public DefaultComboBoxModel<String> getSpecialistModel () {
        return specialistModel;
    }
}
