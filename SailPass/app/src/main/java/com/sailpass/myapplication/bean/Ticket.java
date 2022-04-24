package com.sailpass.myapplication.bean;

public class Ticket {
    private String Destination;
    private String DepartureDate;
    private String ArrivalDate;
    private String AdultPrice;
    private String ChildPrice;

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        this.Destination = destination;
    }

    public String getDepartureDate() {
        return DepartureDate;
    }

    public void setDepartureDate(String departureDate) {
        DepartureDate = departureDate;
    }

    public String getArrivalDate() {
        return ArrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        ArrivalDate = arrivalDate;
    }

    public String getAdultPrice() {
        return AdultPrice;
    }

    public void setAdultPrice(String adultPrice) {
        AdultPrice = adultPrice;
    }

    public String getChildPrice() {
        return ChildPrice;
    }

    public void setChildPrice(String childPrice) {
        ChildPrice = childPrice;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "Destination='" + Destination + '\'' +
                ", DepartureDate='" + DepartureDate + '\'' +
                ", ArrivalDate='" + ArrivalDate + '\'' +
                ", AdultPrice='" + AdultPrice + '\'' +
                ", ChildPrice='" + ChildPrice + '\'' +
                '}';
    }
}

