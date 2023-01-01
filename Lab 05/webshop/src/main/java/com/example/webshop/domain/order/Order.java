package com.example.webshop.domain.order;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
public class Order {
    @Id
    private String orderNumber;
    private Date date;
    private String status;

    private List<OrderLine> lstOrderLine = new ArrayList<>();

    public Order() {
    }

    public Order(String orderNumber, Date date, String status) {
        this.orderNumber = orderNumber;
        this.date = date;
        this.status = status;
    }

    public void addOrderLine(OrderLine orderLine) {
        if (orderLine == null)
            return;
        lstOrderLine.add(orderLine);
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    public List<OrderLine> getLstOrderLine() {
        return lstOrderLine;
    }

    public void setLstOrderLine(List<OrderLine> lstOrderLine) {
        this.lstOrderLine = lstOrderLine;
    }
}
