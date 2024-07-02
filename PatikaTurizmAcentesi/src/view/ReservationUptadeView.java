package view;

import controller.ReservationController;
import controller.HotelController;
import model.Reservation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class ReservationUptadeView extends JFrame {
    private JPanel panelMain;

    // Make Reservation Tab Components
    private JTextField textFieldCustomerName;
    private JTextField textFieldCustomerNationality;
    private JTextField textFieldCustomerIdentityNum;
    private JTextField textFieldCustomerPhone;
    private JTextField textFieldCustomerEmail;
    private JTextField textFieldGuestName;
    private JTextField textFieldGuestNationality;
    private JTextField textFieldGuestIdentity;
    private JTextField textFieldChildName;
    private JTextField textFieldChildNationality;
    private JTextField textFieldChildIdentityNum;
    private JTextField textFieldChild2Name;
    private JTextField textFieldChild2Nationality;
    private JTextField textFieldChild2IdentityNum;
    private JComboBox<Integer> comboBoxStartDay;
    private JComboBox<Integer> comboBoxStartMonth;
    private JComboBox<Integer> comboBoxStartYear;
    private JComboBox<Integer> comboBoxEndDay;
    private JComboBox<Integer> comboBoxEndMonth;
    private JComboBox<Integer> comboBoxEndYear;
    private JComboBox<String> comboBoxCityName;
    private JComboBox<String> comboBoxHotelName;
    private JComboBox<String> comboBoxRoomType;
    private JComboBox<String> comboBoxPensionType;
    private JTextField textFieldTotalPrice;
    private JTextArea textAreaReservationNote;
    private JButton buttonSave;
    private JButton buttonCancel;

    private ReservationController reservationController;
    private HotelController hotelController;
    private Reservation reservation;

    public ReservationUptadeView(ReservationController reservationController, HotelController hotelController, Reservation reservation, Runnable onReservationUpdatedCallback) {
        this.reservationController = reservationController;
        this.hotelController = hotelController;
        this.reservation = reservation;

        setTitle("Rezervasyon Güncelle");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panelMain);

        // ComboBoxları doldur
        for (int i = 1; i <= 31; i++) {
            comboBoxStartDay.addItem(i);
            comboBoxEndDay.addItem(i);
        }
        for (int i = 1; i <= 12; i++) {
            comboBoxStartMonth.addItem(i);
            comboBoxEndMonth.addItem(i);
        }
        for (int i = 2023; i <= 2033; i++) {
            comboBoxStartYear.addItem(i);
            comboBoxEndYear.addItem(i);
        }

        // Şehir bilgilerini yükle
        loadCities();

        // Mevcut rezervasyon bilgilerini doldur
        loadReservationDetails();

        // Şehir seçimi değiştiğinde otel isimlerini güncelle
        comboBoxCityName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCity = (String) comboBoxCityName.getSelectedItem();
                loadHotels(selectedCity);
            }
        });

        // Kaydet butonu olayı
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateReservation();
                onReservationUpdatedCallback.run();
                dispose();
            }
        });

        // İptal butonu olayı
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void loadCities() {
        List<String> cities = hotelController.getCities();
        for (String city : cities) {
            comboBoxCityName.addItem(city);
        }
    }

    private void loadHotels(String city) {
        comboBoxHotelName.removeAllItems();
        List<String> hotels = hotelController.getHotelsByCity(city);
        for (String hotel : hotels) {
            comboBoxHotelName.addItem(hotel);
        }
    }

    private void loadReservationDetails() {
        textFieldCustomerName.setText(reservation.getCustomerName());
        textFieldCustomerNationality.setText(reservation.getCustomerNationality());
        textFieldCustomerIdentityNum.setText(reservation.getCustomerIdentityNum());
        textFieldCustomerPhone.setText(reservation.getCustomerPhone());
        textFieldCustomerEmail.setText(reservation.getCustomerEmail());
        textFieldGuestName.setText(reservation.getGuestName());
        textFieldGuestNationality.setText(reservation.getGuestNationality());
        textFieldGuestIdentity.setText(reservation.getGuestIdentity());
        textFieldChildName.setText(reservation.getChildName());
        textFieldChildNationality.setText(reservation.getChildNationality());
        textFieldChildIdentityNum.setText(reservation.getChildIdentityNum());
        textFieldChild2Name.setText(reservation.getChild2Name());
        textFieldChild2Nationality.setText(reservation.getChild2Nationality());
        textFieldChild2IdentityNum.setText(reservation.getChild2IdentityNum());

        String[] startDateParts = reservation.getStartDate().toString().split("-");
        comboBoxStartYear.setSelectedItem(Integer.parseInt(startDateParts[0]));
        comboBoxStartMonth.setSelectedItem(Integer.parseInt(startDateParts[1]));
        comboBoxStartDay.setSelectedItem(Integer.parseInt(startDateParts[2]));

        String[] endDateParts = reservation.getEndDate().toString().split("-");
        comboBoxEndYear.setSelectedItem(Integer.parseInt(endDateParts[0]));
        comboBoxEndMonth.setSelectedItem(Integer.parseInt(endDateParts[1]));
        comboBoxEndDay.setSelectedItem(Integer.parseInt(endDateParts[2]));

        int roomId = reservation.getRoomId();
        String city = hotelController.getCityByRoomId(roomId);
        comboBoxCityName.setSelectedItem(city);
        loadHotels(city);
        comboBoxHotelName.setSelectedItem(reservation.getHotelName());
        comboBoxRoomType.setSelectedItem(reservation.getRoomType());
        comboBoxPensionType.setSelectedItem(reservation.getPensionType());
        textAreaReservationNote.setText(reservation.getReservationNote());
        textFieldTotalPrice.setText(String.valueOf(reservation.getTotalPrice()));
    }

    private void updateReservation() {
        reservation.setCustomerName(textFieldCustomerName.getText());
        reservation.setCustomerNationality(textFieldCustomerNationality.getText());
        reservation.setCustomerIdentityNum(textFieldCustomerIdentityNum.getText());
        reservation.setCustomerPhone(textFieldCustomerPhone.getText());
        reservation.setCustomerEmail(textFieldCustomerEmail.getText());
        reservation.setGuestName(textFieldGuestName.getText());
        reservation.setGuestNationality(textFieldGuestNationality.getText());
        reservation.setGuestIdentity(textFieldGuestIdentity.getText());
        reservation.setChildName(textFieldChildName.getText());
        reservation.setChildNationality(textFieldChildNationality.getText());
        reservation.setChildIdentityNum(textFieldChildIdentityNum.getText());
        reservation.setChild2Name(textFieldChild2Name.getText());
        reservation.setChild2Nationality(textFieldChild2Nationality.getText());
        reservation.setChild2IdentityNum(textFieldChild2IdentityNum.getText());

        int startDay = (int) comboBoxStartDay.getSelectedItem();
        int startMonth = (int) comboBoxStartMonth.getSelectedItem();
        int startYear = (int) comboBoxStartYear.getSelectedItem();
        String startDate = startYear + "-" + startMonth + "-" + startDay;
        reservation.setStartDate(Date.valueOf(startDate));

        int endDay = (int) comboBoxEndDay.getSelectedItem();
        int endMonth = (int) comboBoxEndMonth.getSelectedItem();
        int endYear = (int) comboBoxEndYear.getSelectedItem();
        String endDate = endYear + "-" + endMonth + "-" + endDay;
        reservation.setEndDate(Date.valueOf(endDate));

        reservation.setHotelName((String) comboBoxHotelName.getSelectedItem());
        reservation.setRoomType((String) comboBoxRoomType.getSelectedItem());
        reservation.setPensionType((String) comboBoxPensionType.getSelectedItem());
        reservation.setReservationNote(textAreaReservationNote.getText());
        reservation.setTotalPrice(Double.parseDouble(textFieldTotalPrice.getText()));

        reservationController.updateReservation(reservation);
        JOptionPane.showMessageDialog(panelMain, "Rezervasyon başarıyla güncellendi!");
    }
}
