package view;

import controller.RoomController;
import model.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
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
    private JTextField textFieldAdultCount;
    private JTextField textFieldChildCount;
    private JTable tableRooms;
    private JButton buttonListRooms;
    private JButton buttonExit;
    private DefaultTableModel tableModelRooms;

    private RoomController roomController;

    public RoomSearchPricingView(RoomController roomController) {
        this.roomController = roomController;

        setTitle("Oda Arama ve Fiyatlandırma");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panelMain);

        initComponents();
        initEventHandlers();
    }

    private void initComponents() {
        comboBoxSearchCity = new JComboBox<>();
        comboBoxSearchHotelName = new JComboBox<>();
        comboBoxCheckInDay = new JComboBox<>();
        comboBoxCheckInMonth = new JComboBox<>();
        comboBoxCheckInYear = new JComboBox<>();
        comboBoxCheckOutDay = new JComboBox<>();
        comboBoxCheckOutMonth = new JComboBox<>();
        comboBoxCheckOutYear = new JComboBox<>();
        textFieldAdultCount = new JTextField();
        textFieldChildCount = new JTextField();
        tableRooms = new JTable();
        buttonListRooms = new JButton("Listele");
        buttonExit = new JButton("Çıkış");

        loadCities();
        initDateComboBoxes();

        tableModelRooms = new DefaultTableModel(new Object[]{"Otel Adı", "Oda Tipi", "Pansiyon Tipi", "Fiyat", "Toplam Fiyat"}, 0) {
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
        List<String> hotels = roomController.getHotelsByCity(selectedCity.equals("Hepsi") ? "" : selectedCity);
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

    private int getNightsDifference(String checkInDate, String checkOutDate) {
        LocalDate startDate = LocalDate.parse(checkInDate);
        LocalDate endDate = LocalDate.parse(checkOutDate);
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }
}
