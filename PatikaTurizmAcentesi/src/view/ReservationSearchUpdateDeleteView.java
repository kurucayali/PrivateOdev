package view;

import controller.ReservationController;
import controller.HotelController;
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
    private JTable tableReservations;
    private JButton buttonUpdate;
    private JButton buttonDelete;
    private JButton buttonExit;
    private JScrollPane scrollPaneReservations;

    private ReservationController reservationController;
    private HotelController hotelController;
    private DefaultTableModel tableModelReservations;

    public ReservationSearchUpdateDeleteView(ReservationController reservationController, HotelController hotelController) {
        this.reservationController = reservationController;
        this.hotelController = hotelController;

        setTitle("Rezervasyon Arama/Güncelleme/Silme");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panelMain);

        initTableModel();
        initEventHandlers();
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
    }

    private void filterReservations() {
        String customerName = textFieldCustomerName.getText();
        String customerIdentity = textFieldCustomerIdentity.getText();
        String hotelName = textFieldHotelName.getText();

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
            new ReservationUptadeView(reservationController, hotelController, reservation, new Runnable() {
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
            reservationController.deleteReservation(reservationId);
            filterReservations();
            JOptionPane.showMessageDialog(panelMain, "Rezervasyon başarıyla silindi!");
        } else {
            JOptionPane.showMessageDialog(panelMain, "Lütfen silmek istediğiniz rezervasyonu seçin.");
        }
    }
}
