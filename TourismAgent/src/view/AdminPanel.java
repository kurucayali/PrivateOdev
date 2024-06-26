package view;

import core.DbConnection;
import core.PanelHelper;
import manager.UserManager;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminPanel extends JPanel {
    private UserManager userManager;
    private JTable userTable;
    private DefaultTableModel userModel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField roleField;
    private JTextField nameField;
    private JTextField surnameField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;

    public AdminPanel() {
        initComponents();

        // Veritabanı bağlantısı ve UserManager oluşturuluyor
        try {
            Connection connection = DbConnection.getConnection();
            userManager = new UserManager(connection);
            loadUsers();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Veritabanı hatası: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser();
            }
        });
    }

    private void initComponents() {
        // Form üzerindeki bileşenleri Swing Designer ile yerleştirdikten sonra, burada bileşenleri başlatın
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        roleField = new JTextField();
        nameField = new JTextField();
        surnameField = new JTextField();
        addButton = new JButton("Kullanıcı Ekle");
        updateButton = new JButton("Kullanıcı Güncelle");
        deleteButton = new JButton("Kullanıcı Sil");

        // DefaultTableModel oluşturuluyor
        userModel = new DefaultTableModel();
        String[] columnNames = {"ID", "Kullanıcı Adı", "Rol", "Ad", "Soyad"};
        userTable = PanelHelper.createTable(userModel, columnNames);

        JScrollPane scrollPane = new JScrollPane(userTable);
        setLayout(new BorderLayout());

        // Bileşenleri panelde yerleştirin
        JPanel formPanel = new JPanel();
        formPanel.add(new JLabel("Kullanıcı Adı:"));
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Şifre:"));
        formPanel.add(passwordField);
        formPanel.add(new JLabel("Rol:"));
        formPanel.add(roleField);
        formPanel.add(new JLabel("Ad:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Soyad:"));
        formPanel.add(surnameField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Kullanıcıları tabloya yükleyen metot
    private void loadUsers() {
        try {
            List<User> users = userManager.getAllUsers();
            List<Object[]> data = new ArrayList<>();
            for (User user : users) {
                data.add(new Object[]{user.getId(), user.getUsername(), user.getRole(), user.getName(), user.getSurname()});
            }
            PanelHelper.loadTableData(userModel, data);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Kullanıcılar yüklenemedi: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Kullanıcı ekleme işlemi
    private void addUser() {
        User user = new User();
        user.setUsername(usernameField.getText());
        user.setPassword(new String(passwordField.getPassword()));
        user.setRole(roleField.getText());
        user.setName(nameField.getText());
        user.setSurname(surnameField.getText());

        try {
            userManager.registerUser(user);
            loadUsers();
            JOptionPane.showMessageDialog(this, "Kullanıcı başarıyla eklendi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Kullanıcı eklenemedi: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Kullanıcı güncelleme işlemi
    private void updateUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow >= 0) {
            int userId = (int) userTable.getValueAt(selectedRow, 0);

            User user = new User();
            user.setId(userId);
            user.setUsername(usernameField.getText());
            String password = new String(passwordField.getPassword());
            if (!password.isEmpty()) {
                user.setPassword(password);
            } else {
                user.setPassword((String) userTable.getValueAt(selectedRow, 2)); // Mevcut şifre
            }
            user.setRole(roleField.getText());
            user.setName(nameField.getText());
            user.setSurname(surnameField.getText());

            try {
                userManager.updateUser(user);
                loadUsers();
                JOptionPane.showMessageDialog(this, "Kullanıcı başarıyla güncellendi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Kullanıcı güncellenemedi: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Lütfen güncellemek istediğiniz kullanıcıyı seçin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Kullanıcı silme işlemi
    private void deleteUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow >= 0) {
            int userId = (int) userTable.getValueAt(selectedRow, 0);
            int result = JOptionPane.showConfirmDialog(this, "Kullanıcıyı silmek istediğinize emin misiniz?", "Kullanıcı Sil", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                try {
                    userManager.deleteUser(userId);
                    loadUsers();
                    JOptionPane.showMessageDialog(this, "Kullanıcı başarıyla silindi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Kullanıcı silinemedi: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Lütfen silmek istediğiniz kullanıcıyı seçin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
}
