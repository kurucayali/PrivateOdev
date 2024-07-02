package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.UserController;
import model.User;

public class AddUserView extends JFrame {
    private JPanel panelMain;
    private JTextField textFieldUsername;
    private JPasswordField passwordField;
    private JTextField textFieldName;
    private JTextField textFieldSurname;
    private JComboBox<String> comboBoxRole;
    private JButton buttonSave;

    private User user;
    private Runnable onUserSavedCallback;

    public AddUserView(UserController userController, User userToUpdate, Runnable onUserSavedCallback) {
        this.user = userToUpdate;
        this.onUserSavedCallback = onUserSavedCallback;

        setTitle(user == null ? "Yeni Kullanıcı Ekle" : "Kullanıcı Güncelle");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panelMain);

        // Rol seçeneklerini ekle
        comboBoxRole.addItem("admin");
        comboBoxRole.addItem("employee");

        // Güncelleme için kullanıcı bilgilerini doldur
        if (user != null) {
            textFieldUsername.setText(user.getUsername());
            passwordField.setText(user.getPassword());
            textFieldName.setText(user.getName());
            textFieldSurname.setText(user.getSurname());
            comboBoxRole.setSelectedItem(user.getRole());
        }

        // Kaydet Butonu Olayı
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textFieldUsername.getText();
                String password = new String(passwordField.getPassword());
                String name = textFieldName.getText();
                String surname = textFieldSurname.getText();
                String role = comboBoxRole.getSelectedItem().toString();

                if (user == null) {
                    // Yeni kullanıcı ekle
                    user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setName(name);
                    user.setSurname(surname);
                    user.setRole(role);

                    userController.addUser(user);
                    JOptionPane.showMessageDialog(panelMain, "Kullanıcı eklendi!");
                } else {
                    // Kullanıcı güncelle
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setName(name);
                    user.setSurname(surname);
                    user.setRole(role);

                    userController.updateUser(user);
                    JOptionPane.showMessageDialog(panelMain, "Kullanıcı güncellendi!");
                }
                dispose();
                onUserSavedCallback.run();  // Kullanıcı kaydedildiğinde callback'i çalıştır
            }
        });
    }
}
