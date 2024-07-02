package view;

import controller.HotelController;
import controller.ReservationController;
import controller.RoomController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservationManagementMainView extends JFrame {
    private JPanel panelMain;
    private JButton buttonMakeReservation;
    private JButton buttonSearchUpdateDelete;
    private JButton buttonRoomSearchPricing;
    private JButton buttonExit;

    private ReservationController reservationController;
    private HotelController hotelController;
    private RoomController roomController;

    public ReservationManagementMainView(ReservationController reservationController, HotelController hotelController, RoomController roomController) {
        this.reservationController = reservationController;
        this.hotelController = hotelController;
        this.roomController = roomController;

        setTitle("Rezervasyon Yönetim Ekranı");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panelMain);

        initEventHandlers();
    }

    private void initEventHandlers() {
        buttonMakeReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MakeReservationView(reservationController, hotelController, roomController).setVisible(true);
            }
        });

        buttonSearchUpdateDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReservationSearchUpdateDeleteView(reservationController, hotelController).setVisible(true);
            }
        });

        buttonRoomSearchPricing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RoomSearchPricingView(roomController).setVisible(true);
            }
        });

        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
