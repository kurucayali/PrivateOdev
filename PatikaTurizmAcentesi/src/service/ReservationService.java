package service;

import dao.ReservationDAO;
import model.Reservation;

import java.util.List;

public class ReservationService {
    private ReservationDAO reservationDAO;

    // Constructor
    public ReservationService(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }

    // Method to make a reservation
    public void makeReservation(Reservation reservation) {
        reservationDAO.addReservation(reservation);
    }

    // Method to get a reservation by ID
    public Reservation getReservation(int id) {
        return reservationDAO.getReservation(id);
    }

    // Method to get all reservations
    public List<Reservation> getAllReservations() {
        return reservationDAO.getAllReservations();
    }

    // Method to update a reservation
    public void updateReservation(Reservation reservation) {
        reservationDAO.updateReservation(reservation);
    }

    // Method to delete a reservation
    public void deleteReservation(int id) {
        reservationDAO.deleteReservation(id);
    }

    // Method to search for reservations
    public List<Reservation> searchReservations(String customerName, String guestName, String guestIdentity) {
        return reservationDAO.searchReservations(customerName, guestName, guestIdentity);
    }
}
