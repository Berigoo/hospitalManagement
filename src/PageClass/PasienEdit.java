package PageClass;

import misc.AButton;

public class PasienEdit extends BaseAddPage{
    private AButton save;
    private AButton back;
    public PasienEdit(){
        super("Data Diri");

        save = new AButton("Simpan");
        back = new AButton("Back");

        c.weightx = .1;
        c.gridy = 7;
        c.gridx = 1;
        main.add(save, c);

        c.weightx = 1;
        c.gridx = 0;
        main.add(back, c);

        pack();
        add(main);
    }

    public AButton getSave () {
        return save;
    }

    public AButton getBack () {
        return back;
    }
}
