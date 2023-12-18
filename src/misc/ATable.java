package misc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ATable extends JTable {
    public ATable(String[] tableHeader, String[][] tableData){
        super(tableData, tableHeader);
    }

    public ATable () {
        super();
    }
}
