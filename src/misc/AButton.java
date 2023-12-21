package misc;

import javax.swing.*;
import java.awt.*;

public class AButton extends JButton {
    public AButton(String name){
        super(name);
        setFocusable(false);
        setFont(new Font("Jetbrains Mono", Font.BOLD, 15));
        setBackground(new Color(49, 47, 46));
        setForeground(new Color(136, 183, 119));
        setMaximumSize(new Dimension(500, 200));
    }
}
