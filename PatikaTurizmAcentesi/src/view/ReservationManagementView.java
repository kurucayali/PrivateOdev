package view;

import controller.HotelController;
import controller.ReservationController;
import controller.RoomController;
import model.Reservation;
import model.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

public class ReservationManagementView extends JFrame {
    private JPanel panelMain;
    private JTabbedPane tabbedPane;
    private JPanel panelMakeReservation;
    private JPanel panelSearchUpdateDelete;
    private JPanel panelRoomSearchPricing;

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
    private JComboBox<String> comboBoxCity;
    private JComboBox<String> comboBoxHotelName;
    private JComboBox<String> comboBoxRoomType;
    private JComboBox<String> comboBoxPensionType;
    private JTextField textFieldTotalPrice;
    private JTextArea textAreaReservationNote;
    private JButton buttonMakeReservation;
    private JButton buttonExit1;

    // Search, Update, Delete Tab Components
    private JTextField textFieldSearchCustomerName;
    private JTextField textFieldSearchCustomerIdentity;
    private JTextField textFieldSearchHotelName;
    private JButton buttonSearch;
    private JTable tableReservations;
    private JButton buttonUpdateReservation;
    private JButton buttonDeleteReservation;
    private JButton buttonExit2;
    private DefaultTableModel tableModelReservations;

    // Room Search and Pricing Tab Components
    private JComboBox<Integer> comboBoxCheckInDay;
    private JComboBox<Integer> comboBoxCheckInMonth;
    private JComboBox<Integer> comboBoxCheckInYear;
    private JComboBox<Integer> comboBoxCheckOutDay;
    private JComboBox<Integer> comboBoxCheckOutMonth;
    private JComboBox<Integer> comboBoxCheckOutYear;
    private JComboBox<String> comboBoxSearchCity;
    private JComboBox<String> comboBoxSearchHotelName;
    private JTextField textFieldAdultCount;
    private JTextField textFieldChildCount;
    private JTable tableRooms;
    private JButton buttonListRooms;
    private JButton buttonExit3;
    private DefaultTableModel tableModelRooms;

    private ReservationController reservationController;
    private HotelController hotelController;
    private RoomController roomController;

    // Constructor
    public ReservationManagementView(ReservationController reservationController, HotelController hotelController, RoomController roomController) {
        this.reservationController = reservationController;
        this.hotelController = hotelController;
        this.roomController = roomController;

        setTitle("Rezervasyon Yönetim Ekranı");
        setSize(1500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panelMain);

        initComponents();
        initTableModels();
        initEventHandlers();
        loadInitialData();
    }

    // Initialize components
    private void initComponents() {
        comboBoxCity = new JComboBox<>();
        comboBoxHotelName = new JComboBox<>();
        comboBoxRoomType = new JComboBox<>();
        comboBoxPensionType = new JComboBox<>();

        comboBoxStartDay = new JComboBox<>();
        comboBoxStartMonth = new JComboBox<>();
        comboBoxStartYear = new JComboBox<>();
        comboBoxEndDay = new JComboBox<>();
        comboBoxEndMonth = new JComboBox<>();
        comboBoxEndYear = new JComboBox<>();

        comboBoxCheckInDay = new JComboBox<>();
        comboBoxCheckInMonth = new JComboBox<>();
        comboBoxCheckInYear = new JComboBox<>();
        comboBoxCheckOutDay = new JComboBox<>();
        comboBoxCheckOutMonth = new JComboBox<>();
        comboBoxCheckOutYear = new JComboBox<>();

        comboBoxSearchCity = new JComboBox<>();
        comboBoxSearchHotelName = new JComboBox<>();
    }

    private void loadInitialData() {
        loadCities();
        loadHotels();
        loadRoomTypes();
        loadPensionTypes();
        loadDateValues();
    }

