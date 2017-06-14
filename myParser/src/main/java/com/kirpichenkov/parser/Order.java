package com.kirpichenkov.parser;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Владислав on 12.06.2017.
 */
@XStreamAlias("ORDER")
public class Order {
    @XStreamAlias("DOCUMENTNAME")
    private String documentName;
    @XStreamAlias("NUMBER")
    private String number;
    @XStreamAlias("DATE")
    private String date;
    @XStreamAlias("DELIVERYDATE")
    private String deliveryDate;
    @XStreamAlias("CAMPAIGNNUMBER")
    private String campainNumber;
    @XStreamAlias("HEAD")
    private Head head;


    public String getDocumentName() {
        return documentName;
    }

    public String getNumber() {
        return number;
    }

    public String getDate() {
        return date;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getCampainNumber() {
        return campainNumber;
    }

    public Head getHead(){
        return head;
    }

    @Override
    public String toString() {
        return "Order [documentName=" + documentName + ", number=" + number + ", date=" + date + ", deliveryDate=" + deliveryDate + ", campainNumber=" + campainNumber + head + "]";

    }
}