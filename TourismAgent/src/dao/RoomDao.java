package dao;

import model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// RoomDao sınıfı, oda veritabanı işlemlerini gerçekleştirir
public class RoomDao {
    private Connection connection;

    // RoomDao constructor
    public RoomDao(Connection connection) {
        this.connection = connection;
    }

    // Yeni oda ekleme metodu
    public void addRoom(Room room) throws SQLException {
        String query = "INSERT INTO rooms (hotel_id, room_type, season_id, pension_type, price_per_night_adult, price_per_night_child, stock, bed_count, area_sqm, tv, minibar, game_console, safe, projector) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, room.getHotelId());
            stmt.setString(2, room.getRoomType());
            stmt.setInt(3, room.getSeasonId());
            stmt.setString(4, room.getPensionType());
            stmt.setBigDecimal(5, room.getPricePerNightAdult());
            stmt.setBigDecimal(6, room.getPricePerNightChild());
            stmt.setInt(7, room.getStock());
            stmt.setInt(8, room.getBedCount());
            stmt.setBigDecimal(9, room.getAreaSqm());
            stmt.setBoolean(10, room.isTv());
            stmt.setBoolean(11, room.isMinibar());
            stmt.setBoolean(12, room.isGameConsole());
            stmt.setBoolean(13, room.isSafe());
            stmt.setBoolean(14, room.isProjector());
            stmt.executeUpdate();
        }
    }

    // Oda id'sine göre odayı getirme metodu
    public Room getRoomById(int roomId) throws SQLException {
        String query = "SELECT * FROM rooms WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, roomId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setHotelId(rs.getInt("hotel_id"));
                room.setRoomType(rs.getString("room_type"));
                room.setSeasonId(rs.getInt("season_id"));
                room.setPensionType(rs.getString("pension_type"));
                room.setPricePerNightAdult(rs.getBigDecimal("price_per_night_adult"));
                room.setPricePerNightChild(rs.getBigDecimal("price_per_night_child"));
                room.setStock(rs.getInt("stock"));
                room.setBedCount(rs.getInt("bed_count"));
                room.setAreaSqm(rs.getBigDecimal("area_sqm"));
                room.setTv(rs.getBoolean("tv"));
                room.setMinibar(rs.getBoolean("minibar"));
                room.setGameConsole(rs.getBoolean("game_console"));
                room.setSafe(rs.getBoolean("safe"));
                room.setProjector(rs.getBoolean("projector"));
                return room;
            }
        }
        return null;
    }

    // Oda güncelleme metodu
    public void updateRoom(Room room) throws SQLException {
        String query = "UPDATE rooms SET hotel_id = ?, room_type = ?, season_id = ?, pension_type = ?, price_per_night_adult = ?, price_per_night_child = ?, stock = ?, bed_count = ?, area_sqm = ?, tv = ?, minibar = ?, game_console = ?, safe = ?, projector = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, room.getHotelId());
            stmt.setString(2, room.getRoomType());
            stmt.setInt(3, room.getSeasonId());
            stmt.setString(4, room.getPensionType());
            stmt.setBigDecimal(5, room.getPricePerNightAdult());
            stmt.setBigDecimal(6, room.getPricePerNightChild());
            stmt.setInt(7, room.getStock());
            stmt.setInt(8, room.getBedCount());
            stmt.setBigDecimal(9, room.getAreaSqm());
            stmt.setBoolean(10, room.isTv());
            stmt.setBoolean(11, room.isMinibar());
            stmt.setBoolean(12, room.isGameConsole());
            stmt.setBoolean(13, room.isSafe());
            stmt.setBoolean(14, room.isProjector());
            stmt.setInt(15, room.getId());
            stmt.executeUpdate();
        }
    }

    // Oda silme metodu
    public void deleteRoom(int roomId) throws SQLException {
        String query = "DELETE FROM rooms WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, roomId);
            stmt.executeUpdate();
        }
    }

    // Belirli bir otelin tüm odalarını listeleme metodu
    public List<Room> getRoomsByHotelId(int hotelId) throws SQLException {
        List<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM rooms WHERE hotel_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, hotelId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setHotelId(rs.getInt("hotel_id"));
                room.setRoomType(rs.getString("room_type"));
                room.setSeasonId(rs.getInt("season_id"));
                room.setPensionType(rs.getString("pension_type"));
                room.setPricePerNightAdult(rs.getBigDecimal("price_per_night_adult"));
                room.setPricePerNightChild(rs.getBigDecimal("price_per_night_child"));
                room.setStock(rs.getInt("stock"));
                room.setBedCount(rs.getInt("bed_count"));
                room.setAreaSqm(rs.getBigDecimal("area_sqm"));
                room.setTv(rs.getBoolean("tv"));
                room.setMinibar(rs.getBoolean("minibar"));
                room.setGameConsole(rs.getBoolean("game_console"));
                room.setSafe(rs.getBoolean("safe"));
                room.setProjector(rs.getBoolean("projector"));
                roomList.add(room);
            }
        }
        return roomList;
    }
}
