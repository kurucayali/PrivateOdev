package view;

import controller.ReservationController;
import controller.HotelController;
import controller.RoomController;
import model.Reservation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReservationSearchUpdateDeleteView extends JFrame {
    private JPanel panelMain;
    private JTextField textFieldCustomerName;
    private JTextField textFieldCustomerIdentity;
    private JTextField textFieldHotelName;
    private JButton buttonFilter;
    private JButton buttonLoadAll;
    private JTable tableReservations;
    private JButton buttonUpdate;
    private JButton buttonDelete;
    private JButton buttonExit;
    private JScrollPane scrollPaneReservations;

    private ReservationController reservationController;
    private HotelController hotelController;
    private RoomController roomController;
    private DefaultTableModel tableModelReservations;

    public ReservationSearchUpdateDeleteView(ReservationController reservationController, HotelController hotelController, RoomController roomController) {
        this.reservationController = reservationController;
        this.hotelController = hotelController;
        this.roomController = roomController;

        setTitle("Rezervasyon Arama/Güncelleme/Silme");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panelMain);

        initTableModel();
        initEventHandlers();

        // İlk açılışta rezervasyonları yükle
        loadAllReservations();
    }

    private void initTableModel() {
        tableModelReservations = new DefaultTableModel(new Object[]{"ID", "Müşteri Adı", "Otel Adı", "Başlangıç Tarihi", "Bitiş Tarihi", "Toplam Fiyat"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableReservations.setModel(tableModelReservations);
    }

    private void initEventHandlers() {
        buttonFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterReservations();
            }
        });

        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateReservation();
            }
        });

        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteReservation();
            }
        });

        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonLoadAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAllReservations();
            }
        });
    }

    private void loadAllReservations() {
        List<Reservation> reservations = reservationController.getAllReservations();
        updateReservationsTable(reservations);
    }

    private void filterReservations() {
        String customerName = textFieldCustomerName.getText().trim();
        String customerIdentity = textFieldCustomerIdentity.getText().trim();
        String hotelName = textFieldHotelName.getText().trim();

        List<Reservation> reservations = reservationController.searchReservations(customerName, customerIdentity, hotelName);
        updateReservationsTable(reservations);
    }

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

    private void updateReservation() {
        int selectedRow = tableReservations.getSelectedRow();
        if (selectedRow != -1) {
            int reservationId = (int) tableReservations.getValueAt(selectedRow, 0);
            Reservation reservation = reservationController.getReservation(reservationId);
            new ReservationUptadeView(reservationController, hotelController, roomController, reservation, new Runnable() {
                @Override
                public void run() {
                    filterReservations();
                }
            }).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(panelMain, "Lütfen güncellemek istediğiniz rezervasyonu seçin.");
        }
    }

    private void deleteReservation() {
        int selectedRow = tableReservations.getSelectedRow();
        if (selectedRow != -1) {
            int reservationId = (int) tableReservations.getValueAt(selectedRow, 0);

            // Kullanıcıya emin olup olmadığını soran bir onay penceresi göster
            int response = JOptionPane.showConfirmDialog(panelMain,
                    "Bu rezervasyonu silmek istediğinizden emin misiniz?",
                    "Onay",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                reservationController.deleteReservation(reservationId);
                filterReservations();
                JOptionPane.showMessageDialog(panelMain, "Rezervasyon başarıyla silindi!");
            }
        } else {
            JOptionPane.showMessageDialog(panelMain, "Lütfen silmek istediğiniz rezervasyonu seçin.");
        }
    }
}
