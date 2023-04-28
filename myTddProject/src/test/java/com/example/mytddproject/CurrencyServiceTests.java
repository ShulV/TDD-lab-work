package com.example.mytddproject;

import com.example.mytddproject.dto.DayCurrencyDto;
import com.example.mytddproject.services.CurrencyService;
import jakarta.xml.bind.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
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

    @Test
    void get_last_currency_value_from_empty_list() {
        CurrencyService currencyService = new CurrencyService();
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->{
            currencyService.getLastCurrencyValue(Collections.EMPTY_LIST);
        });
    }

    @Test
    void get_last_currency_value_singleton_list() {
        CurrencyService currencyService = new CurrencyService();
        double actualValue = currencyService.getLastCurrencyValue(Collections.singletonList(
                new DayCurrencyDto("22.02.2022", "1", 100, "27,44")
        ));
        Assertions.assertEquals(27.44d, actualValue);
    }

    @Test
    void get_last_currency_value_many_elements_list() {
        CurrencyService currencyService = new CurrencyService();
        List<DayCurrencyDto> dayCurrencyDtoList = new ArrayList<>();
        dayCurrencyDtoList.add(new DayCurrencyDto("22.02.2022", "1", 100, "27,44"));
        dayCurrencyDtoList.add(new DayCurrencyDto("23.02.2022", "1", 100, "28,44"));
        dayCurrencyDtoList.add(new DayCurrencyDto("24.02.2022", "1", 100, "26,24"));
        dayCurrencyDtoList.add(new DayCurrencyDto("25.02.2022", "1", 100, "23,04"));
        double actualValue = currencyService.getLastCurrencyValue(dayCurrencyDtoList);
        Assertions.assertEquals(23.04d, actualValue);
    }

    @Test
    void convert_currency_1() {
        CurrencyService currencyService = new CurrencyService();
        double actualValue = currencyService.convertCurrency(80.1d, 1.0d, 40000);
        Assertions.assertEquals(3204000.0d, actualValue);
    }

    @Test
    void convert_currency_2() {
        CurrencyService currencyService = new CurrencyService();
        double actualValue = currencyService.convertCurrency(1.0d, 83.0d, 40000);
        Assertions.assertEquals(481.9277108433735d, actualValue);
    }

    @Test
    void convert_currency_zero_num() {
        CurrencyService currencyService = new CurrencyService();
        double actualValue = currencyService.convertCurrency(1.0d, 83.0d, 0);
        Assertions.assertEquals(0.0d, actualValue);
    }

    @Test
    void convert_currency_zero_rate() {
        CurrencyService currencyService = new CurrencyService();
        Assertions.assertThrows(ArithmeticException.class, ()->{
            currencyService.convertCurrency(1.0d, 0.0d, 1344);
        });
    }

    @Test
    void convert_currency_negative_src_rate() {
        CurrencyService currencyService = new CurrencyService();
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            currencyService.convertCurrency(-1.0d, 1.0d, 1344);
        });
    }

    @Test
    void convert_currency_negative_res_rate() {
        CurrencyService currencyService = new CurrencyService();
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            currencyService.convertCurrency(41.0d, -1.0d, 1344);
        });
    }

    @Test
    void convert_currency_negative_num() {
        CurrencyService currencyService = new CurrencyService();
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            currencyService.convertCurrency(41.0d, 1.0d, -1344);
        });
    }

    @Test
    void get_currency_change_growth() {
        CurrencyService currencyService = new CurrencyService();
        List<DayCurrencyDto> dayCurrencyDtoList = new ArrayList<>();
        dayCurrencyDtoList.add(new DayCurrencyDto("22.02.2022", "1", 100, "27,44"));
        dayCurrencyDtoList.add(new DayCurrencyDto("23.02.2022", "1", 100, "28,44"));
        dayCurrencyDtoList.add(new DayCurrencyDto("24.02.2022", "1", 100, "26,24"));
        dayCurrencyDtoList.add(new DayCurrencyDto("25.02.2022", "1", 100, "28,04"));
        double actualChange = currencyService.getCurrencyChange(dayCurrencyDtoList);
        Assertions.assertEquals(0.14999999999999947d, actualChange);
    }

    @Test
    void get_currency_change_depreciation() {
        CurrencyService currencyService = new CurrencyService();
        List<DayCurrencyDto> dayCurrencyDtoList = new ArrayList<>();
        dayCurrencyDtoList.add(new DayCurrencyDto("22.02.2022", "1", 100, "27,44"));
        dayCurrencyDtoList.add(new DayCurrencyDto("23.02.2022", "1", 100, "28,44"));
        dayCurrencyDtoList.add(new DayCurrencyDto("24.02.2022", "1", 100, "26,24"));
        dayCurrencyDtoList.add(new DayCurrencyDto("25.02.2022", "1", 100, "23,04"));
        double actualChange = currencyService.getCurrencyChange(dayCurrencyDtoList);
        Assertions.assertEquals(-1.1000000000000005d, actualChange);
    }

    @Test
    void get_currency_change_no_change_on_average() {
        CurrencyService currencyService = new CurrencyService();
        List<DayCurrencyDto> dayCurrencyDtoList = new ArrayList<>();
        dayCurrencyDtoList.add(new DayCurrencyDto("22.02.2022", "1", 100, "27,44"));
        dayCurrencyDtoList.add(new DayCurrencyDto("23.02.2022", "1", 100, "28,44"));
        dayCurrencyDtoList.add(new DayCurrencyDto("24.02.2022", "1", 100, "26,44"));
        dayCurrencyDtoList.add(new DayCurrencyDto("25.02.2022", "1", 100, "27,44"));
        double actualChange = currencyService.getCurrencyChange(dayCurrencyDtoList);
        Assertions.assertEquals(0d, actualChange);
    }
}
