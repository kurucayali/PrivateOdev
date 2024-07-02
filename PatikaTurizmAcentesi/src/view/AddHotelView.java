package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.HotelController;
import model.Hotel;

import java.util.ArrayList;
import java.util.List;

public class AddHotelView extends JFrame {
    private JPanel panelMain;
    private JTextField textFieldHotelName;
    private JTextField textFieldAddress;
    private JTextField textFieldEmail;
    private JTextField textFieldPhone;
    private JTextField textFieldCity;
    private JComboBox<String> comboBoxStars;
    private JButton buttonSave;
    private JCheckBox checkBoxParking;
    private JCheckBox checkBoxWiFi;
    private JCheckBox checkBoxPool;
    private JCheckBox checkBoxFitness;
    private JCheckBox checkBoxConcierge;
    private JCheckBox checkBoxSpa;
    private JCheckBox checkBoxRoomService;
    private JComboBox<String> comboBoxPensionTypes;
    private JButton buttonExit;

    private Hotel hotel;
    private Runnable onHotelSavedCallback;

    public AddHotelView(HotelController hotelController, Hotel hotelToUpdate, Runnable onHotelSavedCallback) {
        this.hotel = hotelToUpdate;
        this.onHotelSavedCallback = onHotelSavedCallback;

        setTitle(hotel == null ? "Yeni Otel Ekle" : "Otel Güncelle");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panelMain);

        // Yıldız seçeneklerini ekle
        for (int i = 1; i <= 5; i++) {
            comboBoxStars.addItem(String.valueOf(i));
        }

        // Pansiyon Tipleri seçeneklerini ekle
        comboBoxPensionTypes.addItem("Ultra Her şey Dahil");
        comboBoxPensionTypes.addItem("Her şey Dahil");
        comboBoxPensionTypes.addItem("Oda Kahvaltı");
        comboBoxPensionTypes.addItem("Tam Pansiyon");
        comboBoxPensionTypes.addItem("Yarım Pansiyon");
        comboBoxPensionTypes.addItem("Sadece Yatak");
        comboBoxPensionTypes.addItem("Alkol Hariç Full credit");

        // Güncelleme için otel bilgilerini doldur
        if (hotel != null) {
            textFieldHotelName.setText(hotel.getName());
            textFieldAddress.setText(hotel.getAddress());
            textFieldEmail.setText(hotel.getEmail());
            textFieldPhone.setText(hotel.getPhone());
            textFieldCity.setText(hotel.getCity());
            comboBoxStars.setSelectedItem(String.valueOf(hotel.getStars()));

            // Özellikleri ayarla
            List<String> features = hotel.getFeatures();
            checkBoxParking.setSelected(features.contains("Ücretsiz Otopark"));
            checkBoxWiFi.setSelected(features.contains("Ücretsiz WiFi"));
            checkBoxPool.setSelected(features.contains("Yüzme Havuzu"));
            checkBoxFitness.setSelected(features.contains("Fitness Center"));
            checkBoxConcierge.setSelected(features.contains("Hotel Concierge"));
            checkBoxSpa.setSelected(features.contains("SPA"));
            checkBoxRoomService.setSelected(features.contains("7/24 Oda Servisi"));

            // Pansiyon tiplerini ayarla
            List<String> pensionTypes = hotel.getPensionTypes();
            for (String pensionType : pensionTypes) {
                comboBoxPensionTypes.setSelectedItem(pensionType);
            }
        }

        // Kaydet Butonu Olayı
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textFieldHotelName.getText();
                String address = textFieldAddress.getText();
                String email = textFieldEmail.getText();
                String phone = textFieldPhone.getText();
                String city = textFieldCity.getText();
                int stars = Integer.parseInt(comboBoxStars.getSelectedItem().toString());

                // Özellikleri al
                List<String> features = new ArrayList<>();
                if (checkBoxParking.isSelected()) features.add("Ücretsiz Otopark");
                if (checkBoxWiFi.isSelected()) features.add("Ücretsiz WiFi");
                if (checkBoxPool.isSelected()) features.add("Yüzme Havuzu");
                if (checkBoxFitness.isSelected()) features.add("Fitness Center");
                if (checkBoxConcierge.isSelected()) features.add("Hotel Concierge");
                if (checkBoxSpa.isSelected()) features.add("SPA");
                if (checkBoxRoomService.isSelected()) features.add("7/24 Oda Servisi");

                // Pansiyon tiplerini al
                List<String> pensionTypes = new ArrayList<>();
                pensionTypes.add(comboBoxPensionTypes.getSelectedItem().toString());

                if (hotel == null) {
                    // Yeni otel ekle
                    hotel = new Hotel();
                    hotel.setName(name);
                    hotel.setAddress(address);
                    hotel.setEmail(email);
                    hotel.setPhone(phone);
                    hotel.setCity(city);
                    hotel.setStars(stars);
                    hotel.setFeatures(features);
                    hotel.setPensionTypes(pensionTypes);

                    hotelController.addHotel(hotel);
                    JOptionPane.showMessageDialog(panelMain, "Otel eklendi!");
                } else {
                    // Otel güncelle
                    hotel.setName(name);
                    hotel.setAddress(address);
                    hotel.setEmail(email);
                    hotel.setPhone(phone);
                    hotel.setCity(city);
                    hotel.setStars(stars);
                    hotel.setFeatures(features);
                    hotel.setPensionTypes(pensionTypes);

                    hotelController.updateHotel(hotel);
                    JOptionPane.showMessageDialog(panelMain, "Otel güncellendi!");
                }
                dispose();
                onHotelSavedCallback.run();  // Otel kaydedildiğinde callback'i çalıştır
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
