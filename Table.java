import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Table {
    private DefaultTableModel model;
    private JTable jTable;

    Table(String[][] data, String[] header) {
        model = new DefaultTableModel();
        jTable = new JTable(model);
        for (int i = 0; i < header.length; i++) {
            model.addColumn(header[i]);
        }
        for (int i = 0; i < data.length; i++) {
            model.addRow(data[i]);
            model.fireTableDataChanged();
        }
    }

    public void addColumn(String date, String[] columnData) {
        model.addColumn(date, columnData);
    }

    public JTable getJTable() {
        return jTable;
    }
}
