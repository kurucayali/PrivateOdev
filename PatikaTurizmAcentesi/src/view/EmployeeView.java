package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.*;
import dao.*;
import db.DatabaseConnection;
import service.*;

public class EmployeeView extends JFrame {
    private JPanel panelMain;
    private JLabel labelTitle;
    private JButton buttonHotelManagement;
    private JButton buttonRoomManagement;
    private JButton buttonReservationManagement;
    private JButton buttonSeasonManagement;
    private JButton buttonLogout;

    public EmployeeView() {
        setTitle("Çalışan Paneli");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panelMain);

        // Otel Yönetimi Butonu Olayı
        buttonHotelManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HotelManagementView(new HotelController(new HotelService(new HotelDAO(DatabaseConnection.getConnection())))).setVisible(true);
            }
        });

        // Oda Yönetimi Butonu Olayı
        buttonRoomManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RoomManagementView(
                        new RoomController(new RoomService(new RoomDAO(DatabaseConnection.getConnection()))),
                        new HotelController(new HotelService(new HotelDAO(DatabaseConnection.getConnection())))
                ).setVisible(true);
            }
        });

        // Rezervasyon Yönetimi Butonu Olayı
        buttonReservationManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReservationManagementMainView(
                        new ReservationController(new ReservationService(new ReservationDAO(DatabaseConnection.getConnection()))),
                        new HotelController(new HotelService(new HotelDAO(DatabaseConnection.getConnection()))),
                        new RoomController(new RoomService(new RoomDAO(DatabaseConnection.getConnection())))
                ).setVisible(true);
            }
        });

        // Dönem Yönetimi Butonu Olayı
        buttonSeasonManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SeasonManagementView(
                        new SeasonController(new SeasonService(new SeasonDAO(DatabaseConnection.getConnection()))),
                        new HotelController(new HotelService(new HotelDAO(DatabaseConnection.getConnection())))
                ).setVisible(true);
            }
        });

        // Çıkış Butonu Olayı
        buttonLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginView(new UserController(new UserService(new UserDAO(DatabaseConnection.getConnection())))).setVisible(true);
                dispose();
            }
        });
    }
}
