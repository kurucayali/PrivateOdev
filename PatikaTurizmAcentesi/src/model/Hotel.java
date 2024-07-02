package model;

import java.util.List;

public class Hotel {
    private int id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private int stars;
    private String city;
    private List<String> features;
    private List<String> pensionTypes;

    // Getter ve Setter metodları

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public List<String> getPensionTypes() {
        return pensionTypes;
    }

    public void setPensionTypes(List<String> pensionTypes) {
        this.pensionTypes = pensionTypes;
    }
}
