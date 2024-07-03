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
import java.time.format.DateTimeFormatter;
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
    private JButton buttonMakeReservation;
    private JPanel panelMakeReservation;
    private JButton buttonExit;
    private JLabel labelTotalPrice;

    private ReservationController reservationController;
    private HotelController hotelController;
    private RoomController roomController;

    // Constructor for RoomSearchPricingView
    public MakeReservationView(ReservationController reservationController, HotelController hotelController, RoomController roomController, String city, String hotelName, String roomType, String pensionType, String checkInDate, String checkOutDate) {
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

        // Set the reservation details
        setReservationDetails(city, hotelName, roomType, pensionType, checkInDate, checkOutDate);
    }

    // Default constructor for ReservationManagementMainView
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

        comboBoxHotelName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadRoomTypes();
                loadPensionTypes();
                calculateTotalPrice();
            }
        });

        comboBoxStartDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotalPrice();
            }
        });

        comboBoxStartMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotalPrice();
            }
        });

        comboBoxStartYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotalPrice();
            }
        });

        comboBoxEndDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotalPrice();
            }
        });

        comboBoxEndMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotalPrice();
            }
        });

        comboBoxEndYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotalPrice();
            }
        });

        comboBoxRoomType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotalPrice();
            }
        });

        comboBoxPensionType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotalPrice();
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
        String selectedHotel = (String) comboBoxHotelName.getSelectedItem();
        if (selectedHotel != null && !selectedHotel.equals("Hepsi")) {
            List<String> roomTypes = roomController.getRoomTypesByHotel(selectedHotel);
            comboBoxRoomType.removeAllItems();
            comboBoxRoomType.addItem("Hepsi");
            for (String roomType : roomTypes) {
                comboBoxRoomType.addItem(roomType);
            }
        } else {
            comboBoxRoomType.removeAllItems();
            comboBoxRoomType.addItem("Hepsi");
        }
    }

    private void loadPensionTypes() {
        String selectedHotel = (String) comboBoxHotelName.getSelectedItem();
        if (selectedHotel != null && !selectedHotel.equals("Hepsi")) {
            List<String> pensionTypes = roomController.getPensionTypesByHotel(selectedHotel);
            comboBoxPensionType.removeAllItems();
            comboBoxPensionType.addItem("Hepsi");
            for (String pensionType : pensionTypes) {
                comboBoxPensionType.addItem(pensionType);
            }
        } else {
            comboBoxPensionType.removeAllItems();
            comboBoxPensionType.addItem("Hepsi");
        }
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

    private void setReservationDetails(String city, String hotelName, String roomType, String pensionType, String checkInDate, String checkOutDate) {
        comboBoxCity.setSelectedItem(city);
        comboBoxHotelName.setSelectedItem(hotelName);
        comboBoxRoomType.setSelectedItem(roomType);
        comboBoxPensionType.setSelectedItem(pensionType);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate checkIn = LocalDate.parse(checkInDate, formatter);
        LocalDate checkOut = LocalDate.parse(checkOutDate, formatter);

        comboBoxStartYear.setSelectedItem(checkIn.getYear());
        comboBoxStartMonth.setSelectedItem(checkIn.getMonthValue());
        comboBoxStartDay.setSelectedItem(checkIn.getDayOfMonth());

        comboBoxEndYear.setSelectedItem(checkOut.getYear());
        comboBoxEndMonth.setSelectedItem(checkOut.getMonthValue());
        comboBoxEndDay.setSelectedItem(checkOut.getDayOfMonth());

        calculateTotalPrice();
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
        double totalPrice = Double.parseDouble(labelTotalPrice.getText());

        // Room ID'yi almak için roomController kullanarak room ID'yi alıyoruz.
        int roomId = roomController.getRoomIdByDetails(hotelName, roomType, pensionType);

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
        reservation.setRoomId(roomId);
        reservation.setRoomType(roomType);       // Yeni alan
        reservation.setPensionType(pensionType); // Yeni alan
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
    }

    private void calculateTotalPrice() {
        int startDay = (int) comboBoxStartDay.getSelectedItem();
        int startMonth = (int) comboBoxStartMonth.getSelectedItem();
        int startYear = (int) comboBoxStartYear.getSelectedItem();
        String startDate = startYear + "-" + startMonth + "-" + startDay;

        int endDay = (int) comboBoxEndDay.getSelectedItem();
        int endMonth = (int) comboBoxEndMonth.getSelectedItem();
        int endYear = (int) comboBoxEndYear.getSelectedItem();
        String endDate = endYear + "-" + endMonth + "-" + endDay;

        LocalDate start = LocalDate.of(startYear, startMonth, startDay);
        LocalDate end = LocalDate.of(endYear, endMonth, endDay);
        long nights = java.time.temporal.ChronoUnit.DAYS.between(start, end);

        String selectedHotel = (String) comboBoxHotelName.getSelectedItem();
        String selectedRoomType = (String) comboBoxRoomType.getSelectedItem();
        String selectedPensionType = (String) comboBoxPensionType.getSelectedItem();

        double pricePerNight = roomController.getPricePerNight(selectedHotel, selectedRoomType, selectedPensionType);
        double totalPrice = pricePerNight * nights;

        labelTotalPrice.setText(String.valueOf(totalPrice));
    }
}
