package core;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelHelper {

    // JTable oluşturma metodu
    public static JTable createTable(DefaultTableModel model, String[] columnNames) {
        model.setColumnIdentifiers(columnNames);
        return new JTable(model);
    }

    // GridBagConstraints ayarları (şu an kullanmayacağız)
    public static void addComponent(JPanel panel, Component comp, int x, int y, int width, int height, double weightx, double weighty) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(comp, gbc);
    }

    // Tabloya veri yükleme metodu
    public static void loadTableData(DefaultTableModel model, List<Object[]> data) {
        model.setRowCount(0); // Tablonun sıfırlanması
        for (Object[] row : data) {
            model.addRow(row);
        }
    }
}
