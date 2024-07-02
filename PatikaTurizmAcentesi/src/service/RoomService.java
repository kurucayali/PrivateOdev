package service;

import dao.RoomDAO;
import model.Room;

import java.util.List;

public class RoomService {
    private RoomDAO roomDAO;

    public RoomService(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    public void addRoom(Room room) {
        roomDAO.addRoom(room);
    }

    public Room getRoom(int id) {
        return roomDAO.getRoom(id);
    }

    public List<Room> getAllRooms() {
        return roomDAO.getAllRooms();
    }

    public void updateRoom(Room room) {
        roomDAO.updateRoom(room);
    }

    public void deleteRoom(int id) {
        roomDAO.deleteRoom(id);
    }

    public List<Room> getRoomsByHotelId(int hotelId) {
        return roomDAO.getRoomsByHotelId(hotelId);
    }

    public List<Room> filterRooms(String hotelName, String city, String roomType) {
        return roomDAO.filterRooms(hotelName, city, roomType);
    }

    public List<Room> listAvailableRooms(String city, String hotelName, int adultCount, int childCount) {
        return roomDAO.listAvailableRooms(city, hotelName, adultCount, childCount);
    }

    public List<String> getCities() {
        return roomDAO.getCities();
    }

    public List<String> getHotelsByCity(String city) {
        return roomDAO.getHotelsByCity(city);
    }

    public double getPricePerNight(String hotelName, String roomType, String pensionType) {
        return roomDAO.getPricePerNight(hotelName, roomType, pensionType);
    }

}
