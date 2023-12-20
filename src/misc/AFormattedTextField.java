package misc;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;

public class AFormattedTextField extends JFormattedTextField {
    public AFormattedTextField(MaskFormatter mask){
        super(mask);
        setFont(new Font("Jetbrains Mono", Font.BOLD, 15));
        setBackground(new Color(49, 47, 46));
        setForeground(new Color(136, 183, 119));
        setRequestFocusEnabled(true);
        setCaretColor(new Color(202, 129, 149));
    }
}
