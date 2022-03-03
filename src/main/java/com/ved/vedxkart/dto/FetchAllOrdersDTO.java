package com.ved.vedxkart.dto;

import java.util.Date;

public class FetchAllOrdersDTO {

    private Long orderId;
    private String customer;
    private String country;
    private String address;
    private String productTitle;
    private String productDescription;
    private Date date;
    private String status;

    public FetchAllOrdersDTO(Long orderId, String customer, String country,
                             String address, String productTitle, String productDescription,
                             Date date, String status) {
        this.orderId = orderId;
        this.customer = customer;
        this.country = country;
        this.address = address;
        this.productTitle = productTitle;
        this.productDescription = productDescription;
        this.date = date;
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
