package misc;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ATable extends JTable {
    private JScrollPane pane;
    public ATable () {
        super();
        setFont(new Font("Jetbrains Mono", Font.BOLD, 15));
        setSelectionBackground(new Color(49, 47, 46));
        setSelectionForeground(new Color(136, 183, 119));
        setRequestFocusEnabled(true);
        setBackground(new Color(29, 32, 33));
        setForeground(new Color(222, 205, 158));
        setOpaque(true);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setBackground(new Color(49, 47, 46));
        getTableHeader().setFont(new Font("Jetbrains Mono", Font.BOLD, 16));
        getTableHeader().setForeground(new Color(136, 183, 119));
        pane = new JScrollPane(this);
        pane.getViewport().setBackground(new Color(29, 32, 33));
        pane.getViewport().setForeground(new Color(222, 205, 158));
        pane.getViewport().setFont(new Font("Jetbrains Mono", Font.BOLD, 15));
        pane.getVerticalScrollBar().setBackground(new Color(29, 32, 33));
        pane.getVerticalScrollBar().setForeground(new Color(222, 205, 158));
        JScrollBar scrollBar = new JScrollBar(Adjustable.VERTICAL);
        scrollBar.setUI(new BasicScrollBarUI(){
            @Override
            protected JButton createDecreaseButton (int orientation) {
                JButton btn =  super.createDecreaseButton(orientation);
                btn.setBackground(new Color(29, 32, 33));
                btn.setFocusable(false);
                return btn;
            }

            @Override
            protected JButton createIncreaseButton (int orientation) {
                JButton btn =  super.createDecreaseButton(orientation);
                btn.setBackground(new Color(29, 32, 33));
                btn.setFocusable(false);
                return btn;
            }

            @Override
            protected void configureScrollBarColors () {
                trackColor = new Color(29, 32, 33);
            }
        });
        pane.setVerticalScrollBar(scrollBar);
    }

    public JScrollPane getPane () {
        return pane;
    }
}
