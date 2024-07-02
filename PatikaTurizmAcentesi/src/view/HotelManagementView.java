package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.HotelController;
import model.Hotel;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class HotelManagementView extends JFrame {
    private JPanel panelMain;
    private JLabel labelTitle;
    private JTextField textFieldHotelName;
    private JComboBox<String> comboBoxCity;
    private JButton buttonSearch;
    private JTable tableHotels;
    private JScrollPane scrollPaneHotels;
    private JButton buttonAddHotel;
    private JButton buttonUpdateHotel;
    private JButton buttonDeleteHotel;
    private JComboBox<String> comboBoxFilterCity;
    private JComboBox<String> comboBoxFilterStars;
    private JButton buttonFilter;
    private JButton buttonExit;

    private HotelController hotelController;
    private DefaultTableModel tableModel;

    public HotelManagementView(HotelController hotelController) {
        this.hotelController = hotelController;

        setTitle("Otel Yönetimi Paneli");
        setSize(950, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panelMain);

        // Filtreleme ve arama seçeneklerini doldur
        comboBoxCity.addItem("Hepsi");
        comboBoxFilterCity.addItem("Hepsi");
        comboBoxFilterStars.addItem("Hepsi");
        List<String> cities = hotelController.getCities();
        for (String city : cities) {
            comboBoxFilterCity.addItem(city);
            comboBoxCity.addItem(city);  // Arama için de şehirleri ekliyoruz
        }
        for (int i = 1; i <= 5; i++) {
            comboBoxFilterStars.addItem(String.valueOf(i));
        }

        // Otel Listeleme
        initTableModel();
        refreshHotelTable();

        // Filtreleme Butonu Olayı
        buttonFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Filtreleme işlemleri
                String selectedCity = comboBoxFilterCity.getSelectedItem().toString();
                String selectedStars = comboBoxFilterStars.getSelectedItem().toString();
                int stars = selectedStars.equals("Hepsi") ? -1 : Integer.parseInt(selectedStars);
                List<Hotel> filteredHotels = hotelController.filterHotels(selectedCity.equals("Hepsi") ? "" : selectedCity, stars);
                updateHotelTable(filteredHotels);
            }
        });

        // Arama Butonu Olayı
        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Arama işlemleri
                String hotelName = textFieldHotelName.getText();
                String city = comboBoxCity.getSelectedItem().toString();
                List<Hotel> searchedHotels = hotelController.searchHotels(hotelName, city.equals("Hepsi") ? "" : city);
                updateHotelTable(searchedHotels);
            }
        });

        // Otel Ekleme Butonu Olayı
        buttonAddHotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Otel ekleme işlemleri
                new AddHotelView(hotelController, null, new Runnable() {
                    @Override
                    public void run() {
                        refreshHotelTable();
                    }
                }).setVisible(true);
            }
        });

        // Otel Güncelleme Butonu Olayı
        buttonUpdateHotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableHotels.getSelectedRow();
                if (selectedRow != -1) {
                    int hotelId = (int) tableHotels.getValueAt(selectedRow, 0);
                    Hotel hotel = hotelController.getHotel(hotelId);
                    new AddHotelView(hotelController, hotel, new Runnable() {
                        @Override
                        public void run() {
                            refreshHotelTable();
                        }
                    }).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(panelMain, "Lütfen güncellemek istediğiniz oteli seçin.");
                }
            }
        });

        // Otel Silme Butonu Olayı
        buttonDeleteHotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableHotels.getSelectedRow();
                if (selectedRow != -1) {
                    int hotelId = (int) tableHotels.getValueAt(selectedRow, 0);
                    int confirm = JOptionPane.showConfirmDialog(panelMain, "Otel silmek istediğinize emin misiniz?", "Otel Sil", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        hotelController.deleteHotel(hotelId);
                        JOptionPane.showMessageDialog(panelMain, "Otel silindi!");
                        refreshHotelTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(panelMain, "Lütfen silmek istediğiniz oteli seçin.");
                }
            }
        });

        // Çıkış Yap Butonu Olayı
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Pencereyi kapat
            }
        });
    }

    private void initTableModel() {
        tableModel = new DefaultTableModel(new Object[]{"ID", "Adı", "Adres", "Email", "Telefon", "Yıldız", "Özellikler", "Pansiyon Tipleri", "Şehir"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableHotels.setModel(tableModel);
    }

    private void refreshHotelTable() {
        List<Hotel> hotels = hotelController.getAllHotels();
        updateHotelTable(hotels);
    }

    private void updateHotelTable(List<Hotel> hotels) {
        tableModel.setRowCount(0); // Mevcut tüm satırları temizle
        for (Hotel hotel : hotels) {
            tableModel.addRow(new Object[]{
                    hotel.getId(),
                    hotel.getName(),
                    hotel.getAddress(),
                    hotel.getEmail(),
                    hotel.getPhone(),
                    hotel.getStars(),
                    hotel.getFeatures() != null ? String.join(", ", hotel.getFeatures()) : "",
                    hotel.getPensionTypes() != null ? String.join(", ", hotel.getPensionTypes()) : "",
                    hotel.getCity()
            });
        }
    }
}
