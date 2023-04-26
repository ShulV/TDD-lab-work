package com.example.mytddproject;

import com.example.mytddproject.services.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CurrencyServiceTests {

    @Test
    void currency_service_class_exists() {
        CurrencyService currencyService = new CurrencyService();
    }
}
