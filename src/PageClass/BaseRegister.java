package PageClass;

import misc.AButton;

import javax.swing.border.EmptyBorder;

public class BaseRegister extends BaseAddPage{
    private AButton register;
    private AButton back;
    public BaseRegister(){
        super("Register");
        register = new AButton("Register");
        back = new AButton("Back");

        c.gridy = 7;
        c.gridx = 0;
        c.weightx = .5;
        c.weighty = .5;
        main.add(back, c);
        c.gridx = 1;
        main.add(register, c);

        main.setBorder(new EmptyBorder(100, 100, 100, 100));
        add(main);
    }

    public AButton getRegister () {
        return register;
    }

    public AButton getBack () {
        return back;
    }
}
