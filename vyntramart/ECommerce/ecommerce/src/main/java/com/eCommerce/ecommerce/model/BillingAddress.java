package com.eCommerce.ecommerce.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class BillingAddress {

    @Id
    @Column(name = "BillingAddressId")
    @GeneratedValue(generator = "incrementator")
    @GenericGenerator(name = "incrementator", strategy = "increment")
    private Long billingAddressId;

    @Column(name = "Address")
    private String address;

    @Column(name = "ContactNumber")
    private String contactNumber;

    @Column(name = "PinCode")
    private String pinCode;

    @Column(name = "State")
    private String state;

    @Column(name = "Country")
    private String country;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "billingAddress")
    private List<Orders> ordersList;

    @ManyToMany()
    private List<User> userList;

    public BillingAddress(){
    }

    public BillingAddress(String address, String contactNumber, String pinCode, String state, String country) {
        this.address = address;
        this.contactNumber = contactNumber;
        this.pinCode = pinCode;
        this.state = state;
        this.country = country;
    }

    public Long getBillingAddressId() {
        return billingAddressId;
    }

    public void setBillingAddressId(Long billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
