package misc;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class AFormattedTextField extends JFormattedTextField {
    public AFormattedTextField(MaskFormatter mask){
        super(mask);
    }
}
