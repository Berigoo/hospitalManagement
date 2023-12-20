package PageClass;

import misc.AButton;
import misc.ALabel;
import misc.APanel;
import misc.ATable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminDumpPage extends JFrame {
    private APanel main;
    protected GridBagConstraints c;
    private ALabel title;
    private ATable table;
    private AButton back;
    private AButton delete;

    public AdminDumpPage (){
        main = new APanel(new GridBagLayout());
        c = new GridBagConstraints();
        title = new ALabel("List Pasien", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(24.0f));
        table = new ATable();
        back = new AButton("Back");
        delete = new AButton("Hapus");

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 5, 10 , 5);
        c.gridy = 0;
        c.gridwidth = 2;
        main.add(title);

        c.gridy = 1;
        main.add(table.getPane(), c);

        c.gridy = 2;
        c.gridwidth = 1;
        c.weighty = .1;
        c.weightx = .1;
        main.add(back, c);
        c.gridx = 1;
        main.add(delete, c);

        main.setBorder(new EmptyBorder(5, 5, 5, 5));
        pack();
        add(main);
    }

    public ATable getTable () {
        return table;
    }

    public AButton getBack () {
        return back;
    }

    public AButton getDelete () {
        return delete;
    }
}
