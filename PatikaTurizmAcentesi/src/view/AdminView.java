package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.UserController;
import model.User;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class AdminView extends JFrame {
    private JPanel panelMain;
    private JTable tableUsers;
    private JScrollPane scrollPaneUsers;
    private JButton buttonAddUser;
    private JButton buttonUpdateUser;
    private JButton buttonDeleteUser;
    private JComboBox<String> comboBoxFilterRole;
    private JButton buttonFilter;
    private JButton buttonExit;

    private UserController userController;
    private DefaultTableModel tableModel;

    public AdminView(UserController userController) {
        this.userController = userController;

        setTitle("Admin Paneli");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panelMain);

        // Rol filtreleme seçeneklerini ekle
        comboBoxFilterRole.addItem("Hepsi");
        comboBoxFilterRole.addItem("admin");
        comboBoxFilterRole.addItem("employee");

        // Kullanıcı Listeleme
        initTableModel();
        refreshUserTable();

        // Filtreleme Butonu Olayı
        buttonFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Filtreleme işlemleri
                String selectedRole = comboBoxFilterRole.getSelectedItem().toString();
                List<User> filteredUsers = userController.filterUsersByRole(selectedRole.equals("Hepsi") ? "" : selectedRole);
                updateUserTable(filteredUsers);
            }
        });

        // Kullanıcı Ekleme Butonu Olayı
        buttonAddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddUserView(userController, null, new Runnable() {
                    @Override
                    public void run() {
                        refreshUserTable();
                    }
                }).setVisible(true);
            }
        });

        // Kullanıcı Güncelleme Butonu Olayı
        buttonUpdateUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableUsers.getSelectedRow();
                if (selectedRow != -1) {
                    int userId = (int) tableUsers.getValueAt(selectedRow, 0);
                    User user = userController.getUser(userId);
                    new AddUserView(userController, user, new Runnable() {
                        @Override
                        public void run() {
                            refreshUserTable();
                        }
                    }).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(panelMain, "Lütfen güncellemek istediğiniz kullanıcıyı seçin.");
                }
            }
        });

        // Kullanıcı Silme Butonu Olayı
        buttonDeleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableUsers.getSelectedRow();
                if (selectedRow != -1) {
                    int userId = (int) tableUsers.getValueAt(selectedRow, 0);
                    int confirm = JOptionPane.showConfirmDialog(panelMain, "Bu kullanıcıyı silmek istediğinizden emin misiniz?", "Kullanıcı Silme", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        userController.deleteUser(userId);
                        JOptionPane.showMessageDialog(panelMain, "Kullanıcı silindi!");
                        refreshUserTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(panelMain, "Lütfen silmek istediğiniz kullanıcıyı seçin.");
                }
            }
        });

        // Çıkış Yap Butonu Olayı
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Mevcut pencereyi kapat
                new LoginView(userController).setVisible(true); // Login ekranını aç
            }
        });
    }

    private void initTableModel() {
        tableModel = new DefaultTableModel(new Object[]{"ID", "Kullanıcı Adı", "Rol", "Ad", "Soyad"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableUsers.setModel(tableModel);
    }

    private void refreshUserTable() {
        List<User> users = userController.getAllUsers();
        updateUserTable(users);
    }

    private void updateUserTable(List<User> users) {
        tableModel.setRowCount(0); // Mevcut tüm satırları temizle
        for (User user : users) {
            tableModel.addRow(new Object[]{
                    user.getId(),
                    user.getUsername(),
                    user.getRole(),
                    user.getName(),
                    user.getSurname()
            });
        }
    }
}
