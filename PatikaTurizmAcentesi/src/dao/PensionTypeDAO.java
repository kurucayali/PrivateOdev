package dao;

import model.PensionType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PensionTypeDAO {
    private Connection connection;

    public PensionTypeDAO(Connection connection) {
        this.connection = connection;
    }

    public void addPensionType(PensionType pensionType) {
        String query = "INSERT INTO pension_types (hotelId, type) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, pensionType.getHotelId());
            statement.setString(2, pensionType.getType());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PensionType getPensionType(int id) {
        String query = "SELECT * FROM pension_types WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new PensionType(
                        resultSet.getInt("id"),
                        resultSet.getInt("hotelId"),
                        resultSet.getString("type")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PensionType> getAllPensionTypes() {
        List<PensionType> pensionTypes = new ArrayList<>();
        String query = "SELECT * FROM pension_types";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                pensionTypes.add(new PensionType(
                        resultSet.getInt("id"),
                        resultSet.getInt("hotelId"),
                        resultSet.getString("type")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pensionTypes;
    }

    public void updatePensionType(PensionType pensionType) {
        String query = "UPDATE pension_types SET hotelId = ?, type = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, pensionType.getHotelId());
            statement.setString(2, pensionType.getType());
            statement.setInt(3, pensionType.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePensionType(int id) {
        String query = "DELETE FROM pension_types WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
