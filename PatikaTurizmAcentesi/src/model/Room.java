package model;

import java.util.Map;

public class Room {
    private int id;
    private int hotelId;
    private int seasonId;
    private int pensionTypeId;
    private String roomType;
    private double pricePerNightAdult;
    private double pricePerNightChild;
    private int stock;
    private Map<String, Object> features;
    private String hotelName;
    private String pensionType;

    // Constructors
    public Room() {}

    public Room(int id, int hotelId, int seasonId, int pensionTypeId, String roomType, double pricePerNightAdult, double pricePerNightChild, int stock, Map<String, Object> features) {
        this.id = id;
        this.hotelId = hotelId;
        this.seasonId = seasonId;
        this.pensionTypeId = pensionTypeId;
        this.roomType = roomType;
        this.pricePerNightAdult = pricePerNightAdult;
        this.pricePerNightChild = pricePerNightChild;
        this.stock = stock;
        this.features = features;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getPensionTypeId() {
        return pensionTypeId;
    }

    public void setPensionTypeId(int pensionTypeId) {
        this.pensionTypeId = pensionTypeId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getPricePerNightAdult() {
        return pricePerNightAdult;
    }

    public void setPricePerNightAdult(double pricePerNightAdult) {
        this.pricePerNightAdult = pricePerNightAdult;
    }

    public double getPricePerNightChild() {
        return pricePerNightChild;
    }

    public void setPricePerNightChild(double pricePerNightChild) {
        this.pricePerNightChild = pricePerNightChild;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Map<String, Object> getFeatures() {
        return features;
    }

    public void setFeatures(Map<String, Object> features) {
        this.features = features;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getPensionType() {
        return pensionType;
    }

    public void setPensionType(String pensionType) {
        this.pensionType = pensionType;
    }

    public double getPricePerNight() {
        return pricePerNightAdult; // Bu metot ihtiyaca göre özelleştirilebilir
    }

    public double getTotalPrice(int nights) {
        // Varsayılan olarak tek bir gece fiyatı ve gece sayısını kullanarak hesaplama yapar
        return getPricePerNight() * nights;
    }
}
