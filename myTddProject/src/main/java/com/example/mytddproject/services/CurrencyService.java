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
import static com.example.mytddproject.util.Utils.parseFormatDouble;
import static java.lang.Math.abs;

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

    //Получить значение валюты из списка валют за последний день
    public double getLastCurrencyValue(List<DayCurrencyDto> currencyList) {
        int n = currencyList.size();
        return parseFormatDouble(currencyList.get(n-1).getValue());
    }

    //Конвертировать валюту
    public double convertCurrency(double sourceCurrencyRate, double resultCurrencyRate, double num) {
        if (resultCurrencyRate == 0) {
            throw new ArithmeticException("Divide by zero error");
        }
        if (sourceCurrencyRate < 0 || resultCurrencyRate < 0 || num < 0) {
            throw new IllegalArgumentException("Someone argument is less than zero");
        }
        return (sourceCurrencyRate / resultCurrencyRate) * num;
    }

    //получить среднее изменение курса (в день) за последние дни
    public double getCurrencyChange(List<DayCurrencyDto> dayCurrencyList) {
        int size = dayCurrencyList.size();
        double startValue = parseFormatDouble(dayCurrencyList.get(0).getValue());
        double endValue = parseFormatDouble(dayCurrencyList.get(size - 1).getValue());
        return (endValue - startValue) / size;
    }


    //сгенерировать сообщение об изменении курса за последние дни
    public String genCurrencyChangeMessage(double change) {
        String message;
        if (change > 0) {
            message = String.format("За последние дни курс рос на %.3f в день", change);
        } else if (change == 0) {
            message = "За последние дни курс удерживался на одном значении";
        } else {
            message = String.format("За последние дни курс падал на %.3f в день", abs(change));
        }
        return message;
    }
}
