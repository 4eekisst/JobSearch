package com.example.jobsearch.services.impl;

import com.example.jobsearch.dao.UserDao;
import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.models.User;
import com.example.jobsearch.exceptions.UserNotFoundException;
import com.example.jobsearch.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<UserDto> getUsers() {
        List<User> users = userDao.getAllUsers();
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(e -> dtos.add(getUserDto(e)));
        return dtos;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        try {
            User user = userDao.getUserByEmail(email).orElseThrow(UserNotFoundException::new);
            return getUserDto(user);
        } catch (UserNotFoundException e) {
            log.error("user with email: {} does not exists", email);
        }
        return null;
    }


    @Override
    public UserDto getUserById(Long id) {
        try {
            User user = userDao.getUserById(id).orElseThrow(UserNotFoundException::new);
            return getUserDto(user);
        } catch (UserNotFoundException e) {
            log.error("User with {} not found", id);
        }
        return null;
    }

    public void createUser(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userDao.createUser(user);
    }

    @Override
    public List<UserDto> getUserByName(String name) {
        List<User> users = userDao.getUserByName(name);
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(e -> dtos.add(getUserDto(e)));
        return dtos;
    }

    @Override
    public UserDto getUserByPhone(String phone) {
        try {
            User user = userDao.getUserByPhone(phone).orElseThrow(UserNotFoundException::new);
            return getUserDto(user);

        } catch (UserNotFoundException e) {
            log.error("cannot find user with phone number: {}", phone);
        }
        return null;
    }

    @Override
    public Long getUserId(String email) {
        try {
            User user1 = userDao.getUserByEmail(email).orElseThrow(UserNotFoundException::new);
            return user1.getId();
        } catch (UserNotFoundException e) {
            log.error("user with email: {} does not exists", email);
        }
        return null;
    }

    @Override
    public Boolean checkIfUserExists(String email) {
        return userDao.getUserByEmail(email).isPresent();
    }


    private UserDto getUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .enabled(user.isEnabled())
                .roleId(user.getRoleId())
                .build();
    }

    @Override
    public void editUser(UserDto userDto) throws UserNotFoundException {
        User user = userDao.getUserById(userDto.getId())
                .orElseThrow(() -> new UserNotFoundException("User with id " + userDto.getId() + " not found"));

        if (userDto.getName() != null) {
            user.setName(userDto.getName());
        }
        if (userDto.getSurname() != null) {
            user.setSurname(userDto.getSurname());
        }
        if (userDto.getAge() != null) {
            user.setAge(userDto.getAge());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getPassword() != null) {
            user.setPassword(userDto.getPassword());
        }
        if (userDto.getPhoneNumber() != null) {
            user.setPhoneNumber(userDto.getPhoneNumber());
        }
        if (userDto.getAvatar() != null) {
            user.setAvatar(userDto.getAvatar());
        }
        if (userDto.getEnabled() != null) {
            user.setEnabled(userDto.getEnabled());
        }
        if (userDto.getRoleId() != null) {
            user.setRoleId(userDto.getRoleId());
        }

        userDao.editUser(user);
    }

    @Override
    public List<UserDto> getApplicantsByAccountType(String accountType) {
        List<User> applicants = userDao.getUsersByAccountType(accountType);
        List<UserDto> applicantDtos = new ArrayList<>();
        applicants.forEach(applicant -> applicantDtos.add(mapUserToDto(applicant)));
        return applicantDtos;
    }

    @Override
    public List<UserDto> getEmployersByAccountType(String accountType) {
        List<User> employers = userDao.getEmployersByAccountType(accountType);
        List<UserDto> employerDtos = new ArrayList<>();
        employers.forEach(employer -> employerDtos.add(mapUserToDto(employer)));
        return employerDtos;
    }

    @Override
    public List<UserDto> getAllUsersByVacancyId(Long id) {
        List<User> users = userDao.getAllUsersByVacancyId(id);
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(user -> dtos.add(mapUserToDto(user)));
        return dtos;
    }


    private UserDto mapUserToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .enabled(user.isEnabled())
                .roleId(user.getRoleId())
                .build();
    }
}
