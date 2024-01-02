package com.ey.challenge.model;

import com.ey.challenge.entity.User;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void createUserModelInstanceTest() {
        User user = new User();
        user.setName("Juan Rodriguez");
        user.setEmail("juan.rodriuez");
        user.setPassword("asd");
    }
}
