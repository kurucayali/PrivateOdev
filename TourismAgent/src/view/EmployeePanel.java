package view;

import manager.HotelManager;
import manager.RoomManager;
import model.Hotel;
import model.Room;
import core.DbConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmployeePanel extends JPanel {
    private HotelManager hotelManager;
    private RoomManager roomManager;
    private JTable hotelTable;
    private DefaultTableModel hotelModel;
    private JTable roomTable;
    private DefaultTableModel roomModel;

    public EmployeePanel() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Otel ve Oda Yönetimi");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Otel tablosu ve model oluşturuluyor
        hotelModel = new DefaultTableModel();
        hotelModel.setColumnIdentifiers(new String[]{"ID", "Otel Adı", "Adres", "E-posta", "Telefon", "Yıldız"});
        hotelTable = new JTable(hotelModel);
        JScrollPane hotelScrollPane = new JScrollPane(hotelTable);

        // Oda tablosu ve model oluşturuluyor
        roomModel = new DefaultTableModel();
        roomModel.setColumnIdentifiers(new String[]{"ID", "Otel ID", "Oda Tipi", "Fiyat (Yetişkin)", "Fiyat (Çocuk)", "Stok"});
        roomTable = new JTable(roomModel);
        JScrollPane roomScrollPane = new JScrollPane(roomTable);

        // Otel ekleme butonu oluşturuluyor
        JButton addHotelButton = new JButton("Otel Ekle");
        addHotelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addHotel();
            }
        });

        // Oda ekleme butonu oluşturuluyor
        JButton addRoomButton = new JButton("Oda Ekle");
        addRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRoom();
            }
        });

        // Butonlar paneli oluşturuluyor
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addHotelButton);
        buttonPanel.add(addRoomButton);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, hotelScrollPane, roomScrollPane);
        splitPane.setDividerLocation(300);

        add(titleLabel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Veritabanı bağlantısı ve manager oluşturuluyor
        try {
            Connection connection = DbConnection.getConnection();
            hotelManager = new HotelManager(connection);
            roomManager = new RoomManager(connection);
            loadHotels();
            loadRooms();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Veritabanı hatası: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Otelleri tabloya yükleyen metot
    private void loadHotels() {
        try {
            List<Hotel> hotels = hotelManager.getAllHotels();
            hotelModel.setRowCount(0); // Tablonun sıfırlanması
            for (Hotel hotel : hotels) {
                hotelModel.addRow(new Object[]{hotel.getId(), hotel.getName(), hotel.getAddress(), hotel.getEmail(), hotel.getPhone(), hotel.getStars()});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Oteller yüklenemedi: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Odaları tabloya yükleyen metot
    private void loadRooms() {
        try {
            List<Room> rooms = roomManager.getRoomsByHotelId(1); // Otel ID'yi gerektiği şekilde değiştirin
            roomModel.setRowCount(0); // Tablonun sıfırlanması
            for (Room room : rooms) {
                roomModel.addRow(new Object[]{room.getId(), room.getHotelId(), room.getRoomType(), room.getPricePerNightAdult(), room.getPricePerNightChild(), room.getStock()});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Odalar yüklenemedi: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Otel ekleme işlemi
    private void addHotel() {
        // Otel ekleme formu oluşturuluyor
        JTextField nameField = new JTextField(15);
        JTextField addressField = new JTextField(15);
        JTextField emailField = new JTextField(15);
        JTextField phoneField = new JTextField(15);
        JTextField starsField = new JTextField(15);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Otel Adı:"));
        panel.add(nameField);
        panel.add(new JLabel("Adres:"));
        panel.add(addressField);
        panel.add(new JLabel("E-posta:"));
        panel.add(emailField);
        panel.add(new JLabel("Telefon:"));
        panel.add(phoneField);
        panel.add(new JLabel("Yıldız:"));
        panel.add(starsField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Yeni Otel Ekle", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            Hotel hotel = new Hotel();
            hotel.setName(nameField.getText());
            hotel.setAddress(addressField.getText());
            hotel.setEmail(emailField.getText());
            hotel.setPhone(phoneField.getText());
            hotel.setStars(Integer.parseInt(starsField.getText()));

            try {
                hotelManager.addHotel(hotel);
                loadHotels();
                JOptionPane.showMessageDialog(this, "Otel başarıyla eklendi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Otel eklenemedi: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Oda ekleme işlemi
    private void addRoom() {
        // Oda ekleme formu oluşturuluyor
        JTextField hotelIdField = new JTextField(15);
        JTextField roomTypeField = new JTextField(15);
        JTextField priceAdultField = new JTextField(15);
        JTextField priceChildField = new JTextField(15);
        JTextField stockField = new JTextField(15);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Otel ID:"));
        panel.add(hotelIdField);
        panel.add(new JLabel("Oda Tipi:"));
        panel.add(roomTypeField);
        panel.add(new JLabel("Fiyat (Yetişkin):"));
        panel.add(priceAdultField);
        panel.add(new JLabel("Fiyat (Çocuk):"));
        panel.add(priceChildField);
        panel.add(new JLabel("Stok:"));
        panel.add(stockField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Yeni Oda Ekle", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            Room room = new Room();
            room.setHotelId(Integer.parseInt(hotelIdField.getText()));
            room.setRoomType(roomTypeField.getText());
            room.setPricePerNightAdult(new BigDecimal(priceAdultField.getText()));
            room.setPricePerNightChild(new BigDecimal(priceChildField.getText()));
            room.setStock(Integer.parseInt(stockField.getText()));

            try {
                roomManager.addRoom(room);
                loadRooms();
                JOptionPane.showMessageDialog(this, "Oda başarıyla eklendi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Oda eklenemedi: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
