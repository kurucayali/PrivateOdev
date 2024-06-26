package dao;

import model.Hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// HotelDao sınıfı, otel veritabanı işlemlerini gerçekleştirir
public class HotelDao {
    private Connection connection;

    // HotelDao constructor
    public HotelDao(Connection connection) {
        this.connection = connection;
    }

    // Yeni otel ekleme metodu
    public void addHotel(Hotel hotel) throws SQLException {
        String query = "INSERT INTO hotels (name, address, email, phone, stars, facilities, pension_types) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, hotel.getName());
            stmt.setString(2, hotel.getAddress());
            stmt.setString(3, hotel.getEmail());
            stmt.setString(4, hotel.getPhone());
            stmt.setInt(5, hotel.getStars());
            stmt.setArray(6, connection.createArrayOf("TEXT", hotel.getFacilities().toArray()));
            stmt.setArray(7, connection.createArrayOf("TEXT", hotel.getPensionTypes().toArray()));
            stmt.executeUpdate();
        }
    }

    // Otel id'sine göre oteli getirme metodu
    public Hotel getHotelById(int hotelId) throws SQLException {
        String query = "SELECT * FROM hotels WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, hotelId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(rs.getInt("id"));
                hotel.setName(rs.getString("name"));
                hotel.setAddress(rs.getString("address"));
                hotel.setEmail(rs.getString("email"));
                hotel.setPhone(rs.getString("phone"));
                hotel.setStars(rs.getInt("stars"));
                hotel.setFacilities(List.of((String[]) rs.getArray("facilities").getArray()));
                hotel.setPensionTypes(List.of((String[]) rs.getArray("pension_types").getArray()));
                return hotel;
            }
        }
        return null;
    }

    // Otel güncelleme metodu
    public void updateHotel(Hotel hotel) throws SQLException {
        String query = "UPDATE hotels SET name = ?, address = ?, email = ?, phone = ?, stars = ?, facilities = ?, pension_types = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, hotel.getName());
            stmt.setString(2, hotel.getAddress());
            stmt.setString(3, hotel.getEmail());
            stmt.setString(4, hotel.getPhone());
            stmt.setInt(5, hotel.getStars());
            stmt.setArray(6, connection.createArrayOf("TEXT", hotel.getFacilities().toArray()));
            stmt.setArray(7, connection.createArrayOf("TEXT", hotel.getPensionTypes().toArray()));
            stmt.setInt(8, hotel.getId());
            stmt.executeUpdate();
        }
    }

    // Otel silme metodu
    public void deleteHotel(int hotelId) throws SQLException {
        String query = "DELETE FROM hotels WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, hotelId);
            stmt.executeUpdate();
        }
    }

    // Tüm otelleri listeleme metodu
    public List<Hotel> getAllHotels() throws SQLException {
        List<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM hotels";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(rs.getInt("id"));
                hotel.setName(rs.getString("name"));
                hotel.setAddress(rs.getString("address"));
                hotel.setEmail(rs.getString("email"));
                hotel.setPhone(rs.getString("phone"));
                hotel.setStars(rs.getInt("stars"));
                hotel.setFacilities(List.of((String[]) rs.getArray("facilities").getArray()));
                hotel.setPensionTypes(List.of((String[]) rs.getArray("pension_types").getArray()));
                hotelList.add(hotel);
            }
        }
        return hotelList;
    }
}
