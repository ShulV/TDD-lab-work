package com.example.mytddproject.utils;

import com.example.mytddproject.util.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class UtilsTests {

    @Test
    void get_string_format_date_some_day() {
        String actualDate = Utils.getStringFormatDate(new Date(994995999999L));//2001-07-13 09:16:39.999
        String expectedDate = "13/07/2001";
        Assertions.assertEquals(expectedDate, actualDate);
    }

    @Test
    void get_string_format_date_start_of_date() {
        String actualDate = Utils.getStringFormatDate(new Date(0L));
        String expectedDate = "01/01/1970";
        Assertions.assertEquals(expectedDate, actualDate);
    }
}
