package misc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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

    public void addSizeWindowListener(ComponentAdapter adapter){

    }
}
