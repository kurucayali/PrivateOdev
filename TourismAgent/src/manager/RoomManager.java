package manager;

import dao.RoomDao;
import model.Room;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

// RoomManager sınıfı, iş mantığını içeren servis katmanıdır
public class RoomManager {
    private RoomDao roomDao;

    // RoomManager constructor
    public RoomManager(Connection connection) {
        this.roomDao = new RoomDao(connection);
    }

    // Oda ekleme metodu
    public void addRoom(Room room) throws SQLException {
        roomDao.addRoom(room);
    }

    // Oda id'sine göre oda getirme metodu
    public Room getRoomById(int roomId) throws SQLException {
        return roomDao.getRoomById(roomId);
    }

    // Oda güncelleme metodu
    public void updateRoom(Room room) throws SQLException {
        roomDao.updateRoom(room);
    }

    // Oda silme metodu
    public void deleteRoom(int roomId) throws SQLException {
        roomDao.deleteRoom(roomId);
    }

    // Belirli bir otelin tüm odalarını listeleme metodu
    public List<Room> getRoomsByHotelId(int hotelId) throws SQLException {
        return roomDao.getRoomsByHotelId(hotelId);
    }
}
