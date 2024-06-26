package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        setTitle("Turizm Acente Sistemi");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // CardLayout ve ana panel oluşturuluyor
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Paneller oluşturuluyor ve ana panele ekleniyor
        JPanel loginPanel = new LoginPanel(this);
        JPanel adminPanel = new AdminPanel();
        JPanel employeePanel = new EmployeePanel();

        mainPanel.add(loginPanel, "login");
        mainPanel.add(adminPanel, "admin");
        mainPanel.add(employeePanel, "employee");

        add(mainPanel);
    }

    // Belirli bir paneli göstermek için kullanılan metot
    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
