package com.example.mytddproject.services;

import com.example.mytddproject.dto.DayCurrencyDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CurrencyService {

    //Получить список объектов валюты за последнюю неделю
    public List<DayCurrencyDto> getCurrencyForPeriod(String sourceCurrencyCode) {
        return new ArrayList<>();
    }

    //Получить список объектов валюты-рубля за последнюю неделю
    public List<DayCurrencyDto> getRubleCurrencyForPeriod() {
        return new ArrayList<>();
    }
}
