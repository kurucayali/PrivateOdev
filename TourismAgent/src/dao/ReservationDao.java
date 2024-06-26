package dao;

import model.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// ReservationDao sınıfı, rezervasyon veritabanı işlemlerini gerçekleştirir
public class ReservationDao {
    private Connection connection;

    // ReservationDao constructor
    public ReservationDao(Connection connection) {
        this.connection = connection;
    }

    // Yeni rezervasyon ekleme metodu
    public void addReservation(Reservation reservation) throws SQLException {
        String query = "INSERT INTO reservations (room_id, start_date, end_date, guest_count_adult, guest_count_child, total_price, contact_name, contact_phone, contact_email, guest_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, reservation.getRoomId());
            stmt.setDate(2, new java.sql.Date(reservation.getStartDate().getTime()));
            stmt.setDate(3, new java.sql.Date(reservation.getEndDate().getTime()));
            stmt.setInt(4, reservation.getGuestCountAdult());
            stmt.setInt(5, reservation.getGuestCountChild());
            stmt.setBigDecimal(6, reservation.getTotalPrice());
            stmt.setString(7, reservation.getContactName());
            stmt.setString(8, reservation.getContactPhone());
            stmt.setString(9, reservation.getContactEmail());
            stmt.setString(10, reservation.getGuestId());
            stmt.executeUpdate();
        }
    }

    // Rezervasyon id'sine göre rezervasyonu getirme metodu
    public Reservation getReservationById(int reservationId) throws SQLException {
        String query = "SELECT * FROM reservations WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, reservationId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(rs.getInt("id"));
                reservation.setRoomId(rs.getInt("room_id"));
                reservation.setStartDate(rs.getDate("start_date"));
                reservation.setEndDate(rs.getDate("end_date"));
                reservation.setGuestCountAdult(rs.getInt("guest_count_adult"));
                reservation.setGuestCountChild(rs.getInt("guest_count_child"));
                reservation.setTotalPrice(rs.getBigDecimal("total_price"));
                reservation.setContactName(rs.getString("contact_name"));
                reservation.setContactPhone(rs.getString("contact_phone"));
                reservation.setContactEmail(rs.getString("contact_email"));
                reservation.setGuestId(rs.getString("guest_id"));
                return reservation;
            }
        }
        return null;
    }

    // Rezervasyon güncelleme metodu
    public void updateReservation(Reservation reservation) throws SQLException {
        String query = "UPDATE reservations SET room_id = ?, start_date = ?, end_date = ?, guest_count_adult = ?, guest_count_child = ?, total_price = ?, contact_name = ?, contact_phone = ?, contact_email = ?, guest_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, reservation.getRoomId());
            stmt.setDate(2, new java.sql.Date(reservation.getStartDate().getTime()));
            stmt.setDate(3, new java.sql.Date(reservation.getEndDate().getTime()));
            stmt.setInt(4, reservation.getGuestCountAdult());
            stmt.setInt(5, reservation.getGuestCountChild());
            stmt.setBigDecimal(6, reservation.getTotalPrice());
            stmt.setString(7, reservation.getContactName());
            stmt.setString(8, reservation.getContactPhone());
            stmt.setString(9, reservation.getContactEmail());
            stmt.setString(10, reservation.getGuestId());
            stmt.setInt(11, reservation.getId());
            stmt.executeUpdate();
        }
    }

    // Rezervasyon silme metodu
    public void deleteReservation(int reservationId) throws SQLException {
        String query = "DELETE FROM reservations WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, reservationId);
            stmt.executeUpdate();
        }
    }

    // Belirli bir odanın tüm rezervasyonlarını listeleme metodu
    public List<Reservation> getReservationsByRoomId(int roomId) throws SQLException {
        List<Reservation> reservationList = new ArrayList<>();
        String query = "SELECT * FROM reservations WHERE room_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, roomId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(rs.getInt("id"));
                reservation.setRoomId(rs.getInt("room_id"));
                reservation.setStartDate(rs.getDate("start_date"));
                reservation.setEndDate(rs.getDate("end_date"));
                reservation.setGuestCountAdult(rs.getInt("guest_count_adult"));
                reservation.setGuestCountChild(rs.getInt("guest_count_child"));
                reservation.setTotalPrice(rs.getBigDecimal("total_price"));
                reservation.setContactName(rs.getString("contact_name"));
                reservation.setContactPhone(rs.getString("contact_phone"));
                reservation.setContactEmail(rs.getString("contact_email"));
                reservation.setGuestId(rs.getString("guest_id"));
                reservationList.add(reservation);
            }
        }
        return reservationList;
    }
}
