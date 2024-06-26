package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel mainPanel;

    public MainFrame() {
        setTitle("Patika Turizm Acentesi");
        setSize(800, 600);  // Ana pencerenin boyutunu ayarlama
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();  // Bileşenleri başlatma

        setLocationRelativeTo(null);  // Pencereyi ekranın ortasında açma
        setVisible(true);
    }

    private void initComponents() {
        mainPanel = new JPanel();
        mainPanel.setName("mainPanel");
        mainPanel.setLayout(new CardLayout());

        add(mainPanel);
    }

    public void showPanel(String panelName) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, panelName);
    }
}
