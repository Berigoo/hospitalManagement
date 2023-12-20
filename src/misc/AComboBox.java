package misc;

import javax.swing.*;
import java.awt.*;

public class AComboBox<T> extends JComboBox<T> {
    public AComboBox(T[] list){
        super(list);
        setFont(new Font("Jetbrains Mono", Font.BOLD, 15));
        setBackground(new Color(49, 47, 46));
        setForeground(new Color(136, 183, 119));
        setRequestFocusEnabled(true);
    }

    public AComboBox (ComboBoxModel<T> model) {
        super(model);
        setFont(new Font("Jetbrains Mono", Font.BOLD, 15));
        setBackground(new Color(49, 47, 46));
        setForeground(new Color(136, 183, 119));
        setRequestFocusEnabled(true);
    }

    public AComboBox () {
        setFont(new Font("Jetbrains Mono", Font.BOLD, 15));
        setBackground(new Color(49, 47, 46));
        setForeground(new Color(136, 183, 119));
        setRequestFocusEnabled(true);
    }
}
