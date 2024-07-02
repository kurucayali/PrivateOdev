package view;

import controller.RoomController;
import controller.HotelController;
import model.Room;
import model.Hotel;
import model.Season;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddRoomView extends JFrame {
    private JPanel panelMain;
    private JComboBox<String> comboBoxHotel;
    private JComboBox<String> comboBoxRoomType;
    private JTextField textFieldPricePerNightAdult;
    private JTextField textFieldPricePerNightChild;
    private JTextField textFieldStock;
    private JTextField textFieldSquareMeters;
    private JCheckBox checkBoxTelevision;
    private JCheckBox checkBoxMinibar;
    private JCheckBox checkBoxGameConsole;
    private JCheckBox checkBoxSafe;
    private JCheckBox checkBoxProjector;
    private JComboBox<String> comboBoxSeason;
    private JComboBox<String> comboBoxPensionType;
    private JButton buttonSave;
    private JButton buttonExit;

    private RoomController roomController;
    private HotelController hotelController;
    private Room roomToUpdate;
    private Runnable onRoomSavedCallback;

    public AddRoomView(RoomController roomController, HotelController hotelController, Room roomToUpdate, Runnable onRoomSavedCallback) {
        this.roomController = roomController;
        this.hotelController = hotelController;
        this.roomToUpdate = roomToUpdate;
        this.onRoomSavedCallback = onRoomSavedCallback;

        setTitle(roomToUpdate == null ? "Yeni Oda Ekle" : "Oda Güncelle");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panelMain);

        // Otel isimlerini combobox'a ekle
        List<Hotel> hotels = hotelController.getAllHotels();
        for (Hotel hotel : hotels) {
            comboBoxHotel.addItem(hotel.getName());
        }

        // Oda tipi seçeneklerini combobox'a ekle
        comboBoxRoomType.addItem("Single room");
        comboBoxRoomType.addItem("Double room");
        comboBoxRoomType.addItem("Junior suite room");
        comboBoxRoomType.addItem("Suite room");

        // Dönemleri combobox'a ekle
        List<Season> seasons = hotelController.getAllSeasons();
        for (Season season : seasons) {
            comboBoxSeason.addItem(season.getName());
        }

        // Pansiyon tiplerini combobox'a ekle
        comboBoxPensionType.addItem("Ultra Her şey Dahil");
        comboBoxPensionType.addItem("Her şey Dahil");
        comboBoxPensionType.addItem("Oda Kahvaltı");
        comboBoxPensionType.addItem("Tam Pansiyon");
        comboBoxPensionType.addItem("Yarım Pansiyon");
        comboBoxPensionType.addItem("Sadece Yatak");
        comboBoxPensionType.addItem("Alkol Hariç Full credit");

        // Güncelleme için oda bilgilerini doldur
        if (roomToUpdate != null) {
            comboBoxHotel.setSelectedItem(hotelController.getHotel(roomToUpdate.getHotelId()).getName());
            comboBoxRoomType.setSelectedItem(roomToUpdate.getRoomType());
            textFieldPricePerNightAdult.setText(String.valueOf(roomToUpdate.getPricePerNightAdult()));
            textFieldPricePerNightChild.setText(String.valueOf(roomToUpdate.getPricePerNightChild()));
            textFieldStock.setText(String.valueOf(roomToUpdate.getStock()));
            textFieldSquareMeters.setText(roomToUpdate.getFeatures() != null ? roomToUpdate.getFeatures().getOrDefault("Metrekare", "").toString() : "");
            checkBoxTelevision.setSelected(roomToUpdate.getFeatures() != null && (boolean) roomToUpdate.getFeatures().getOrDefault("Televizyon", false));
            checkBoxMinibar.setSelected(roomToUpdate.getFeatures() != null && (boolean) roomToUpdate.getFeatures().getOrDefault("Minibar", false));
            checkBoxGameConsole.setSelected(roomToUpdate.getFeatures() != null && (boolean) roomToUpdate.getFeatures().getOrDefault("Oyun Konsolu", false));
            checkBoxSafe.setSelected(roomToUpdate.getFeatures() != null && (boolean) roomToUpdate.getFeatures().getOrDefault("Kasa", false));
            checkBoxProjector.setSelected(roomToUpdate.getFeatures() != null && (boolean) roomToUpdate.getFeatures().getOrDefault("Projeksiyon", false));
            comboBoxSeason.setSelectedItem(hotelController.getSeason(roomToUpdate.getSeasonId()).getName());
            comboBoxPensionType.setSelectedItem(hotelController.getPensionType(roomToUpdate.getPensionTypeId()).getType());
        }

        // Kaydet Butonu Olayı
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = (String) comboBoxHotel.getSelectedItem();
                String roomType = (String) comboBoxRoomType.getSelectedItem();
                double pricePerNightAdult = Double.parseDouble(textFieldPricePerNightAdult.getText());
                double pricePerNightChild = Double.parseDouble(textFieldPricePerNightChild.getText());
                int stock = Integer.parseInt(textFieldStock.getText());
                String squareMeters = textFieldSquareMeters.getText();
                boolean television = checkBoxTelevision.isSelected();
                boolean minibar = checkBoxMinibar.isSelected();
                boolean gameConsole = checkBoxGameConsole.isSelected();
                boolean safe = checkBoxSafe.isSelected();
                boolean projector = checkBoxProjector.isSelected();
                String seasonName = (String) comboBoxSeason.getSelectedItem();
                String pensionType = (String) comboBoxPensionType.getSelectedItem();

                int hotelId = hotelController.getHotelByName(hotelName).getId();
                int seasonId = hotelController.getSeasonByName(seasonName).getId();
                int pensionTypeId = hotelController.getPensionTypeByName(pensionType).getId();

                Map<String, Object> features = new HashMap<>();
                features.put("Metrekare", squareMeters);
                features.put("Televizyon", television);
                features.put("Minibar", minibar);
                features.put("Oyun Konsolu", gameConsole);
                features.put("Kasa", safe);
                features.put("Projeksiyon", projector);

                if (roomToUpdate == null) {
                    // Yeni oda ekle
                    Room room = new Room(0, hotelId, seasonId, pensionTypeId, roomType, pricePerNightAdult, pricePerNightChild, stock, features);
                    roomController.addRoom(room);
                    JOptionPane.showMessageDialog(panelMain, "Oda eklendi!");
                } else {
                    // Oda güncelle
                    roomToUpdate.setHotelId(hotelId);
                    roomToUpdate.setSeasonId(seasonId);
                    roomToUpdate.setPensionTypeId(pensionTypeId);
                    roomToUpdate.setRoomType(roomType);
                    roomToUpdate.setPricePerNightAdult(pricePerNightAdult);
                    roomToUpdate.setPricePerNightChild(pricePerNightChild);
                    roomToUpdate.setStock(stock);
                    roomToUpdate.setFeatures(features);
                    roomController.updateRoom(roomToUpdate);
                    JOptionPane.showMessageDialog(panelMain, "Oda güncellendi!");
                }
                dispose();
                onRoomSavedCallback.run();  // Oda kaydedildiğinde callback'i çalıştır
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
}
