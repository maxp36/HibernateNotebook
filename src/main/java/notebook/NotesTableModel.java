package notebook;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class NotesTableModel extends AbstractTableModel {

    private NotebookController controller;

    private List<Contact> contacts;

    public NotesTableModel(NotebookController controller) {
        this.controller = controller;
        updateTable();
    }

    @Override
    public int getRowCount() {
        return contacts.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Contact c = contacts.get(rowIndex);
        switch (columnIndex) {
            case 0 : return c.getId();
            case 1 : return c.getPhone();
            case 2 : return c.getName();
            case 3 : return c.getEmail();
            case 4 : return c.getNote();
            default: return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "ID";
            case 1: return "PHONE";
            case 2: return "NAME";
            case 3: return "EMAIL";
            case 4: return "NOTE";
            default: return "";
        }
    }

    public void updateTable() {
        contacts = controller.getContacts();
        this.fireTableDataChanged();
    }
}