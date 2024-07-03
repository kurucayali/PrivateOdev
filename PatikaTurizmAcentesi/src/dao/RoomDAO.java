package dao;

import model.Room;
import service.Helper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private Connection connection;

    public RoomDAO(Connection connection) {
        this.connection = connection;
    }

    public void addRoom(Room room) {
        String query = "INSERT INTO rooms (hotel_id, season_id, pension_type_id, room_type, price_per_night_adult, price_per_night_child, stock, features) VALUES (?, ?, ?, ?, ?, ?, ?, ?::jsonb)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, room.getHotelId());
            preparedStatement.setInt(2, room.getSeasonId());
            preparedStatement.setInt(3, room.getPensionTypeId());
            preparedStatement.setString(4, room.getRoomType());
            preparedStatement.setDouble(5, room.getPricePerNightAdult());
            preparedStatement.setDouble(6, room.getPricePerNightChild());
            preparedStatement.setInt(7, room.getStock());
            preparedStatement.setString(8, Helper.convertMapToJson(room.getFeatures()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Room getRoom(int id) {
        Room room = null;
        String query = "SELECT * FROM rooms WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setHotelId(resultSet.getInt("hotel_id"));
                room.setSeasonId(resultSet.getInt("season_id"));
                room.setPensionTypeId(resultSet.getInt("pension_type_id"));
                room.setRoomType(resultSet.getString("room_type"));
                room.setPricePerNightAdult(resultSet.getDouble("price_per_night_adult"));
                room.setPricePerNightChild(resultSet.getDouble("price_per_night_child"));
                room.setStock(resultSet.getInt("stock"));
                room.setFeatures(Helper.convertJsonToMap(resultSet.getString("features")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return room;
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setHotelId(resultSet.getInt("hotel_id"));
                room.setSeasonId(resultSet.getInt("season_id"));
                room.setPensionTypeId(resultSet.getInt("pension_type_id"));
                room.setRoomType(resultSet.getString("room_type"));
                room.setPricePerNightAdult(resultSet.getDouble("price_per_night_adult"));
                room.setPricePerNightChild(resultSet.getDouble("price_per_night_child"));
                room.setStock(resultSet.getInt("stock"));
                room.setFeatures(Helper.convertJsonToMap(resultSet.getString("features")));

                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    public void updateRoom(Room room) {
        String query = "UPDATE rooms SET hotel_id = ?, season_id = ?, pension_type_id = ?, room_type = ?, price_per_night_adult = ?, price_per_night_child = ?, stock = ?, features = ?::jsonb WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, room.getHotelId());
            preparedStatement.setInt(2, room.getSeasonId());
            preparedStatement.setInt(3, room.getPensionTypeId());
            preparedStatement.setString(4, room.getRoomType());
            preparedStatement.setDouble(5, room.getPricePerNightAdult());
            preparedStatement.setDouble(6, room.getPricePerNightChild());
            preparedStatement.setInt(7, room.getStock());
            preparedStatement.setString(8, Helper.convertMapToJson(room.getFeatures()));
            preparedStatement.setInt(9, room.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRoom(int id) {
        String query = "DELETE FROM rooms WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Room> getRoomsByHotelId(int hotelId) {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms WHERE hotel_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, hotelId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setHotelId(resultSet.getInt("hotel_id"));
                room.setSeasonId(resultSet.getInt("season_id"));
                room.setPensionTypeId(resultSet.getInt("pension_type_id"));
                room.setRoomType(resultSet.getString("room_type"));
                room.setPricePerNightAdult(resultSet.getDouble("price_per_night_adult"));
                room.setPricePerNightChild(resultSet.getDouble("price_per_night_child"));
                room.setStock(resultSet.getInt("stock"));
                room.setFeatures(Helper.convertJsonToMap(resultSet.getString("features")));

                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    public List<Room> filterRooms(String hotelName, String city, String roomType) {
        List<Room> rooms = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT r.*, h.name AS hotel_name, p.type AS pension_type FROM rooms r JOIN hotels h ON r.hotel_id = h.id JOIN pension_types p ON r.pension_type_id = p.id WHERE 1=1");

        if (!hotelName.isEmpty()) {
            query.append(" AND h.name = ?");
        }
        if (!city.isEmpty()) {
            query.append(" AND h.city = ?");
        }
        if (!roomType.isEmpty()) {
            query.append(" AND r.room_type = ?");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int parameterIndex = 1;
            if (!hotelName.isEmpty()) {
                preparedStatement.setString(parameterIndex++, hotelName);
            }
            if (!city.isEmpty()) {
                preparedStatement.setString(parameterIndex++, city);
            }
            if (!roomType.isEmpty()) {
                preparedStatement.setString(parameterIndex++, roomType);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setHotelId(resultSet.getInt("hotel_id"));
                room.setSeasonId(resultSet.getInt("season_id"));
                room.setPensionTypeId(resultSet.getInt("pension_type_id"));
                room.setRoomType(resultSet.getString("room_type"));
                room.setPricePerNightAdult(resultSet.getDouble("price_per_night_adult"));
                room.setPricePerNightChild(resultSet.getDouble("price_per_night_child"));
                room.setStock(resultSet.getInt("stock"));
                room.setHotelName(resultSet.getString("hotel_name"));
                room.setPensionType(resultSet.getString("pension_type"));
                room.setFeatures(Helper.convertJsonToMap(resultSet.getString("features")));

                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    public List<Room> listAvailableRooms(String city, String hotelName) {
        List<Room> rooms = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT r.*, h.name AS hotel_name, p.type AS pension_type FROM rooms r JOIN hotels h ON r.hotel_id = h.id JOIN pension_types p ON r.pension_type_id = p.id WHERE 1=1");

        if (!city.equals("Hepsi") && !city.isEmpty()) {
            query.append(" AND h.city = ?");
        }
        if (!hotelName.equals("Hepsi") && !hotelName.isEmpty()) {
            query.append(" AND h.name = ?");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int parameterIndex = 1;
            if (!city.equals("Hepsi") && !city.isEmpty()) {
                preparedStatement.setString(parameterIndex++, city);
            }
            if (!hotelName.equals("Hepsi") && !hotelName.isEmpty()) {
                preparedStatement.setString(parameterIndex++, hotelName);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setHotelId(resultSet.getInt("hotel_id"));
                room.setSeasonId(resultSet.getInt("season_id"));
                room.setPensionTypeId(resultSet.getInt("pension_type_id"));
                room.setRoomType(resultSet.getString("room_type"));
                room.setPricePerNightAdult(resultSet.getDouble("price_per_night_adult"));
                room.setPricePerNightChild(resultSet.getDouble("price_per_night_child"));
                room.setStock(resultSet.getInt("stock"));
                room.setHotelName(resultSet.getString("hotel_name"));
                room.setPensionType(resultSet.getString("pension_type"));
                room.setFeatures(Helper.convertJsonToMap(resultSet.getString("features")));

                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    public List<String> getCities() {
        List<String> cities = new ArrayList<>();
        String query = "SELECT DISTINCT city FROM hotels";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                cities.add(resultSet.getString("city"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }

    public List<String> getHotelsByCity(String city) {
        List<String> hotels = new ArrayList<>();
        String query = "SELECT name FROM hotels WHERE city = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, city);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                hotels.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotels;
    }

    public List<String> getRoomTypesByHotel(String hotelName) {
        List<String> roomTypes = new ArrayList<>();
        String query = "SELECT DISTINCT room_type FROM rooms r JOIN hotels h ON r.hotel_id = h.id WHERE h.name = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, hotelName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                roomTypes.add(resultSet.getString("room_type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roomTypes;
    }

    public List<String> getPensionTypesByHotel(String hotelName) {
        List<String> pensionTypes = new ArrayList<>();
        String query = "SELECT DISTINCT p.type FROM rooms r JOIN hotels h ON r.hotel_id = h.id JOIN pension_types p ON r.pension_type_id = p.id WHERE h.name = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, hotelName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                pensionTypes.add(resultSet.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pensionTypes;
    }

    public int getRoomIdByDetails(String hotelName, String roomType, String pensionType) {
        String query = "SELECT r.id FROM rooms r " +
                "JOIN hotels h ON r.hotel_id = h.id " +
                "JOIN pension_types p ON r.pension_type_id = p.id " +
                "WHERE h.name = ? AND r.room_type = ? AND p.type = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, hotelName);
            preparedStatement.setString(2, roomType);
            preparedStatement.setString(3, pensionType);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getPricePerNight(String hotelName, String roomType, String pensionType) {
        String query = "SELECT price_per_night_adult FROM rooms r " +
                "JOIN hotels h ON r.hotel_id = h.id " +
                "JOIN pension_types p ON r.pension_type_id = p.id " +
                "WHERE h.name = ? AND r.room_type = ? AND p.type = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, hotelName);
            preparedStatement.setString(2, roomType);
            preparedStatement.setString(3, pensionType);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("price_per_night_adult");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
