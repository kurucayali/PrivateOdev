package dao;

import model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            preparedStatement.setString(8, convertMapToJson(room.getFeatures()));

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
                room.setFeatures(convertJsonToMap(resultSet.getString("features")));
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
                room.setFeatures(convertJsonToMap(resultSet.getString("features")));

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
            preparedStatement.setString(8, convertMapToJson(room.getFeatures()));
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
                room.setFeatures(convertJsonToMap(resultSet.getString("features")));

                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    public List<Room> filterRooms(String hotelName, String city, String roomType) {
        List<Room> rooms = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT r.* FROM rooms r JOIN hotels h ON r.hotel_id = h.id WHERE 1=1");

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
                room.setFeatures(convertJsonToMap(resultSet.getString("features")));

                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    public List<Room> listAvailableRooms(String city, String hotelName, int adultCount, int childCount) {
        // Implement availability checking and filtering logic
        return new ArrayList<>();
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

    private String convertMapToJson(Map<String, Object> map) {
        StringBuilder json = new StringBuilder("{");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (json.length() > 1) {
                json.append(",");
            }
            json.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\"");
        }
        json.append("}");
        return json.toString();
    }

    private Map<String, Object> convertJsonToMap(String json) {
        Map<String, Object> map = new HashMap<>();
        json = json.replace("{", "").replace("}", "");
        String[] pairs = json.split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            if (keyValue.length == 2) {
                String key = keyValue[0].replace("\"", "").trim();
                String value = keyValue[1].replace("\"", "").trim();
                if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
                    map.put(key, Boolean.parseBoolean(value));
                } else {
                    map.put(key, value);
                }
            }
        }
        return map;
    }
}
