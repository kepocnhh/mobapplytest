package com.mobapply.test.models;

public class Order
{
    String uuid;
    String number;
    String good;

    long actualWeight;
    long appointmentFrom;

    String note1;

    long initialPrice;

    String CANCELLED;

    public Address departureAddress;
    public Address destinationAddress;

    public class Address
    {
        public String country;
        public String zipCode;
        public String city;
        public String countryCode;
        public String street;
        public String houseNumber;
    }
}
