package com.ved.vedxkart.dto;

public class UpdateStatusDTO {

    private Long OrderId;

    private String status;

    public Long getOrderId() {
        return OrderId;
    }

    public void setOrderId(Long orderId) {
        OrderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
