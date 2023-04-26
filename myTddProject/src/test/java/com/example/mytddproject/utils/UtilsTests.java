package com.example.mytddproject.utils;

import com.example.mytddproject.util.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class UtilsTests {

    @Test
    void get_string_format_date() {
        String actualDate = Utils.getStringFormatDate(new Date(994995999999L));//2001-07-13 09:16:39.999
        String expectedDate = "13/07/2001";
    }
}
