package dao;

import model.Hotel;
import model.Season;
import model.PensionType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {
    private Connection connection;

    public HotelDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Hotel> getAllHotels() {
        List<Hotel> hotels = new ArrayList<>();
        String query = "SELECT id, name, address, email, phone, star_rating, city FROM hotels";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(resultSet.getInt("id"));
                hotel.setName(resultSet.getString("name"));
                hotel.setAddress(resultSet.getString("address"));
                hotel.setEmail(resultSet.getString("email"));
                hotel.setPhone(resultSet.getString("phone"));
                hotel.setStars(resultSet.getInt("star_rating"));
                hotel.setCity(resultSet.getString("city"));

                // Tesis özelliklerini al
                List<String> features = getHotelFeatures(hotel.getId());
                hotel.setFeatures(features);

                // Pansiyon tiplerini al
                List<String> pensionTypes = getHotelPensionTypes(hotel.getId());
                hotel.setPensionTypes(pensionTypes);

                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotels;
    }

    private List<String> getHotelFeatures(int hotelId) {
        List<String> features = new ArrayList<>();
        String query = "SELECT feature FROM hotel_features WHERE hotel_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, hotelId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                features.add(resultSet.getString("feature"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return features;
    }

    private List<String> getHotelPensionTypes(int hotelId) {
        List<String> pensionTypes = new ArrayList<>();
        String query = "SELECT type FROM pension_types WHERE hotel_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, hotelId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                pensionTypes.add(resultSet.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pensionTypes;
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

    public List<Hotel> filterHotels(String city, int stars) {
        List<Hotel> hotels = new ArrayList<>();
        String query = "SELECT id, name, address, email, phone, star_rating, city FROM hotels WHERE city = ? AND star_rating = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, city);
            preparedStatement.setInt(2, stars);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Hotel hotel = new Hotel();
                    hotel.setId(resultSet.getInt("id"));
                    hotel.setName(resultSet.getString("name"));
                    hotel.setAddress(resultSet.getString("address"));
                    hotel.setEmail(resultSet.getString("email"));
                    hotel.setPhone(resultSet.getString("phone"));
                    hotel.setStars(resultSet.getInt("star_rating"));
                    hotel.setCity(resultSet.getString("city"));

                    // Tesis özelliklerini al
                    List<String> features = getHotelFeatures(hotel.getId());
                    hotel.setFeatures(features);

                    // Pansiyon tiplerini al
                    List<String> pensionTypes = getHotelPensionTypes(hotel.getId());
                    hotel.setPensionTypes(pensionTypes);

                    hotels.add(hotel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotels;
    }

    public List<Hotel> getHotelsByCity(String city) {
        List<Hotel> hotels = new ArrayList<>();
        String query = "SELECT id, name, address, email, phone, star_rating, city FROM hotels WHERE city = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, city);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Hotel hotel = new Hotel();
                    hotel.setId(resultSet.getInt("id"));
                    hotel.setName(resultSet.getString("name"));
                    hotel.setAddress(resultSet.getString("address"));
                    hotel.setEmail(resultSet.getString("email"));
                    hotel.setPhone(resultSet.getString("phone"));
                    hotel.setStars(resultSet.getInt("star_rating"));
                    hotel.setCity(resultSet.getString("city"));

                    // Tesis özelliklerini al
                    List<String> features = getHotelFeatures(hotel.getId());
                    hotel.setFeatures(features);

                    // Pansiyon tiplerini al
                    List<String> pensionTypes = getHotelPensionTypes(hotel.getId());
                    hotel.setPensionTypes(pensionTypes);

                    hotels.add(hotel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotels;
    }

    public List<Hotel> getHotelsByStars(int stars) {
        List<Hotel> hotels = new ArrayList<>();
        String query = "SELECT id, name, address, email, phone, star_rating, city FROM hotels WHERE star_rating = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, stars);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Hotel hotel = new Hotel();
                    hotel.setId(resultSet.getInt("id"));
                    hotel.setName(resultSet.getString("name"));
                    hotel.setAddress(resultSet.getString("address"));
                    hotel.setEmail(resultSet.getString("email"));
                    hotel.setPhone(resultSet.getString("phone"));
                    hotel.setStars(resultSet.getInt("star_rating"));
                    hotel.setCity(resultSet.getString("city"));

                    // Tesis özelliklerini al
                    List<String> features = getHotelFeatures(hotel.getId());
                    hotel.setFeatures(features);

                    // Pansiyon tiplerini al
                    List<String> pensionTypes = getHotelPensionTypes(hotel.getId());
                    hotel.setPensionTypes(pensionTypes);

                    hotels.add(hotel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotels;
    }

    public List<Hotel> searchHotels(String hotelName, String city) {
        List<Hotel> hotels = new ArrayList<>();
        String query = "SELECT id, name, address, email, phone, star_rating, city FROM hotels WHERE name ILIKE ? AND city = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + hotelName + "%");
            preparedStatement.setString(2, city);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Hotel hotel = new Hotel();
                    hotel.setId(resultSet.getInt("id"));
                    hotel.setName(resultSet.getString("name"));
                    hotel.setAddress(resultSet.getString("address"));
                    hotel.setEmail(resultSet.getString("email"));
                    hotel.setPhone(resultSet.getString("phone"));
                    hotel.setStars(resultSet.getInt("star_rating"));
                    hotel.setCity(resultSet.getString("city"));

                    // Tesis özelliklerini al
                    List<String> features = getHotelFeatures(hotel.getId());
                    hotel.setFeatures(features);

                    // Pansiyon tiplerini al
                    List<String> pensionTypes = getHotelPensionTypes(hotel.getId());
                    hotel.setPensionTypes(pensionTypes);

                    hotels.add(hotel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotels;
    }

    public List<Hotel> searchHotelsByName(String hotelName) {
        List<Hotel> hotels = new ArrayList<>();
        String query = "SELECT id, name, address, email, phone, star_rating, city FROM hotels WHERE name ILIKE ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + hotelName + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Hotel hotel = new Hotel();
                    hotel.setId(resultSet.getInt("id"));
                    hotel.setName(resultSet.getString("name"));
                    hotel.setAddress(resultSet.getString("address"));
                    hotel.setEmail(resultSet.getString("email"));
                    hotel.setPhone(resultSet.getString("phone"));
                    hotel.setStars(resultSet.getInt("star_rating"));
                    hotel.setCity(resultSet.getString("city"));

                    // Tesis özelliklerini al
                    List<String> features = getHotelFeatures(hotel.getId());
                    hotel.setFeatures(features);

                    // Pansiyon tiplerini al
                    List<String> pensionTypes = getHotelPensionTypes(hotel.getId());
                    hotel.setPensionTypes(pensionTypes);

                    hotels.add(hotel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotels;
    }

    public Hotel getHotel(int id) {
        Hotel hotel = null;
        String query = "SELECT id, name, address, email, phone, star_rating, city FROM hotels WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    hotel = new Hotel();
                    hotel.setId(resultSet.getInt("id"));
                    hotel.setName(resultSet.getString("name"));
                    hotel.setAddress(resultSet.getString("address"));
                    hotel.setEmail(resultSet.getString("email"));
                    hotel.setPhone(resultSet.getString("phone"));
                    hotel.setStars(resultSet.getInt("star_rating"));
                    hotel.setCity(resultSet.getString("city"));

                    // Tesis özelliklerini al
                    List<String> features = getHotelFeatures(hotel.getId());
                    hotel.setFeatures(features);

                    // Pansiyon tiplerini al
                    List<String> pensionTypes = getHotelPensionTypes(hotel.getId());
                    hotel.setPensionTypes(pensionTypes);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotel;
    }
    public Hotel getHotelByName(String name) {
        Hotel hotel = null;
        String query = "SELECT * FROM hotels WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    hotel = new Hotel();
                    hotel.setId(resultSet.getInt("id"));
                    hotel.setName(resultSet.getString("name"));
                    hotel.setAddress(resultSet.getString("address"));
                    hotel.setEmail(resultSet.getString("email"));
                    hotel.setPhone(resultSet.getString("phone"));
                    hotel.setStars(resultSet.getInt("star_rating"));
                    hotel.setCity(resultSet.getString("city"));

                    // Tesis özelliklerini al
                    List<String> features = getHotelFeatures(hotel.getId());
                    hotel.setFeatures(features);

                    // Pansiyon tiplerini al
                    List<String> pensionTypes = getHotelPensionTypes(hotel.getId());
                    hotel.setPensionTypes(pensionTypes);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotel;
    }


    public void deleteHotel(int id) {
        // İlk olarak otelin ilişkili özelliklerini ve pansiyon tiplerini silin
        deleteHotelFeatures(id);
        deleteHotelPensionTypes(id);

        // Sonra oteli silin
        String query = "DELETE FROM hotels WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addHotel(Hotel hotel) {
        String query = "INSERT INTO hotels (name, address, email, phone, star_rating, city) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, hotel.getName());
            preparedStatement.setString(2, hotel.getAddress());
            preparedStatement.setString(3, hotel.getEmail());
            preparedStatement.setString(4, hotel.getPhone());
            preparedStatement.setInt(5, hotel.getStars());
            preparedStatement.setString(6, hotel.getCity());

            preparedStatement.executeUpdate();

            // Yeni eklenen otelin ID'sini alın
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int hotelId = generatedKeys.getInt(1);

                // Tesis özelliklerini ekleyin
                addHotelFeatures(hotelId, hotel.getFeatures());

                // Pansiyon tiplerini ekleyin
                addHotelPensionTypes(hotelId, hotel.getPensionTypes());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateHotel(Hotel hotel) {
        String query = "UPDATE hotels SET name = ?, address = ?, email = ?, phone = ?, star_rating = ?, city = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, hotel.getName());
            preparedStatement.setString(2, hotel.getAddress());
            preparedStatement.setString(3, hotel.getEmail());
            preparedStatement.setString(4, hotel.getPhone());
            preparedStatement.setInt(5, hotel.getStars());
            preparedStatement.setString(6, hotel.getCity());
            preparedStatement.setInt(7, hotel.getId());

            preparedStatement.executeUpdate();

            // Tesis özelliklerini güncelle
            updateHotelFeatures(hotel.getId(), hotel.getFeatures());

            // Pansiyon tiplerini güncelle
            updateHotelPensionTypes(hotel.getId(), hotel.getPensionTypes());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addHotelFeatures(int hotelId, List<String> features) {
        String query = "INSERT INTO hotel_features (hotel_id, feature) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (String feature : features) {
                preparedStatement.setInt(1, hotelId);
                preparedStatement.setString(2, feature);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateHotelFeatures(int hotelId, List<String> features) {
        // Mevcut tesis özelliklerini sil
        deleteHotelFeatures(hotelId);

        // Yeni tesis özelliklerini ekle
        addHotelFeatures(hotelId, features);
    }

    private void deleteHotelFeatures(int hotelId) {
        String query = "DELETE FROM hotel_features WHERE hotel_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, hotelId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addHotelPensionTypes(int hotelId, List<String> pensionTypes) {
        String query = "INSERT INTO pension_types (hotel_id, type) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (String pensionType : pensionTypes) {
                preparedStatement.setInt(1, hotelId);
                preparedStatement.setString(2, pensionType);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateHotelPensionTypes(int hotelId, List<String> pensionTypes) {
        // Mevcut pansiyon tiplerini sil
        deleteHotelPensionTypes(hotelId);

        // Yeni pansiyon tiplerini ekle
        addHotelPensionTypes(hotelId, pensionTypes);
    }

    private void deleteHotelPensionTypes(int hotelId) {
        String query = "DELETE FROM pension_types WHERE hotel_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, hotelId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                season.setStartDate(resultSet.getString("start_date"));
                season.setEndDate(resultSet.getString("end_date"));
                season.setName(resultSet.getString("name"));
                season.setMultiplier(resultSet.getDouble("multiplier"));
                seasons.add(season);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasons;
    }

    public Season getSeason(int id) {
        Season season = null;
        String query = "SELECT * FROM seasons WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    season = new Season();
                    season.setId(resultSet.getInt("id"));
                    season.setHotelId(resultSet.getInt("hotel_id"));
                    season.setStartDate(resultSet.getString("start_date"));
                    season.setEndDate(resultSet.getString("end_date"));
                    season.setName(resultSet.getString("name"));
                    season.setMultiplier(resultSet.getDouble("multiplier"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return season;
    }

    public Season getSeasonByName(String name) {
        Season season = null;
        String query = "SELECT * FROM seasons WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    season = new Season();
                    season.setId(resultSet.getInt("id"));
                    season.setHotelId(resultSet.getInt("hotel_id"));
                    season.setStartDate(resultSet.getString("start_date"));
                    season.setEndDate(resultSet.getString("end_date"));
                    season.setName(resultSet.getString("name"));
                    season.setMultiplier(resultSet.getDouble("multiplier"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return season;
    }
    public PensionType getPensionType(int id) {
        PensionType pensionType = null;
        String query = "SELECT * FROM pension_types WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    pensionType = new PensionType();
                    pensionType.setId(resultSet.getInt("id"));
                    pensionType.setHotelId(resultSet.getInt("hotel_id"));
                    pensionType.setType(resultSet.getString("type"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pensionType;
    }

    public PensionType getPensionTypeByName(String name) {
        PensionType pensionType = null;
        String query = "SELECT * FROM pension_types WHERE type = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    pensionType = new PensionType();
                    pensionType.setId(resultSet.getInt("id"));
                    pensionType.setHotelId(resultSet.getInt("hotel_id"));
                    pensionType.setType(resultSet.getString("type"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pensionType;
    }
    public String getCityByRoomId(int roomId) {
        String query = "SELECT city FROM hotels h JOIN rooms r ON h.id = r.hotel_id WHERE r.id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, roomId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("city");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
