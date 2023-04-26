package com.example.mytddproject;

import com.example.mytddproject.dto.DayCurrencyDto;
import com.example.mytddproject.services.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CurrencyServiceTests {

    @Test
    void currency_service_class_exists() {
        CurrencyService currencyService = new CurrencyService();
    }

    @Test
    void get_currency_for_period() {
        CurrencyService currencyService = new CurrencyService();
        List<DayCurrencyDto> dayCurrencyDtoList = currencyService.getCurrencyForPeriod("R");
        assertThatList(dayCurrencyDtoList);
    }

    @Test
    void get_ruble_currency_for_period() {
        CurrencyService currencyService = new CurrencyService();
        List<DayCurrencyDto> dayCurrencyDtoList = currencyService.getRubleCurrencyForPeriod();
        assertThatList(dayCurrencyDtoList);
    }

    @Test
    void get_ruble_currency_for_period_right_fields() {
        CurrencyService currencyService = new CurrencyService();
        List<DayCurrencyDto> dayCurrencyDtoList = currencyService.getRubleCurrencyForPeriod();
        assertEquals(1, dayCurrencyDtoList.size());
        DayCurrencyDto dayCurrency = dayCurrencyDtoList.get(0);
        assertEquals(dayCurrency.getValue(), "1");
        assertEquals(dayCurrency.getNominal(), 1);
        assertEquals(dayCurrency.getId(), "R");
    }
}
