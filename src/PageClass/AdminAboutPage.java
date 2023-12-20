package PageClass;

import misc.AButton;
import misc.ALabel;
import misc.APanel;

import javax.swing.*;
import java.awt.*;

public class AdminAboutPage extends JFrame {
    private APanel main;
    private ALabel name;
    private ALabel alamat;
    private ALabel desc;
    private AButton back;

    public AdminAboutPage (){
        main = new APanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        name = new ALabel("Rumah Sakit ABC", SwingConstants.CENTER);
        alamat = new ALabel("Jl. DEF", SwingConstants.CENTER);
        desc = new ALabel("Information goes here.", SwingConstants.CENTER);
        back = new AButton("Back");

        main.add(name);
        main.add(alamat);
        main.add(desc);
        main.add(back);

        pack();
        add(main, BorderLayout.CENTER);
    }

    public AButton getBack () {
        return back;
    }
}
