package model;

import java.util.Date;

public class Reservation {
    private int id;
    private int roomId;
    private String customerName;
    private String customerNationality;
    private String customerIdentityNum;
    private String customerPhone;
    private String customerEmail;
    private String guestName;
    private String guestNationality;
    private String guestIdentity;
    private String childName;
    private String childNationality;
    private String childIdentityNum;
    private String child2Name;
    private String child2Nationality;
    private String child2IdentityNum;
    private Date startDate;
    private Date endDate;
    private double totalPrice;
    private String reservationNote;
    private String hotelName;
    private String roomType;
    private String pensionType;

    public Reservation() {}

    public Reservation(int id, int roomId, String customerName, String customerNationality, String customerIdentityNum, String customerPhone, String customerEmail, String guestName, String guestNationality, String guestIdentity, String childName, String childNationality, String childIdentityNum, String child2Name, String child2Nationality, String child2IdentityNum, Date startDate, Date endDate, double totalPrice, String reservationNote, String hotelName, String roomType, String pensionType) {
        this.id = id;
        this.roomId = roomId;
        this.customerName = customerName;
        this.customerNationality = customerNationality;
        this.customerIdentityNum = customerIdentityNum;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.guestName = guestName;
        this.guestNationality = guestNationality;
        this.guestIdentity = guestIdentity;
        this.childName = childName;
        this.childNationality = childNationality;
        this.childIdentityNum = childIdentityNum;
        this.child2Name = child2Name;
        this.child2Nationality = child2Nationality;
        this.child2IdentityNum = child2IdentityNum;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.reservationNote = reservationNote;
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.pensionType = pensionType; // Yeni alan
    }


    public int getId() {
        return id;
    }

    public String getPensionType() {
        return pensionType;
    }

    public void setPensionType(String pensionType) {
        this.pensionType = pensionType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNationality() {
        return customerNationality;
    }

    public void setCustomerNationality(String customerNationality) {
        this.customerNationality = customerNationality;
    }

    public String getCustomerIdentityNum() {
        return customerIdentityNum;
    }

    public void setCustomerIdentityNum(String customerIdentityNum) {
        this.customerIdentityNum = customerIdentityNum;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestNationality() {
        return guestNationality;
    }

    public void setGuestNationality(String guestNationality) {
        this.guestNationality = guestNationality;
    }

    public String getGuestIdentity() {
        return guestIdentity;
    }

    public void setGuestIdentity(String guestIdentity) {
        this.guestIdentity = guestIdentity;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getChildNationality() {
        return childNationality;
    }

    public void setChildNationality(String childNationality) {
        this.childNationality = childNationality;
    }

    public String getChildIdentityNum() {
        return childIdentityNum;
    }

    public void setChildIdentityNum(String childIdentityNum) {
        this.childIdentityNum = childIdentityNum;
    }

    public String getChild2Name() {
        return child2Name;
    }

    public void setChild2Name(String child2Name) {
        this.child2Name = child2Name;
    }

    public String getChild2Nationality() {
        return child2Nationality;
    }

    public void setChild2Nationality(String child2Nationality) {
        this.child2Nationality = child2Nationality;
    }

    public String getChild2IdentityNum() {
        return child2IdentityNum;
    }

    public void setChild2IdentityNum(String child2IdentityNum) {
        this.child2IdentityNum = child2IdentityNum;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getReservationNote() {
        return reservationNote;
    }

    public void setReservationNote(String reservationNote) {
        this.reservationNote = reservationNote;
    }
}
