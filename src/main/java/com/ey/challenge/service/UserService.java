package com.ey.challenge.service;

import com.ey.challenge.dto.UserDTO;
import com.ey.challenge.dto.UserResDTO;
import com.ey.challenge.entity.Phone;
import com.ey.challenge.entity.User;
import com.ey.challenge.exception.EmailExistsException;
import com.ey.challenge.exception.UserDTONotValidException;
import com.ey.challenge.repository.PhoneRepository;
import com.ey.challenge.repository.UserRepository;
import com.ey.challenge.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    public UserResDTO createUser(UserDTO userDTO) {
        this.userValidation(userDTO);

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setToken(UUID.randomUUID().toString() );


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

        User createdUser = userRepository.save(user);

        return this.userToUserResDTO(createdUser);
    }

    public List<UserResDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResDTO> usersResDTO = users.stream().map( user -> this.userToUserResDTO(user)).toList();
        return usersResDTO;
    }

    private UserResDTO userToUserResDTO(User user) {
        UserResDTO userResDTO = new UserResDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhones(),
                user.getCreated(),
                user.getModified(),
                user.getLastLogin(),
                user.getToken(),
                user.getIsActive()
        );
        return userResDTO;
    }

    private void userValidation(UserDTO userDTO) {
        // validar campos obligatorios
        List<String> errors = new ArrayList<>();
        if( userDTO.getName().isEmpty() ) {
            errors.add("El campo name es obligatorio");
        }
        if( userDTO.getEmail().isEmpty() ) {
            errors.add("El campo email es obligatorio");
        }
        if( userDTO.getPassword().isEmpty() ) {
            errors.add("El campo password es obligatorio");
        }

        // validar formato correo y password
        if( !Validation.validatePassword(userDTO.getPassword()) ) {
            errors.add("La password debe tener al menos, una letra minúscula, una letra mayúscula y 2 números");
        }

        if(!Validation.validateEmail(userDTO.getEmail())) {
            errors.add("El correo debe tener el formato correo@dominio.com");
        }


        if( errors.size()>0 ) {
            String message = String.join("; ", errors);
            throw new UserDTONotValidException(message);
        }


        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new EmailExistsException("El correo ya está registrado");
        }
    }
}
