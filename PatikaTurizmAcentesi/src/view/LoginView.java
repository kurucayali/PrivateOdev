package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.UserController;
import model.User;

public class LoginView extends JFrame {
    // Formdaki bileşenler
    private JPanel panelMain;
    private JTextField textFieldUsername;
    private JPasswordField passwordField;
    private JButton buttonLogin;

    public LoginView(UserController userController) {
        setTitle("Giriş Ekranı");
        setSize(300, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panelMain);

        // Giriş butonuna tıklama olayı
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textFieldUsername.getText();
                String password = new String(passwordField.getPassword());

                // Kullanıcı adı ve şifre kontrolü
                User user = userController.getUserByUsernameAndPassword(username, password);
                if (user != null) {
                    JOptionPane.showMessageDialog(panelMain, "Giriş Başarılı");
                    if (user.getRole().equals("admin")) {
                        new AdminView(userController).setVisible(true);
                    } else {
                        new EmployeeView().setVisible(true);
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(panelMain, "Geçersiz Kullanıcı adı veya Şifre");
                }
            }
        });
    }
}
