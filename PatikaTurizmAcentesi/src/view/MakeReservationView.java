package view;

import controller.HotelController;
import controller.ReservationController;
import controller.RoomController;
import model.Reservation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class MakeReservationView extends JFrame {
    private JPanel panelMain;
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
    private JComboBox<String> comboBoxCity;
    private JComboBox<String> comboBoxHotelName;
    private JComboBox<String> comboBoxRoomType;
    private JComboBox<String> comboBoxPensionType;
    private JTextArea textAreaReservationNote;
    private JTextField textFieldTotalPrice;
    private JButton buttonMakeReservation;
    private JPanel panelMakeReservation;
    private JButton buttonExit;

    private ReservationController reservationController;
    private HotelController hotelController;
    private RoomController roomController;

    public MakeReservationView(ReservationController reservationController, HotelController hotelController, RoomController roomController) {
        this.reservationController = reservationController;
        this.hotelController = hotelController;
        this.roomController = roomController;

        setTitle("Rezervasyon Yap");
        setSize(1500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panelMain);

        initComponents();
        initEventHandlers();
    }

    private void initComponents() {
        loadCities();
        loadRoomTypes();
        loadPensionTypes();
        loadDateValues();
    }

    private void initEventHandlers() {
        buttonMakeReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeReservation();
            }
        });

        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        comboBoxCity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadHotels();
            }
        });
    }

    private void loadCities() {
        List<String> cities = hotelController.getCities();
        comboBoxCity.addItem("Hepsi");
        for (String city : cities) {
            comboBoxCity.addItem(city);
        }
    }

    private void loadHotels() {
        String selectedCity = (String) comboBoxCity.getSelectedItem();
        List<String> hotels = hotelController.getHotelsByCity(selectedCity.equals("Hepsi") ? "" : selectedCity);
        comboBoxHotelName.removeAllItems();
        comboBoxHotelName.addItem("Hepsi");
        for (String hotel : hotels) {
            comboBoxHotelName.addItem(hotel);
        }
    }

    private void loadRoomTypes() {
        comboBoxRoomType.removeAllItems();
        comboBoxRoomType.addItem("Hepsi");
        comboBoxRoomType.addItem("Single room");
        comboBoxRoomType.addItem("Double room");
        comboBoxRoomType.addItem("Junior suite room");
        comboBoxRoomType.addItem("Suite room");
    }

    private void loadPensionTypes() {
        comboBoxPensionType.removeAllItems();
        comboBoxPensionType.addItem("Hepsi");
        comboBoxPensionType.addItem("Ultra Her şey Dahil");
        comboBoxPensionType.addItem("Her şey Dahil");
        comboBoxPensionType.addItem("Oda Kahvaltı");
        comboBoxPensionType.addItem("Tam Pansiyon");
        comboBoxPensionType.addItem("Yarım Pansiyon");
        comboBoxPensionType.addItem("Sadece Yatak");
        comboBoxPensionType.addItem("Alkol Hariç Full credit");
    }

    private void loadDateValues() {
        int currentYear = LocalDate.now().getYear();
        for (int i = currentYear; i <= currentYear + 10; i++) {
            comboBoxStartYear.addItem(i);
            comboBoxEndYear.addItem(i);
        }
        for (int i = 1; i <= 12; i++) {
            comboBoxStartMonth.addItem(i);
            comboBoxEndMonth.addItem(i);
        }
        for (int i = 1; i <= 31; i++) {
            comboBoxStartDay.addItem(i);
            comboBoxEndDay.addItem(i);
        }
    }

    private void makeReservation() {
        String customerName = textFieldCustomerName.getText();
        String customerNationality = textFieldCustomerNationality.getText();
        String customerIdentityNum = textFieldCustomerIdentityNum.getText();
        String customerPhone = textFieldCustomerPhone.getText();
        String customerEmail = textFieldCustomerEmail.getText();
        String guestName = textFieldGuestName.getText();
        String guestNationality = textFieldGuestNationality.getText();
        String guestIdentity = textFieldGuestIdentity.getText();
        String childName = textFieldChildName.getText();
        String childNationality = textFieldChildNationality.getText();
        String childIdentityNum = textFieldChildIdentityNum.getText();
        String child2Name = textFieldChild2Name.getText();
        String child2Nationality = textFieldChild2Nationality.getText();
        String child2IdentityNum = textFieldChild2IdentityNum.getText();

        int startDay = (int) comboBoxStartDay.getSelectedItem();
        int startMonth = (int) comboBoxStartMonth.getSelectedItem();
        int startYear = (int) comboBoxStartYear.getSelectedItem();
        String startDate = startYear + "-" + startMonth + "-" + startDay;

        int endDay = (int) comboBoxEndDay.getSelectedItem();
        int endMonth = (int) comboBoxEndMonth.getSelectedItem();
        int endYear = (int) comboBoxEndYear.getSelectedItem();
        String endDate = endYear + "-" + endMonth + "-" + endDay;

        String city = (String) comboBoxCity.getSelectedItem();
        String hotelName = (String) comboBoxHotelName.getSelectedItem();
        String roomType = (String) comboBoxRoomType.getSelectedItem();
        String pensionType = (String) comboBoxPensionType.getSelectedItem();
        String reservationNote = textAreaReservationNote.getText();
        double totalPrice = Double.parseDouble(textFieldTotalPrice.getText());

        Reservation reservation = new Reservation();
        reservation.setCustomerName(customerName);
        reservation.setCustomerNationality(customerNationality);
        reservation.setCustomerIdentityNum(customerIdentityNum);
        reservation.setCustomerPhone(customerPhone);
        reservation.setCustomerEmail(customerEmail);
        reservation.setGuestName(guestName);
        reservation.setGuestNationality(guestNationality);
        reservation.setGuestIdentity(guestIdentity);
        reservation.setChildName(childName);
        reservation.setChildNationality(childNationality);
        reservation.setChildIdentityNum(childIdentityNum);
        reservation.setChild2Name(child2Name);
        reservation.setChild2Nationality(child2Nationality);
        reservation.setChild2IdentityNum(child2IdentityNum);
        reservation.setStartDate(Date.valueOf(startDate));
        reservation.setEndDate(Date.valueOf(endDate));
        reservation.setHotelName(hotelName);
        reservation.setTotalPrice(totalPrice);
        reservation.setReservationNote(reservationNote);

        reservationController.makeReservation(reservation);

        JOptionPane.showMessageDialog(panelMain, "Rezervasyon başarıyla yapıldı!");
        clearReservationForm();
    }

    private void clearReservationForm() {
        textFieldCustomerName.setText("");
        textFieldCustomerNationality.setText("");
        textFieldCustomerIdentityNum.setText("");
        textFieldCustomerPhone.setText("");
        textFieldCustomerEmail.setText("");
        textFieldGuestName.setText("");
        textFieldGuestNationality.setText("");
        textFieldGuestIdentity.setText("");
        textFieldChildName.setText("");
        textFieldChildNationality.setText("");
        textFieldChildIdentityNum.setText("");
        textFieldChild2Name.setText("");
        textFieldChild2Nationality.setText("");
        textFieldChild2IdentityNum.setText("");
        comboBoxStartDay.setSelectedIndex(0);
        comboBoxStartMonth.setSelectedIndex(0);
        comboBoxStartYear.setSelectedIndex(0);
        comboBoxEndDay.setSelectedIndex(0);
        comboBoxEndMonth.setSelectedIndex(0);
        comboBoxEndYear.setSelectedIndex(0);
        comboBoxCity.setSelectedIndex(0);
        comboBoxHotelName.setSelectedIndex(0);
        comboBoxRoomType.setSelectedIndex(0);
        comboBoxPensionType.setSelectedIndex(0);
        textAreaReservationNote.setText("");
        textFieldTotalPrice.setText("");
    }
}
