package manager;

import dao.HotelDao;
import model.Hotel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

// HotelManager sınıfı, iş mantığını içeren servis katmanıdır
public class HotelManager {
    private HotelDao hotelDao;

    // HotelManager constructor
    public HotelManager(Connection connection) {
        this.hotelDao = new HotelDao(connection);
    }

    // Otel ekleme metodu
    public void addHotel(Hotel hotel) throws SQLException {
        hotelDao.addHotel(hotel);
    }

    // Otel id'sine göre otel getirme metodu
    public Hotel getHotelById(int hotelId) throws SQLException {
        return hotelDao.getHotelById(hotelId);
    }

    // Otel güncelleme metodu
    public void updateHotel(Hotel hotel) throws SQLException {
        hotelDao.updateHotel(hotel);
    }

    // Otel silme metodu
    public void deleteHotel(int hotelId) throws SQLException {
        hotelDao.deleteHotel(hotelId);
    }

    // Tüm otelleri listeleme metodu
    public List<Hotel> getAllHotels() throws SQLException {
        return hotelDao.getAllHotels();
    }
}
