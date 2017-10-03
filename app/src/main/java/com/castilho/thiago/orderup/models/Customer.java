package com.castilho.thiago.orderup.models;

/**
 * Created by Thiago on 2017-09-25.
 */

public class Customer {
    private String Name;
    private String Email;
    private String Phone;
    private String Address;
    private String CreditCard;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCreditCard() {
        return CreditCard;
    }

    public void setCreditCard(String creditCard) {
        CreditCard = creditCard;
    }

    public Customer() {
    }

    public Customer(String name, String email, String phone, String address, String creditCard) {
        Name = name;
        Email = email;
        Phone = phone;
        Address = address;
        CreditCard = creditCard;
    }
}
