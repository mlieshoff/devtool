package devtool;

import java.util.*;

import javax.swing.table.*;

public class ResultTableModel extends AbstractTableModel {
    private String[] columnNames = {"Category", "Product", "Version", "Subject"};
    private Object[][] data;
    private List<Key> model = new ArrayList<Key>();
    
    public ResultTableModel(List<Key> results) {
        refresh(results);
    }

    public void refresh(List<Key> results) {
        model = results;
        data = new Object[model.size()][columnNames.length];
        int i = 0;
        for (Key key : model) {
            data[i][0] = key.getCategory();
            data[i][1] = key.getProduct();
            data[i][2] = key.getVersion();
            data[i][3] = key.getSubject();
            i ++;
        }
    }

    /**
     * Gets the column count.
     *
     * @return the column count
     */
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Gets the row count.
     *
     * @return the row count
     */
    public int getRowCount() {
        return data.length;
    }

    /**
     * Gets the column name.
     *
     * @param col the col
     * @return the column name
     */
    public String getColumnName(int col) {
        return columnNames[col];
    }

    /**
     * Gets the value at.
     *
     * @param row the row
     * @param col the col
     * @return the value at
     */
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    /**
     * Gets the column class.
     *
     * @param c the c
     * @return the column class
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /**
     * Checks if is cell editable.
     *
     * @param row the row
     * @param col the col
     * @return true, if is cell editable
     */
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public Key getKey(int index) {
        return model.get(index);
    }

}
