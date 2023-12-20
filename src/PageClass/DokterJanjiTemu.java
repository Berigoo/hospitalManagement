package PageClass;

import misc.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DokterJanjiTemu extends JFrame {
    private APanel main;
    private ALabel title;
    private ATable table;
    private AButton approve;
    private AButton reject;
    private AButton back;

    public DokterJanjiTemu(){
        main = new APanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        title = new ALabel("Upcoming Janji Temu", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(24.0f));
        table = new ATable();
        approve = new AButton("Approve");
        reject = new AButton("Reject");
        back = new AButton("Back");

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = .8;
        c.weightx = 1;
        c.gridwidth = 2;
        c.insets = new Insets(10, 5, 10 , 5);
        main.add(title, c);

        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 2;
        main.add(table.getPane(), c);

        c.gridy = 2;
        c.gridx = 0;
        c.weighty = .4;
        c.weightx = .4;
        c.gridwidth = 1;
        main.add(reject, c);
        c.gridx = 1;
        main.add(approve, c);

        c.gridy = 3;
        c.gridx = 0;
        c.weightx = .1;
        c.weighty = .1;
        main.add(back, c);

        main.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(main);
    }

    public ATable getTable () {
        return table;
    }

    public AButton getApprove () {
        return approve;
    }

    public AButton getReject () {
        return reject;
    }

    public AButton getBack () {
        return back;
    }
}
