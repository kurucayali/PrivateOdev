package controller;

import model.Room;
import service.RoomService;

import java.util.List;

public class RoomController {
    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    public void addRoom(Room room) {
        roomService.addRoom(room);
    }

    public Room getRoom(int id) {
        return roomService.getRoom(id);
    }

    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    public void updateRoom(Room room) {
        roomService.updateRoom(room);
    }

    public void deleteRoom(int id) {
        roomService.deleteRoom(id);
    }

    public List<Room> getRoomsByHotelId(int hotelId) {
        return roomService.getRoomsByHotelId(hotelId);
    }

    public List<Room> filterRooms(String hotelName, String city, String roomType) {
        return roomService.filterRooms(hotelName, city, roomType);
    }

    public List<Room> listAvailableRooms(String city, String hotelName, int adultCount, int childCount) {
        return roomService.listAvailableRooms(city, hotelName, adultCount, childCount);
    }

    public List<String> getCities() {
        return roomService.getCities();
    }

    public List<String> getHotelsByCity(String city) {
        return roomService.getHotelsByCity(city);
    }
    public double getPricePerNight(String hotelName, String roomType, String pensionType) {
        return roomService.getPricePerNight(hotelName, roomType, pensionType);
    }

}
