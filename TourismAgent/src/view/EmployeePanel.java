package view;

import javax.swing.*;

public class EmployeePanel extends JPanel {
    public JTable hotelTable;
    public JButton addButton;
    public JButton updateButton;
    public JButton deleteButton;

    public EmployeePanel() {
        initComponents();
    }

    private void initComponents() {
        // Bileşenler form aracılığıyla yerleştirilecek
    }

    public JTable getHotelTable() {
        return hotelTable;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setHotelTable(JTable hotelTable) {
        this.hotelTable = hotelTable;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public void setUpdateButton(JButton updateButton) {
        this.updateButton = updateButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public void addAddButtonListener(java.awt.event.ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addUpdateButtonListener(java.awt.event.ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    public void addDeleteButtonListener(java.awt.event.ActionListener listener) {
        deleteButton.addActionListener(listener);
    }
}
