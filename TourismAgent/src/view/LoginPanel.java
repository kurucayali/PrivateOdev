package view;

import core.DbConnection;
import manager.UserManager;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginPanel extends JPanel {
    private MainFrame mainFrame;

    public LoginPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("Turizm Acente Sistemi");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Kullanıcı adı ve şifre alanları oluşturuluyor
        JLabel usernameLabel = new JLabel("Kullanıcı Adı:");
        JTextField usernameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Şifre:");
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Giriş");

        // Giriş butonu için action listener ekleniyor
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                try (Connection connection = DbConnection.getConnection()) {
                    UserManager userManager = new UserManager(connection);
                    User user = userManager.loginUser(username, password);
                    if (user != null) {
                        if ("admin".equals(user.getRole())) {
                            mainFrame.showPanel("admin");
                        } else {
                            mainFrame.showPanel("employee");
                        }
                    } else {
                        JOptionPane.showMessageDialog(mainFrame, "Geçersiz kullanıcı adı veya şifre", "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(mainFrame, "Veritabanı hatası: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // GridBagLayout kullanarak bileşenler panele ekleniyor
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy = 1;
        add(usernameLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(loginButton, gbc);
    }
}
