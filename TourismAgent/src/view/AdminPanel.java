package view;

import javax.swing.*;

public class AdminPanel extends JPanel {
    public JTable userTable;
    public JButton addButton;
    public JButton updateButton;
    public JButton deleteButton;
    private JPanel container;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField surnameField;
    private JTextField nameField;
    private JTextField roleField;


    public AdminPanel() {
        initComponents();
        this.add(container);
    }

    private void initComponents() {
        // Bileşenler form aracılığıyla yerleştirilecek
    }

    public JTable getUserTable() {
        return userTable;
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

    public void setUserTable(JTable userTable) {
        this.userTable = userTable;
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
