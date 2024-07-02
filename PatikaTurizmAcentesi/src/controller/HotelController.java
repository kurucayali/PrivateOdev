package controller;

import model.Hotel;
import model.Season;
import model.PensionType;
import service.HotelService;

import java.util.List;
import java.util.stream.Collectors;

public class HotelController {
    private HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    public Hotel getHotelByName(String name) {
        return hotelService.getHotelByName(name);
    }

    public List<String> getCities() {
        return hotelService.getCities();
    }

    public List<Hotel> filterHotels(String city, int stars) {
        if (city.equals("") && stars == -1) {
            return hotelService.getAllHotels();
        } else if (city.equals("")) {
            return hotelService.getHotelsByStars(stars);
        } else if (stars == -1) {
            return hotelService.getHotelsByCity(city).stream().map(hotel -> {
                Hotel h = new Hotel();
                h.setName(hotel);
                return h;
            }).collect(Collectors.toList());
        } else {
            return hotelService.filterHotels(city, stars);
        }
    }

    public List<Hotel> searchHotels(String hotelName, String city) {
        if (city.equals("")) {
            return hotelService.searchHotelsByName(hotelName);
        }
        return hotelService.searchHotels(hotelName, city);
    }

    public Hotel getHotel(int id) {
        return hotelService.getHotel(id);
    }

    public void deleteHotel(int id) {
        hotelService.deleteHotel(id);
    }

    public void addHotel(Hotel hotel) {
        hotelService.addHotel(hotel);
    }

    public void updateHotel(Hotel hotel) {
        hotelService.updateHotel(hotel);
    }

    public List<Season> getAllSeasons() {
        return hotelService.getAllSeasons();
    }

    public Season getSeason(int id) {
        return hotelService.getSeason(id);
    }

    public Season getSeasonByName(String name) {
        return hotelService.getSeasonByName(name);
    }

    public PensionType getPensionType(int id) {
        return hotelService.getPensionType(id);
    }

    public PensionType getPensionTypeByName(String name) {
        return hotelService.getPensionTypeByName(name);
    }

    public String getCityByRoomId(int roomId) {
        return hotelService.getCityByRoomId(roomId);
    }

    public List<String> getHotelsByCity(String city) {
        return hotelService.getHotelsByCity(city);
    }
}
