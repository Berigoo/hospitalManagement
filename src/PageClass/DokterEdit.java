package PageClass;

import misc.AButton;
import misc.ALabel;
import misc.ATable;
import misc.ATextField;

import javax.swing.*;

public class DokterEdit extends BaseAddPage{
    private AButton back;
    private AButton save;
    private ALabel ldept;
    private ATextField dept;
    private ALabel lSpecialist;
    private ATextField specialist;
    public DokterEdit(){
        super("Data Diri");

        ldept = new ALabel("Department: ", SwingConstants.LEFT);
        lSpecialist = new ALabel("Specialist: ", SwingConstants.LEFT);
        dept = new ATextField();
        specialist = new ATextField();
        back = new AButton("Back");
        save = new AButton("Save");

        dept.setEditable(false);
        specialist.setEditable(false);
        getNama().setEditable(false);
        getAlamat().setEditable(false);
        getEmail().setEditable(false);

        c.gridy = 7;
        c.weightx = .1;
        c.gridx = 0;
        main.add(ldept, c);
        c.gridx = 1;
        c.weightx = 1;
        main.add(dept, c);

        c.gridy = 8;
        c.weightx = .1;
        c.gridx = 0;
        main.add(lSpecialist, c);
        c.gridx = 1;
        c.weightx = 1;
        main.add(specialist, c);

        c.gridy = 9;
        c.gridx = 0;
        c.weightx = .1;
        main.add(back, c);
        c.gridx = 1;
        main.add(save, c);

        add(main);
    }

    public AButton getBack () {
        return back;
    }

    public AButton getSave () {
        return save;
    }

    public ATextField getDept () {
        return dept;
    }

    public ATextField getSpecialist () {
        return specialist;
    }
}
