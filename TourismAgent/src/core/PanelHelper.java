package core;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PanelHelper {

    // JTable oluşturmak için bir yardımcı metot
    public static JTable createTable(DefaultTableModel model, String[] columnNames) {
        JTable table = new JTable(model);
        model.setColumnIdentifiers(columnNames);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        return table;
    }

    // ScrollPane içine tablo eklemek için yardımcı metot
    public static JScrollPane createScrollPane(JTable table) {
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

    // Formlarda tekrar eden düzenlemeler için yardımcı metot
    public static void configureFormPanel(JPanel panel, int width, int height) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new java.awt.Dimension(width, height));
    }
}
