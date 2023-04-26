package com.example.mytddproject.services;

import com.example.mytddproject.dto.DayCurrencyDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.example.mytddproject.util.Utils.getStringFormatDate;

public class CurrencyService {

    public List<List<String>> currencyCodes = new ArrayList<>();
    {
        currencyCodes.add(Arrays.asList("Доллар США", "R01235"));
        currencyCodes.add(Arrays.asList("Евро", "R01239"));
        currencyCodes.add(Arrays.asList("Фунт стерлингов", "R01035"));
        currencyCodes.add(Arrays.asList("Российский рубль", "R"));
    }
    private static final String url = "http://www.cbr.ru/scripts/XML_dynamic.asp";

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