    // Initialize table models
    private void initTableModels() {
        tableModelReservations = new DefaultTableModel(new Object[]{"ID", "Müşteri Adı", "Otel Adı", "Oda Tipi", "Başlangıç Tarihi", "Bitiş Tarihi", "Toplam Fiyat"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableReservations.setModel(tableModelReservations);

        tableModelRooms = new DefaultTableModel(new Object[]{"Otel Adı", "Oda Tipi", "Pansiyon Tipi", "Fiyat", "Toplam Fiyat"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableRooms.setModel(tableModelRooms);
    }

    // Initialize event handlers
    private void initEventHandlers() {
        // Make Reservation Tab
        buttonMakeReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeReservation();
            }
        });

        buttonExit1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Search, Update, Delete Tab
        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchReservations();
            }
        });

        buttonUpdateReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateReservation();
            }
        });

        buttonDeleteReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteReservation();
            }
        });

        buttonExit2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Room Search and Pricing Tab
        buttonListRooms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listRooms();
            }
        });

        buttonExit3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    // Load cities from the database
    private void loadCities() {
        List<String> cities = hotelController.getCities();
        comboBoxCity.addItem("Hepsi");
        comboBoxSearchCity.addItem("Hepsi");
        for (String city : cities) {
            comboBoxCity.addItem(city);
            comboBoxSearchCity.addItem(city);
        }
        comboBoxCity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadHotels();
            }
        });
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
        // Yıl, ay ve gün combobox'larını doldur
        int currentYear = LocalDate.now().getYear();
        for (int i = currentYear; i <= currentYear + 10; i++) {
            comboBoxStartYear.addItem(i);
            comboBoxEndYear.addItem(i);
            comboBoxCheckInYear.addItem(i);
            comboBoxCheckOutYear.addItem(i);
        }
        for (int i = 1; i <= 12; i++) {
            comboBoxStartMonth.addItem(i);
            comboBoxEndMonth.addItem(i);
            comboBoxCheckInMonth.addItem(i);
            comboBoxCheckOutMonth.addItem(i);
        }
        for (int i = 1; i <= 31; i++) {
            comboBoxStartDay.addItem(i);
            comboBoxEndDay.addItem(i);
            comboBoxCheckInDay.addItem(i);
            comboBoxCheckOutDay.addItem(i);
        }
    }

    // Make reservation method
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

    // Clear reservation form
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

    // Search reservations method
    private void searchReservations() {
        String customerName = textFieldSearchCustomerName.getText();
        String customerIdentity = textFieldSearchCustomerIdentity.getText();
        String hotelName = textFieldSearchHotelName.getText();

        List<Reservation> reservations = reservationController.searchReservations(customerName, customerIdentity, hotelName);
        updateReservationsTable(reservations);
    }

    // Update reservations table
    private void updateReservationsTable(List<Reservation> reservations) {
        tableModelReservations.setRowCount(0);
        for (Reservation reservation : reservations) {
            tableModelReservations.addRow(new Object[]{
                    reservation.getId(),
                    reservation.getCustomerName(),
                    reservation.getHotelName(),
                    reservation.getStartDate(),
                    reservation.getEndDate(),
                    reservation.getTotalPrice()
            });
        }
    }

    // Update reservation method
    private void updateReservation() {
        int selectedRow = tableReservations.getSelectedRow();
        if (selectedRow != -1) {
            int reservationId = (int) tableReservations.getValueAt(selectedRow, 0);
            Reservation reservation = reservationController.getReservation(reservationId);
            new ReservationUptadeView(reservationController, hotelController, reservation, new Runnable() {
                @Override
                public void run() {
                    searchReservations();
                }
            }).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(panelMain, "Lütfen güncellemek istediğiniz rezervasyonu seçin.");
        }
    }

    // Delete reservation method
    private void deleteReservation() {
        int selectedRow = tableReservations.getSelectedRow();
        if (selectedRow != -1) {
            int reservationId = (int) tableReservations.getValueAt(selectedRow, 0);
            reservationController.deleteReservation(reservationId);
            searchReservations();
            JOptionPane.showMessageDialog(panelMain, "Rezervasyon başarıyla silindi!");
        } else {
            JOptionPane.showMessageDialog(panelMain, "Lütfen silmek istediğiniz rezervasyonu seçin.");
        }
    }

    // List rooms method
    private void listRooms() {
        String city = (String) comboBoxSearchCity.getSelectedItem();
        String hotelName = (String) comboBoxSearchHotelName.getSelectedItem();
        int adultCount = Integer.parseInt(textFieldAdultCount.getText());
        int childCount = Integer.parseInt(textFieldChildCount.getText());

        List<Room> rooms = roomController.listAvailableRooms(city, hotelName, adultCount, childCount);
        updateRoomsTable(rooms);
    }

    private void updateRoomsTable(List<Room> rooms) {
        int adultCount = Integer.parseInt(textFieldAdultCount.getText());
        int childCount = Integer.parseInt(textFieldChildCount.getText());
        String checkInDate = comboBoxCheckInYear.getSelectedItem() + "-" + comboBoxCheckInMonth.getSelectedItem() + "-" + comboBoxCheckInDay.getSelectedItem();
        String checkOutDate = comboBoxCheckOutYear.getSelectedItem() + "-" + comboBoxCheckOutMonth.getSelectedItem() + "-" + comboBoxCheckOutDay.getSelectedItem();
        int nights = getNightsDifference(checkInDate, checkOutDate);

        tableModelRooms.setRowCount(0);
        for (Room room : rooms) {
            tableModelRooms.addRow(new Object[]{
                    room.getHotelName(),
                    room.getRoomType(),
                    room.getPensionType(),
                    room.getPricePerNight(),
                    room.getTotalPrice(adultCount, childCount, nights)
            });
        }
    }

    // İki tarih arasındaki gece sayısını hesaplayan metot
    private int getNightsDifference(String checkInDate, String checkOutDate) {
        LocalDate startDate = LocalDate.parse(checkInDate);
        LocalDate endDate = LocalDate.parse(checkOutDate);
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }
}
