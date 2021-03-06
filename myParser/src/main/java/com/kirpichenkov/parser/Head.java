package com.kirpichenkov.parser;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Владислав on 12.06.2017.
 */
@XStreamAlias("HEAD")
public class Head {
    @XStreamImplicit
    private List<Position> positionList;
    @XStreamAlias("DELIVERYPLACE")
    private String deliveryPlace;
    @XStreamAlias("SENDER")
    private String sender;
    @XStreamAlias("RECIPIENT")
    private String recipient;
    @XStreamAlias("EDIINTERCHANGEID")
    private String ediInterchangeID;

    public String getDeliveryPlace(){
        return deliveryPlace;
    }
    public String getSender(){
        return sender;
    }
    public String getRecipient(){
        return recipient;
    }
    public String getEdiInterchangeID(){
        return ediInterchangeID;
    }

    public List<Position> getPositionList(){
        return positionList;
    }

    @Override
    public String toString() {
        return " Head [deliveryPlace=" + deliveryPlace + ", sender=" + sender + ", recipient=" + recipient + ", positionList=" + positionList + "]";
    }

}
@XStreamAlias("POSITION")
class Position {
    @XStreamAlias("POSITIONNUMBER")
    private String positionNumber;
    @XStreamAlias("PRODUCT")
    private String product;
    @XStreamAlias("PRODUCTIDBUYER")
    private String productIdByer;
    @XStreamAlias("ORDEREDQUANTITY")
    private String orderQuantity;
    @XStreamAlias("ORDERUNIT")
    private String orderUnit;
    @XStreamAlias("ORDERPRICE")
    private Double orderPrice;
    @XStreamAlias("PRICEWITHVAT")
    private Double prieWithVat;
    @XStreamAlias("VAT")
    private Double vat;
    @XStreamAlias("CHARACTERISTIC")
    private Characteristic characteristic;

    @Override
    public String toString() {
        return "positionList [positionNumber=" + positionNumber + ", product=" + product +", productIdByer=" + productIdByer +", orderQuantity=" + orderQuantity + ", orderUnit=" + orderUnit +", orderPrice=" + orderPrice +", orderPrice=" + prieWithVat +", vat=" + vat  + characteristic +"]";


    }
}
@XStreamAlias("CHARACTERISTIC")
class Characteristic{
    @XStreamAlias("DESCRIPTION")
    private String description;

    @Override
    public String toString() {
        return ", Characteristic [description=" + description + "]";


    }

}