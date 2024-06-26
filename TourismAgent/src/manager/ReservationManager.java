package manager;

import dao.ReservationDao;
import model.Reservation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

// ReservationManager sınıfı, iş mantığını içeren servis katmanıdır
public class ReservationManager {
    private ReservationDao reservationDao;

    // ReservationManager constructor
    public ReservationManager(Connection connection) {
        this.reservationDao = new ReservationDao(connection);
    }

    // Rezervasyon ekleme metodu
    public void addReservation(Reservation reservation) throws SQLException {
        reservationDao.addReservation(reservation);
    }

    // Rezervasyon id'sine göre rezervasyonu getirme metodu
    public Reservation getReservationById(int reservationId) throws SQLException {
        return reservationDao.getReservationById(reservationId);
    }

    // Rezervasyon güncelleme metodu
    public void updateReservation(Reservation reservation) throws SQLException {
        reservationDao.updateReservation(reservation);
    }

    // Rezervasyon silme metodu
    public void deleteReservation(int reservationId) throws SQLException {
        reservationDao.deleteReservation(reservationId);
    }

    // Belirli bir odanın tüm rezervasyonlarını listeleme metodu
    public List<Reservation> getReservationsByRoomId(int roomId) throws SQLException {
        return reservationDao.getReservationsByRoomId(roomId);
    }
}
