package com.example.mytddproject.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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

