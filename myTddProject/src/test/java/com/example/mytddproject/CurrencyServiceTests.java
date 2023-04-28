package com.example.mytddproject;

import com.example.mytddproject.dto.DayCurrencyDto;
import com.example.mytddproject.services.CurrencyService;
import jakarta.xml.bind.ValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CurrencyServiceTests {

    @Test
    void currency_service_class_exists() {
        CurrencyService currencyService = new CurrencyService();
    }

    @Test
    void get_currency_for_period() throws ValidationException {
        CurrencyService currencyService = new CurrencyService();
        List<DayCurrencyDto> dayCurrencyDtoList = currencyService.getCurrencyForPeriod("R");
        assertThatList(dayCurrencyDtoList);
    }

    @Test
    void get_currency_data_returns_list_of_day_currency_dto() throws ValidationException {
        CurrencyService currencyService = new CurrencyService();
        List<DayCurrencyDto> currencyData = currencyService.getCurrencyForPeriod("R01235");
        assertNotNull(currencyData);
        assertNotNull(currencyData.get(0));
    }

    @Test
    void get_currency_data_returns_list_of_ruble_day_currency_dto_for_currency_code_R() throws ValidationException {
        CurrencyService currencyService = new CurrencyService();
        List<DayCurrencyDto> currencyData = currencyService.getCurrencyForPeriod("R");
        assertNotNull(currencyData);
        DayCurrencyDto dayCurrencyDto = currencyData.get(0);
        assertEquals("R", dayCurrencyDto.getId());
        assertEquals("1", dayCurrencyDto.getValue());
        assertEquals(1, dayCurrencyDto.getNominal());
    }

    @Test
    void get_currency_data_sets_correct_start_and_end_dates() throws ValidationException {
        CurrencyService currencyService = new CurrencyService();
        List<DayCurrencyDto> currencyData = currencyService.getCurrencyForPeriod("R01235");
        assertNotNull(currencyData);
        assertTrue(currencyData.size() <= 7);//api может не выдавать значение в праздничные дни и выходные
    }

    @Test
    void get_currency_data_throws_runtime_exception() {
        CurrencyService currencyService = new CurrencyService();
        assertThrows(RuntimeException.class, () -> {currencyService.getCurrencyForPeriod("error_code");});
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
