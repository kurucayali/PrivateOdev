package controller;

import model.Reservation;
import service.ReservationService;

import java.util.List;

public class ReservationController {
    private ReservationService reservationService;

    // Constructor
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // Method to make a reservation
    public void makeReservation(Reservation reservation) {
        reservationService.makeReservation(reservation);
    }

    // Method to get a reservation by ID
    public Reservation getReservation(int id) {
        return reservationService.getReservation(id);
    }

    // Method to get all reservations
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    // Method to update a reservation
    public void updateReservation(Reservation reservation) {
        reservationService.updateReservation(reservation);
    }

    // Method to delete a reservation
    public void deleteReservation(int id) {
        reservationService.deleteReservation(id);
    }

    // Method to search for reservations
    public List<Reservation> searchReservations(String customerName, String guestIdentity, String hotelName) {
        return reservationService.searchReservations(customerName, guestIdentity, hotelName);
    }
}
