package service;

import dao.HotelDAO;
import model.Hotel;
import model.Season;
import model.PensionType;

import java.util.List;
import java.util.stream.Collectors;

public class HotelService {
    private HotelDAO hotelDAO;

    public HotelService(HotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }

    public List<Hotel> getAllHotels() {
        return hotelDAO.getAllHotels();
    }

    public Hotel getHotelByName(String name) {
        return hotelDAO.getHotelByName(name);
    }

    public List<String> getCities() {
        return hotelDAO.getCities();
    }

    public List<Hotel> filterHotels(String city, int stars) {
        return hotelDAO.filterHotels(city, stars);
    }

    public List<String> getHotelsByCity(String city) {
        return hotelDAO.getHotelsByCity(city).stream()
                .map(Hotel::getName)
                .collect(Collectors.toList());
    }

    public List<Hotel> getHotelsByStars(int stars) {
        return hotelDAO.getHotelsByStars(stars);
    }

    public List<Hotel> searchHotels(String hotelName, String city) {
        return hotelDAO.searchHotels(hotelName, city);
    }

    public List<Hotel> searchHotelsByName(String hotelName) {
        return hotelDAO.searchHotelsByName(hotelName);
    }

    public Hotel getHotel(int id) {
        return hotelDAO.getHotel(id);
    }

    public void deleteHotel(int id) {
        hotelDAO.deleteHotel(id);
    }

    public void addHotel(Hotel hotel) {
        hotelDAO.addHotel(hotel);
    }

    public void updateHotel(Hotel hotel) {
        hotelDAO.updateHotel(hotel);
    }

    public List<Season> getAllSeasons() {
        return hotelDAO.getAllSeasons();
    }

    public Season getSeason(int id) {
        return hotelDAO.getSeason(id);
    }

    public Season getSeasonByName(String name) {
        return hotelDAO.getSeasonByName(name);
    }

    public PensionType getPensionType(int id) {
        return hotelDAO.getPensionType(id);
    }

    public PensionType getPensionTypeByName(String name) {
        return hotelDAO.getPensionTypeByName(name);
    }

    public String getCityByRoomId(int roomId) {
        return hotelDAO.getCityByRoomId(roomId);
    }
}
