package com.eCommerce.ecommerce.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Orders {
    @Id
    @Column(name = "OrderId")
    @GeneratedValue(generator = "incrementator")
    @GenericGenerator(name = "incrementator", strategy = "increment")
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billing_address_id", nullable = false)
    private BillingAddress billingAddress;

    @Column(name = "OrderedTime")
    private String orderedTime;

    @Column(name = "DeliveryTime")
    private String deliveryTime;

    @Column(name = "OrderCost")
    private Long orderCost;

    public Orders(){
    }


    public Orders(User user, BillingAddress billingAddress, String orderedTime, String deliveryTime, Long orderCost) {
        this.user = user;
        this.billingAddress = billingAddress;
        this.orderedTime = orderedTime;
        this.deliveryTime = deliveryTime;
        this.orderCost = orderCost;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(String orderedTime) {
        this.orderedTime = orderedTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Long getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(Long orderCost) {
        this.orderCost = orderCost;
    }
}
