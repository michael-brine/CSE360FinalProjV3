import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Table {
    private DefaultTableModel model;
    private JTable jTable;

    /**
     * Takes String[][] array data, String[] header and Constructs the model, JTable
     * with the header and given data
     *
     * @param data
     * @param header
     */
    Table(String[][] data, String[] header) {
        model = new DefaultTableModel();
        jTable = new JTable(model);
        //adds header
        for (int i = 0; i < header.length; i++) {
            model.addColumn(header[i]);
        }
        //adds roster data
        for (int i = 0; i < data.length; i++) {
            model.addRow(data[i]);
            model.fireTableDataChanged();
        }
    }

    /**
     * Takes date as a String date, and a column as an Array String[] columnData
     * and adds them
     *
     * @param date
     * @param columnData
     */
    public void addColumn(String date, String[] columnData) {
        model.addColumn(date, columnData);
    }

    /**
     * Returns the private jTable
     *
     * @return jTable
     */
    public JTable getJTable() {
        return jTable;
    }
}
