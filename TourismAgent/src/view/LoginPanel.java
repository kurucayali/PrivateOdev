package view;

import javax.swing.*;

public class LoginPanel extends JPanel {
    public JTextField usernameField;
    public JPasswordField passwordField;
    public JButton loginButton;
    private JPanel container;


    public LoginPanel() {
        initComponents();
        this.add(container);
    }

    private void initComponents() {
        // Bileşenler form aracılığıyla yerleştirilecek
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public void setUsernameField(JTextField usernameField) {
        this.usernameField = usernameField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public void setLoginButton(JButton loginButton) {
        this.loginButton = loginButton;
    }

    public void addLoginButtonListener(java.awt.event.ActionListener listener) {
        loginButton.addActionListener(listener);
    }
}
