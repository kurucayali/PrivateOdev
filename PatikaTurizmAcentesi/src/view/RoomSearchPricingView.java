package view;

import controller.RoomController;
import controller.HotelController;
import controller.ReservationController;
import model.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class RoomSearchPricingView extends JFrame {
    private JPanel panelMain;
    private JComboBox<Integer> comboBoxCheckInDay;
    private JComboBox<Integer> comboBoxCheckInMonth;
    private JComboBox<Integer> comboBoxCheckInYear;
    private JComboBox<Integer> comboBoxCheckOutDay;
    private JComboBox<Integer> comboBoxCheckOutMonth;
    private JComboBox<Integer> comboBoxCheckOutYear;
    private JComboBox<String> comboBoxSearchCity;
    private JComboBox<String> comboBoxSearchHotelName;
    private JTable tableRooms;
    private JButton buttonListRooms;
    private JButton buttonMakeReservation;
    private JButton buttonExit;
    private DefaultTableModel tableModelRooms;

    private RoomController roomController;
    private HotelController hotelController;
    private ReservationController reservationController;

    public RoomSearchPricingView(RoomController roomController, HotelController hotelController, ReservationController reservationController) {
        this.roomController = roomController;
        this.hotelController = hotelController;
        this.reservationController = reservationController;

        setTitle("Oda Arama ve Fiyatlandırma");
        setSize(1200, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panelMain);

        initComponents();
        initEventHandlers();
    }

    private void initComponents() {
        loadCities();
        initDateComboBoxes();

        tableModelRooms = new DefaultTableModel(new Object[]{"Şehir", "Otel Adı", "Oda Tipi", "Pansiyon Tipi", "Fiyat", "Toplam Fiyat"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableRooms.setModel(tableModelRooms);
    }

    private void initEventHandlers() {
        comboBoxSearchCity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadHotels();
            }
        });

        buttonListRooms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listRooms();
            }
        });

        buttonMakeReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableRooms.getSelectedRow();
                if (selectedRow != -1) {
                    String city = (String) tableModelRooms.getValueAt(selectedRow, 0);
                    String hotelName = (String) tableModelRooms.getValueAt(selectedRow, 1);
                    String roomType = (String) tableModelRooms.getValueAt(selectedRow, 2);
                    String pensionType = (String) tableModelRooms.getValueAt(selectedRow, 3);
                    String checkInDate = comboBoxCheckInYear.getSelectedItem() + "-" + String.format("%02d", comboBoxCheckInMonth.getSelectedItem()) + "-" + String.format("%02d", comboBoxCheckInDay.getSelectedItem());
                    String checkOutDate = comboBoxCheckOutYear.getSelectedItem() + "-" + String.format("%02d", comboBoxCheckOutMonth.getSelectedItem()) + "-" + String.format("%02d", comboBoxCheckOutDay.getSelectedItem());

                    new MakeReservationView(reservationController, hotelController, roomController, city, hotelName, roomType, pensionType, checkInDate, checkOutDate).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(panelMain, "Lütfen bir oda seçin.");
                }
            }
        });

        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void loadCities() {
        List<String> cities = roomController.getCities();
        comboBoxSearchCity.addItem("Hepsi");
        for (String city : cities) {
            comboBoxSearchCity.addItem(city);
        }
    }

    private void loadHotels() {
        String selectedCity = (String) comboBoxSearchCity.getSelectedItem();
        List<String> hotels = hotelController.getHotelsByCity(selectedCity.equals("Hepsi") ? "" : selectedCity);
        comboBoxSearchHotelName.removeAllItems();
        comboBoxSearchHotelName.addItem("Hepsi");
        for (String hotel : hotels) {
            comboBoxSearchHotelName.addItem(hotel);
        }
    }

    private void initDateComboBoxes() {
        int currentYear = LocalDate.now().getYear();
        for (int i = currentYear; i <= currentYear + 10; i++) {
            comboBoxCheckInYear.addItem(i);
            comboBoxCheckOutYear.addItem(i);
        }
        for (int i = 1; i <= 12; i++) {
            comboBoxCheckInMonth.addItem(i);
            comboBoxCheckOutMonth.addItem(i);
        }
        for (int i = 1; i <= 31; i++) {
            comboBoxCheckInDay.addItem(i);
            comboBoxCheckOutDay.addItem(i);
        }
    }

    private void listRooms() {
        String city = (String) comboBoxSearchCity.getSelectedItem();
        String hotelName = (String) comboBoxSearchHotelName.getSelectedItem();

        List<Room> rooms = roomController.listAvailableRooms(city.equals("Hepsi") ? "" : city, hotelName.equals("Hepsi") ? "" : hotelName);
        updateRoomsTable(rooms);
    }

    private void updateRoomsTable(List<Room> rooms) {
        String checkInDate = comboBoxCheckInYear.getSelectedItem() + "-" + String.format("%02d", comboBoxCheckInMonth.getSelectedItem()) + "-" + String.format("%02d", comboBoxCheckInDay.getSelectedItem());
        String checkOutDate = comboBoxCheckOutYear.getSelectedItem() + "-" + String.format("%02d", comboBoxCheckOutMonth.getSelectedItem()) + "-" + String.format("%02d", comboBoxCheckOutDay.getSelectedItem());
        int nights = getNightsDifference(checkInDate, checkOutDate);

        tableModelRooms.setRowCount(0);
        for (Room room : rooms) {
            double totalPrice = room.getTotalPrice(nights); // Toplam fiyat hesaplama
            tableModelRooms.addRow(new Object[]{
                    hotelController.getCityByRoomId(room.getId()),
                    room.getHotelName(),
                    room.getRoomType(),
                    room.getPensionType(),
                    room.getPricePerNight(),
                    totalPrice
            });
        }
    }

    private int getNightsDifference(String checkInDate, String checkOutDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(checkInDate, formatter);
        LocalDate endDate = LocalDate.parse(checkOutDate, formatter);
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }
}
