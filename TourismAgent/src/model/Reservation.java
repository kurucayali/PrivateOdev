package model;

import java.math.BigDecimal;
import java.util.Date;

public class Reservation {
    private int id;
    private int roomId;
    private Date startDate;
    private Date endDate;
    private int guestCountAdult;
    private int guestCountChild;
    private BigDecimal totalPrice;
    private String contactName;
    private String contactPhone;
    private String contactEmail;
    private String guestId;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getGuestCountAdult() {
        return guestCountAdult;
    }

    public void setGuestCountAdult(int guestCountAdult) {
        this.guestCountAdult = guestCountAdult;
    }

    public int getGuestCountChild() {
        return guestCountChild;
    }

    public void setGuestCountChild(int guestCountChild) {
        this.guestCountChild = guestCountChild;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }
}
