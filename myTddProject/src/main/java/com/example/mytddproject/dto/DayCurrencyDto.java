package com.example.mytddproject.dto;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Record")
@XmlAccessorType(XmlAccessType.FIELD)
public class DayCurrencyDto {
    @XmlAttribute(name = "Date")
    private String date;

    @XmlAttribute(name = "Id")
    private String id;
    @XmlElement(name = "Nominal")
    private int nominal;
    @XmlElement(name = "Value")
    private String value;

    public DayCurrencyDto() {
    }

    public DayCurrencyDto(String date, String id, int nominal, String value) {
        this.date = date;
        this.id = id;
        this.nominal = nominal;
        this.value = value;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
