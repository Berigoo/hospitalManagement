package misc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;

public class ALabel extends JLabel {
    private ComponentAdapter adapter;
    public ALabel(String name, int c){
        super(name, c);
        setBackground(new Color(29, 32, 33));
        setForeground(new Color(222, 205, 158));
        setFont(new Font("JetBrains Mono", Font.BOLD, 15));
    }
    public ALabel(Icon icon){
        super();
        setIcon(icon);
        setBackground(new Color(29, 32, 33));
        setForeground(new Color(222, 205, 158));
        setFont(new Font("JetBrains Mono", Font.BOLD, 15));
    }
}
