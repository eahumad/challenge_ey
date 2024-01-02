package com.ey.challenge.service;

import com.ey.challenge.dto.UserDTO;
import com.ey.challenge.entity.Phone;
import com.ey.challenge.entity.User;
import com.ey.challenge.repository.PhoneRepository;
import com.ey.challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    public User createUser(UserDTO userDTO) {
        // Mapear UserDTO a User
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        // Mapear PhonesDTO a Phones y asociar con User
        List<Phone> phones = userDTO.getPhones().stream()
                .map(phoneDTO -> {
                    Phone phone = new Phone();
                    phone.setNumber(phoneDTO.getNumber());
                    phone.setCitycode(phoneDTO.getCitycode());
                    phone.setCountrycode(phoneDTO.getCountrycode());
                    phone.setUser(user);
                    return phone;
                })
                .collect(Collectors.toList());

        user.setPhones(phones);

        // Guardar en la base de datos
        return userRepository.save(user);
    }
}
