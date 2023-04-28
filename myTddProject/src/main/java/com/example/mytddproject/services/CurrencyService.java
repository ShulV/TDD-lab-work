package com.example.mytddproject.services;

import com.example.mytddproject.dto.DayCurrencyDto;
import com.example.mytddproject.dto.ValCursDto;
import com.example.mytddproject.util.TimeConstants;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.ValidationException;

import java.net.MalformedURLException;
import java.net.URL;
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
    public List<DayCurrencyDto> getCurrencyForPeriod(String currencyCode) throws ValidationException {
        if (currencyCode == "R") {
            return getRubleCurrencyForPeriod();
        }
        Date dateWeekAgo = new Date(System.currentTimeMillis() - TimeConstants.DAY * 6);
        Date curDate = new Date(System.currentTimeMillis());

        String startDate = getStringFormatDate(dateWeekAgo);
        String endDate = getStringFormatDate(curDate);

        try {
            boolean containsCode = currencyCodes.stream()
                    .flatMap(List::stream) // объединяем все списки в один поток
                    .anyMatch(currencyCode::equals); // проверяем, есть ли элемент
            if (!containsCode) {
                throw new ValidationException("Invalid currency code");
            }
            String reqUrl = String.format("%s?date_req1=%s&date_req2=%s&VAL_NM_RQ=%s", url, startDate, endDate, currencyCode);
            JAXBContext jaxbContext = JAXBContext.newInstance(ValCursDto.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            URL url1 = new URL(reqUrl);
            ValCursDto valCursDto = (ValCursDto) jaxbUnmarshaller.unmarshal(url1);
            return valCursDto.getDayCurrencyDtoList();
        } catch (JAXBException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    //Получить список объектов валюты-рубля за последнюю неделю
    public List<DayCurrencyDto> getRubleCurrencyForPeriod() {
        List<DayCurrencyDto> dayCurrencies = new ArrayList<>();
        String date = getStringFormatDate(new Date(System.currentTimeMillis()));
        dayCurrencies.add(new DayCurrencyDto(date, "R", 1, "1"));
        return dayCurrencies;
    }


}
