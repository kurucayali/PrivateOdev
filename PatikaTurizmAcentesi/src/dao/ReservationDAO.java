package dao;

import model.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
    private Connection connection;

    public ReservationDAO(Connection connection) {
        this.connection = connection;
    }

    public void addReservation(Reservation reservation) {
        String query = "INSERT INTO reservations (room_id, customer_name, customer_nationality, customer_identity_num, customer_phone, customer_email, guest_name, guest_nationality, guest_identity, child_name, child_nationality, child_identity_num, child2_name, child2_nationality, child2_identity_num, start_date, end_date, total_price, reservation_note, hotel_name, room_type, pension_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reservation.getRoomId());
            statement.setString(2, reservation.getCustomerName());
            statement.setString(3, reservation.getCustomerNationality());
            statement.setString(4, reservation.getCustomerIdentityNum());
            statement.setString(5, reservation.getCustomerPhone());
            statement.setString(6, reservation.getCustomerEmail());
            statement.setString(7, reservation.getGuestName());
            statement.setString(8, reservation.getGuestNationality());
            statement.setString(9, reservation.getGuestIdentity());
            statement.setString(10, reservation.getChildName());
            statement.setString(11, reservation.getChildNationality());
            statement.setString(12, reservation.getChildIdentityNum());
            statement.setString(13, reservation.getChild2Name());
            statement.setString(14, reservation.getChild2Nationality());
            statement.setString(15, reservation.getChild2IdentityNum());
            statement.setDate(16, new java.sql.Date(reservation.getStartDate().getTime()));
            statement.setDate(17, new java.sql.Date(reservation.getEndDate().getTime()));
            statement.setDouble(18, reservation.getTotalPrice());
            statement.setString(19, reservation.getReservationNote());
            statement.setString(20, reservation.getHotelName());
            statement.setString(21, reservation.getRoomType());
            statement.setString(22, reservation.getPensionType());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Reservation getReservation(int id) {
        String query = "SELECT * FROM reservations WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Reservation(
                        resultSet.getInt("id"),
                        resultSet.getInt("room_id"),
                        resultSet.getString("customer_name"),
                        resultSet.getString("customer_nationality"),
                        resultSet.getString("customer_identity_num"),
                        resultSet.getString("customer_phone"),
                        resultSet.getString("customer_email"),
                        resultSet.getString("guest_name"),
                        resultSet.getString("guest_nationality"),
                        resultSet.getString("guest_identity"),
                        resultSet.getString("child_name"),
                        resultSet.getString("child_nationality"),
                        resultSet.getString("child_identity_num"),
                        resultSet.getString("child2_name"),
                        resultSet.getString("child2_nationality"),
                        resultSet.getString("child2_identity_num"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date"),
                        resultSet.getDouble("total_price"),
                        resultSet.getString("reservation_note"),
                        resultSet.getString("hotel_name"),
                        resultSet.getString("room_type"),
                        resultSet.getString("pension_type")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservations";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                reservations.add(new Reservation(
                        resultSet.getInt("id"),
                        resultSet.getInt("room_id"),
                        resultSet.getString("customer_name"),
                        resultSet.getString("customer_nationality"),
                        resultSet.getString("customer_identity_num"),
                        resultSet.getString("customer_phone"),
                        resultSet.getString("customer_email"),
                        resultSet.getString("guest_name"),
                        resultSet.getString("guest_nationality"),
                        resultSet.getString("guest_identity"),
                        resultSet.getString("child_name"),
                        resultSet.getString("child_nationality"),
                        resultSet.getString("child_identity_num"),
                        resultSet.getString("child2_name"),
                        resultSet.getString("child2_nationality"),
                        resultSet.getString("child2_identity_num"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date"),
                        resultSet.getDouble("total_price"),
                        resultSet.getString("reservation_note"),
                        resultSet.getString("hotel_name"),
                        resultSet.getString("room_type"),
                        resultSet.getString("pension_type")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public void updateReservation(Reservation reservation) {
        String query = "UPDATE reservations SET room_id = ?, customer_name = ?, customer_nationality = ?, customer_identity_num = ?, customer_phone = ?, customer_email = ?, guest_name = ?, guest_nationality = ?, guest_identity = ?, child_name = ?, child_nationality = ?, child_identity_num = ?, child2_name = ?, child2_nationality = ?, child2_identity_num = ?, start_date = ?, end_date = ?, total_price = ?, reservation_note = ?, hotel_name = ?, room_type = ?, pension_type = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reservation.getRoomId());
            statement.setString(2, reservation.getCustomerName());
            statement.setString(3, reservation.getCustomerNationality());
            statement.setString(4, reservation.getCustomerIdentityNum());
            statement.setString(5, reservation.getCustomerPhone());
            statement.setString(6, reservation.getCustomerEmail());
            statement.setString(7, reservation.getGuestName());
            statement.setString(8, reservation.getGuestNationality());
            statement.setString(9, reservation.getGuestIdentity());
            statement.setString(10, reservation.getChildName());
            statement.setString(11, reservation.getChildNationality());
            statement.setString(12, reservation.getChildIdentityNum());
            statement.setString(13, reservation.getChild2Name());
            statement.setString(14, reservation.getChild2Nationality());
            statement.setString(15, reservation.getChild2IdentityNum());
            statement.setDate(16, new java.sql.Date(reservation.getStartDate().getTime()));
            statement.setDate(17, new java.sql.Date(reservation.getEndDate().getTime()));
            statement.setDouble(18, reservation.getTotalPrice());
            statement.setString(19, reservation.getReservationNote());
            statement.setString(20, reservation.getHotelName());
            statement.setString(21, reservation.getRoomType());
            statement.setString(22, reservation.getPensionType());
            statement.setInt(23, reservation.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteReservation(int id) {
        String query = "DELETE FROM reservations WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Reservation> searchReservations(String customerName, String guestName, String guestIdentity) {
        List<Reservation> reservations = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM reservations WHERE 1=1");

        if (!customerName.isEmpty()) {
            query.append(" AND customer_name ILIKE ?");
        }
        if (!guestName.isEmpty()) {
            query.append(" AND guest_name ILIKE ?");
        }
        if (!guestIdentity.isEmpty()) {
            query.append(" AND guest_identity ILIKE ?");
        }

        try (PreparedStatement statement = connection.prepareStatement(query.toString())) {
            int paramIndex = 1;
            if (!customerName.isEmpty()) {
                statement.setString(paramIndex++, "%" + customerName + "%");
            }
            if (!guestName.isEmpty()) {
                statement.setString(paramIndex++, "%" + guestName + "%");
            }
            if (!guestIdentity.isEmpty()) {
                statement.setString(paramIndex++, "%" + guestIdentity + "%");
            }

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reservations.add(new Reservation(
                        resultSet.getInt("id"),
                        resultSet.getInt("room_id"),
                        resultSet.getString("customer_name"),
                        resultSet.getString("customer_nationality"),
                        resultSet.getString("customer_identity_num"),
                        resultSet.getString("customer_phone"),
                        resultSet.getString("customer_email"),
                        resultSet.getString("guest_name"),
                        resultSet.getString("guest_nationality"),
                        resultSet.getString("guest_identity"),
                        resultSet.getString("child_name"),
                        resultSet.getString("child_nationality"),
                        resultSet.getString("child_identity_num"),
                        resultSet.getString("child2_name"),
                        resultSet.getString("child2_nationality"),
                        resultSet.getString("child2_identity_num"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date"),
                        resultSet.getDouble("total_price"),
                        resultSet.getString("reservation_note"),
                        resultSet.getString("hotel_name"),
                        resultSet.getString("room_type"),
                        resultSet.getString("pension_type")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }
}
