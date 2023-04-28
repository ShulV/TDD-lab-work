package com.example.mytddproject.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "ValCurs")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValCursDto {
    @XmlElement(name = "Record")
    private List<DayCurrencyDto> dayCurrencyDtoList;

    public List<DayCurrencyDto> getDayCurrencyDtoList() {
        return dayCurrencyDtoList;
    }
}

