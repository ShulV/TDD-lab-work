package com.example.mytddproject.utils;

import com.example.mytddproject.util.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static com.example.mytddproject.util.Utils.parseFormatDouble;

@SpringBootTest
class UtilsTests {

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

    @Test
    void parse_format_double_with_comma() {
        double actualNum = parseFormatDouble("27,77");
        Assertions.assertEquals(27.77d, actualNum);
    }

    @Test
    void parse_format_double_with_dot() {
        double actualNum = parseFormatDouble("27.77");
        Assertions.assertEquals(27.77d, actualNum);
    }

    @Test
    void parse_format_double_zero_without_comma_and_dot() {
        double actualNum = parseFormatDouble("0");
        Assertions.assertEquals(0.0d, actualNum);
    }

    @Test
    void parse_format_double_negative_zero() {
        double actualNum = parseFormatDouble("-0");
        Assertions.assertEquals(-0.0d, actualNum);
    }
    @Test
    void parse_format_double_negative_num() {
        double actualNum = parseFormatDouble("-0.33");
        Assertions.assertEquals(-0.33d, actualNum);
    }

    @Test
    void parse_format_double_invalid_str() {
        Assertions.assertThrows(RuntimeException.class, ()->{
            parseFormatDouble("invalid_str");
        });
    }


    @Test
    void parse_format_double_empty_str() {
        Assertions.assertThrows(NumberFormatException.class, ()->{
            parseFormatDouble("");
        });
    }

    @Test
    void parse_format_double_null_str() {
        Assertions.assertThrows(NullPointerException.class, ()->{
            parseFormatDouble(null);
        });
    }



}
