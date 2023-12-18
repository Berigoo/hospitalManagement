package misc;

import javax.swing.*;

public class AComboBox<T> extends JComboBox<T> {
    public AComboBox(T[] list){
        super(list);
    }

    public AComboBox (ComboBoxModel<T> model) {
        super(model);
    }
}
