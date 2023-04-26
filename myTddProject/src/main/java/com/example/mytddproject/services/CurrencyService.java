package com.example.mytddproject.services;

import com.example.mytddproject.dto.DayCurrencyDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.mytddproject.util.Utils.getStringFormatDate;

public class CurrencyService {

    //Получить список объектов валюты за последнюю неделю
    public List<DayCurrencyDto> getCurrencyForPeriod(String sourceCurrencyCode) {
        return new ArrayList<>();
    }

    //Получить список объектов валюты-рубля за последнюю неделю
    public List<DayCurrencyDto> getRubleCurrencyForPeriod() {
        List<DayCurrencyDto> dayCurrencies = new ArrayList<>();
        String date = getStringFormatDate(new Date(System.currentTimeMillis()));
        dayCurrencies.add(new DayCurrencyDto(date, "R", 1, "1"));
        return dayCurrencies;
    }
}
