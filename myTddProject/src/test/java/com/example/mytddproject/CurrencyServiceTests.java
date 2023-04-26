package com.example.mytddproject;

import com.example.mytddproject.dto.DayCurrencyDto;
import com.example.mytddproject.services.CurrencyService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
        Assertions.assertThatList(dayCurrencyDtoList);
    }

    @Test
    void get_ruble_currency_for_period() {
        CurrencyService currencyService = new CurrencyService();
        List<DayCurrencyDto> dayCurrencyDtoList = currencyService.getRubleCurrencyForPeriod();
        Assertions.assertThatList(dayCurrencyDtoList);
    }
}
