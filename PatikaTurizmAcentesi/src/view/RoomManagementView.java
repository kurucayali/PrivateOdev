package view;

import controller.HotelController;
import controller.RoomController;
import model.Hotel;
import model.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class RoomManagementView extends JFrame {
    private JPanel panelMain;
    private JTable tableRooms;
    private JComboBox<String> comboBoxHotelName;
    private JComboBox<String> comboBoxCity;
    private JComboBox<String> comboBoxRoomType;
    private JButton buttonFilter;
    private JButton buttonAddRoom;
    private JButton buttonUpdateRoom;
    private JButton buttonDeleteRoom;
    private JButton buttonExit;
    private JScrollPane scrollPaneRooms;

    private RoomController roomController;
    private HotelController hotelController;
    private DefaultTableModel tableModel;

    public RoomManagementView(RoomController roomController, HotelController hotelController) {
        this.roomController = roomController;
        this.hotelController = hotelController;

        setTitle("Oda Yönetimi Paneli");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panelMain);

        // Otel isimlerini ve şehirleri combobox'a ekle
        List<Hotel> hotels = hotelController.getAllHotels();

        comboBoxHotelName.addItem("Hepsi");
        comboBoxCity.addItem("Hepsi");
        ArrayList<String> cities= new ArrayList<>();



        for (Hotel hotel : hotels) {
            comboBoxHotelName.addItem(hotel.getName());
            if (!cities.contains(hotel.getCity())) {
                cities.add(hotel.getCity());
            }
        }
        for(String city: cities) {
            comboBoxCity.addItem(city);            
        }

        // Oda tiplerini combobox'a ekle
        comboBoxRoomType.addItem("Hepsi");
        comboBoxRoomType.addItem("Single room");
        comboBoxRoomType.addItem("Double room");
        comboBoxRoomType.addItem("Junior suite room");
        comboBoxRoomType.addItem("Suite room");

        // Tablo modeli
        tableModel = new DefaultTableModel(new Object[]{"ID", "Otel Adı", "Oda Tipi", "Yetişkin Fiyatı", "Çocuk Fiyatı", "Stok", "Özellikler"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableRooms.setModel(tableModel);

        // Odaları listele
        refreshRoomTable();

        // Filtreleme Butonu Olayı
        buttonFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedHotel = (String) comboBoxHotelName.getSelectedItem();
                String selectedCity = (String) comboBoxCity.getSelectedItem();
                String selectedRoomType = (String) comboBoxRoomType.getSelectedItem();
                // Filtreleme işlemi burada yapılacak
                List<Room> filteredRooms = roomController.filterRooms(selectedHotel.equals("Hepsi") ? "" : selectedHotel,
                        selectedCity.equals("Hepsi") ? "" : selectedCity,
                        selectedRoomType.equals("Hepsi") ? "" : selectedRoomType);
                updateRoomTable(filteredRooms);
            }
        });

        // Oda Ekleme Butonu Olayı
        buttonAddRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddRoomView(roomController, hotelController, null, new Runnable() {
                    @Override
                    public void run() {
                        refreshRoomTable();
                    }
                }).setVisible(true);
            }
        });

        // Oda Güncelleme Butonu Olayı
        buttonUpdateRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableRooms.getSelectedRow();
                if (selectedRow != -1) {
                    int roomId = (int) tableRooms.getValueAt(selectedRow, 0);
                    Room room = roomController.getRoom(roomId);
                    new AddRoomView(roomController, hotelController, room, new Runnable() {
                        @Override
                        public void run() {
                            refreshRoomTable();
                        }
                    }).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(panelMain, "Lütfen güncellemek istediğiniz odayı seçin.");
                }
            }
        });

        // Oda Silme Butonu Olayı
        buttonDeleteRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableRooms.getSelectedRow();
                if (selectedRow != -1) {
                    int roomId = (int) tableRooms.getValueAt(selectedRow, 0);
                    int confirm = JOptionPane.showConfirmDialog(panelMain, "Bu odayı silmek istediğinizden emin misiniz?", "Oda Silme", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        roomController.deleteRoom(roomId);
                        JOptionPane.showMessageDialog(panelMain, "Oda silindi!");
                        refreshRoomTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(panelMain, "Lütfen silmek istediğiniz odayı seçin.");
                }
            }
        });

        // Çıkış Butonu Olayı
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Pencereyi kapat
            }
        });
    }

    private void refreshRoomTable() {
        List<Room> rooms = roomController.getAllRooms();
        updateRoomTable(rooms);
    }

    private void updateRoomTable(List<Room> rooms) {
        tableModel.setRowCount(0); // Mevcut tüm satırları temizle
        for (Room room : rooms) {
            Hotel hotel = hotelController.getHotel(room.getHotelId());
            tableModel.addRow(new Object[]{
                    room.getId(),
                    hotel.getName(),
                    room.getRoomType(),
                    room.getPricePerNightAdult(),
                    room.getPricePerNightChild(),
                    room.getStock(),
                    room.getFeatures() != null ? String.join(", ", room.getFeatures().keySet()) : ""
            });
        }
    }
}
