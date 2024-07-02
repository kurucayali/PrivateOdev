package dao;

import model.Season;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeasonDAO {
    private Connection connection;

    public SeasonDAO(Connection connection) {
        this.connection = connection;
    }

    public void addSeason(Season season) {
        String query = "INSERT INTO seasons (hotel_id, start_date, end_date, name, multiplier) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, season.getHotelId());
            statement.setDate(2, Date.valueOf(season.getStartDate()));
            statement.setDate(3, Date.valueOf(season.getEndDate()));
            statement.setString(4, season.getName());
            statement.setDouble(5, season.getMultiplier());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Season getSeason(int id) {
        String query = "SELECT * FROM seasons WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Season season = new Season();
                season.setId(resultSet.getInt("id"));
                season.setHotelId(resultSet.getInt("hotel_id"));
                season.setStartDate(resultSet.getDate("start_date").toString());
                season.setEndDate(resultSet.getDate("end_date").toString());
                season.setName(resultSet.getString("name"));
                season.setMultiplier(resultSet.getDouble("multiplier"));  // Yeni alan
                return season;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Season> getAllSeasons() {
        List<Season> seasons = new ArrayList<>();
        String query = "SELECT * FROM seasons";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Season season = new Season();
                season.setId(resultSet.getInt("id"));
                season.setHotelId(resultSet.getInt("hotel_id"));
                season.setStartDate(resultSet.getDate("start_date").toString());
                season.setEndDate(resultSet.getDate("end_date").toString());
                season.setName(resultSet.getString("name"));
                season.setMultiplier(resultSet.getDouble("multiplier"));  // Yeni alan
                seasons.add(season);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasons;
    }

    public void updateSeason(Season season) {
        String query = "UPDATE seasons SET hotel_id = ?, start_date = ?, end_date = ?, name = ?, multiplier = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, season.getHotelId());
            statement.setDate(2, Date.valueOf(season.getStartDate()));
            statement.setDate(3, Date.valueOf(season.getEndDate()));
            statement.setString(4, season.getName());
            statement.setDouble(5, season.getMultiplier());
            statement.setInt(6, season.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSeason(int id) {
        String query = "DELETE FROM seasons WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
