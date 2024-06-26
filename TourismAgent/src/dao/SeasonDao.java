package dao;

import model.Season;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// SeasonDao sınıfı, dönem veritabanı işlemlerini gerçekleştirir
public class SeasonDao {
    private Connection connection;

    // SeasonDao constructor
    public SeasonDao(Connection connection) {
        this.connection = connection;
    }

    // Yeni dönem ekleme metodu
    public void addSeason(Season season) throws SQLException {
        String query = "INSERT INTO seasons (hotel_id, start_date, end_date) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, season.getHotelId());
            stmt.setDate(2, new java.sql.Date(season.getStartDate().getTime()));
            stmt.setDate(3, new java.sql.Date(season.getEndDate().getTime()));
            stmt.executeUpdate();
        }
    }

    // Dönem id'sine göre dönemi getirme metodu
    public Season getSeasonById(int seasonId) throws SQLException {
        String query = "SELECT * FROM seasons WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, seasonId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Season season = new Season();
                season.setId(rs.getInt("id"));
                season.setHotelId(rs.getInt("hotel_id"));
                season.setStartDate(rs.getDate("start_date"));
                season.setEndDate(rs.getDate("end_date"));
                return season;
            }
        }
        return null;
    }

    // Dönem güncelleme metodu
    public void updateSeason(Season season) throws SQLException {
        String query = "UPDATE seasons SET hotel_id = ?, start_date = ?, end_date = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, season.getHotelId());
            stmt.setDate(2, new java.sql.Date(season.getStartDate().getTime()));
            stmt.setDate(3, new java.sql.Date(season.getEndDate().getTime()));
            stmt.setInt(4, season.getId());
            stmt.executeUpdate();
        }
    }

    // Dönem silme metodu
    public void deleteSeason(int seasonId) throws SQLException {
        String query = "DELETE FROM seasons WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, seasonId);
            stmt.executeUpdate();
        }
    }

    // Belirli bir otelin tüm dönemlerini listeleme metodu
    public List<Season> getSeasonsByHotelId(int hotelId) throws SQLException {
        List<Season> seasonList = new ArrayList<>();
        String query = "SELECT * FROM seasons WHERE hotel_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, hotelId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Season season = new Season();
                season.setId(rs.getInt("id"));
                season.setHotelId(rs.getInt("hotel_id"));
                season.setStartDate(rs.getDate("start_date"));
                season.setEndDate(rs.getDate("end_date"));
                seasonList.add(season);
            }
        }
        return seasonList;
    }
}
