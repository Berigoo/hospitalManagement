package misc;

import javax.swing.*;
import java.awt.event.ComponentAdapter;

public class ALabel extends JLabel {
    private ComponentAdapter adapter;
    public ALabel(String name, int c){
        super(name, c);
//        adapter = new ComponentAdapter() {
//            @Override
//            public void componentResized (ComponentEvent e) {
//
//            }
//        };
    }
    public ALabel(Icon icon){
        super();
        setIcon(icon);
    }

    public void addSizeWindowListener(ComponentAdapter adapter){

    }
}
