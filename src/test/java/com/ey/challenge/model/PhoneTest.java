package com.ey.challenge.model;

import com.ey.challenge.entity.Phone;
import org.junit.jupiter.api.Test;

public class PhoneTest {

    @Test
    void createPhoneModelInstanceTest() {
        Phone phone = new Phone();
        phone.setNumber("21312434");
        phone.setCountrycode("434234");
        phone.setCitycode("233243");
    }
}
