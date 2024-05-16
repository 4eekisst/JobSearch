package com.example.jobsearch.services.impl;

import com.example.jobsearch.dto.users.EditUserDto;
import com.example.jobsearch.dto.users.UserCreationDto;
import com.example.jobsearch.dto.users.UserDto;
import com.example.jobsearch.exceptions.AlreadyExistsException;
import com.example.jobsearch.exceptions.UserNotFoundException;
import com.example.jobsearch.models.UserModel;
import com.example.jobsearch.repositories.AuthorityRepository;
import com.example.jobsearch.repositories.UserModelRepository;
import com.example.jobsearch.repositories.UserRepository;
import com.example.jobsearch.services.EmailService;
import com.example.jobsearch.util.FileUtil;
import com.example.jobsearch.services.UserService;
import com.example.jobsearch.util.Utility;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserModelRepository userModelRepository;
    private final AuthorityRepository authorityRepository;
    private final FileUtil fileUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final EmailService emailService;

    @Override
    public UserDto getUser(String email) {
        UserModel userModel = userModelRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        String accType = getAccTypeByUserEmail(email);
        return getUserDto(userModel,accType);
    }

    private String getAccTypeByUserEmail(String email) {
        UserModel user = userModelRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return user.getRole().getRole();
    }

    @Override
    public UserDto getUserByEmail(String email) {
        String accType = getAccTypeByUserEmail(email);
        return getUserDto(userModelRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("user with email: " + email + " does not exists")), accType);
    }


    @Override
    public UserDto getUserById(Long id) {
        UserModel user = userModelRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user with id: " + id + " does not exists"));
        String accType = getAccTypeByUserEmail(user.getEmail());
        return getUserDto(user, accType);
    }

    @Override
    public void createUser(UserCreationDto dto) {
        if (userModelRepository.existsByEmail(dto.getEmail())) {
            throw new AlreadyExistsException("User with email:" + dto.getEmail() + " already exists.");
        }
        UserModel userModel = UserModel.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .age(Integer.valueOf(dto.getAge()))
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .phoneNumber(dto.getPhoneNumber())
                .enabled(true)
                .role(authorityRepository.findById(Long.valueOf(dto.getAccType())).orElseThrow(NoSuchElementException::new))
                .avatar(null)
                .build();
        userModelRepository.save(userModel);
    }


    @Override
    public UserDto getUserByPhone(String phone) {
        UserModel userModel = userModelRepository.findByPhoneNumber(phone).orElseThrow(() -> new UserNotFoundException("User by phone num: " + phone + " not found"));
        String accType = getAccTypeByUserEmail(userModel.getEmail());
        return getUserDto(userModel,accType);
    }

    @Override
    public Long getUserId(String email) {
        UserModel userModel1 = userModelRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User by email " + email + " not found"));
        return userModel1.getId();
    }

    @Override
    public void uploadImage(MultipartFile avatar, Authentication authentication) {
        if (avatar != null) {
            if (Objects.requireNonNull(avatar.getContentType()).matches("png|jpeg|jpg")) {
                throw new IllegalArgumentException("Unsupported img types (should be: \"png|jpeg|jpg\")");
            }
            String fileName = fileUtil.saveUploadedFile(avatar, "/images");
            UserModel user = userModelRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UserNotFoundException("User by email " + authentication.getName() + " not found"));
            user.setAvatar(fileName);
            userModelRepository.save(user);
        }
    }


    @Override
    public void editUser(EditUserDto user, Authentication auth) {
        UserModel userModel = userModelRepository.findByEmail(auth.getName()).orElseThrow(() -> new UserNotFoundException("User by email " + auth.getName() + " not found"));
        userModel.setName(user.getName());
        userModel.setSurname(user.getSurname());
        userModel.setPhoneNumber(user.getPhoneNumber());
        userModel.setAge(Integer.valueOf(user.getAge()));
        userModelRepository.save(userModel);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword, String email) {
        UserModel user = userModelRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User by email: " + email + "not registered"));
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userModelRepository.save(user);
        }
    }


    @Override
    public ResponseEntity<InputStreamResource> downloadImage(String name) {
        return fileUtil.getOutputFile(name, "/images");
    }


    @Override
    public Boolean loginCheck(String email, String password) {
        return userModelRepository.findAll().stream()
                .anyMatch(userModel ->
                        userModel.getEmail().equals(email) && passwordEncoder.matches(password, userModel.getPassword()));
    }

    @Override
    public UserModel getUserModelByEmail(String email) {
        return userModelRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User by email " + email + " not found"));
    }

    @Override
    public void makeResetPasswordLnk(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();
        updateResetPasswordToken(token, email);

        String resetPasswordLnk = Utility.getSiteUrl(request) + "/reset_password?token=" + token;
        emailService.sendEmail(email, resetPasswordLnk);
    }

    private void updateResetPasswordToken(String token, String email){
        UserModel userModel = userRepository.getByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User no found by email: " + email));
        userModel.setResetPasswordToken(token);
        userRepository.saveAndFlush(userModel);
    }

    @Override
    public UserModel getByResetPasswordToken(String token){
        return userModelRepository.findByResetPasswordToken(token).orElseThrow(()-> new UsernameNotFoundException("User no found by token: " + token));
    }

    @Override
    public void updatePassword(UserModel userModel, String password){
        String encodedPassword = encoder.encode(password);
        userModel.setPassword(encodedPassword);
        userModel. setResetPasswordToken(null);
        userRepository.saveAndFlush(userModel);
    }


    private UserDto getUserDto(UserModel userModel, String accType) {
        return UserDto.builder()
                .id(userModel.getId())
                .name(userModel.getName())
                .surname(userModel.getSurname())
                .age(userModel.getAge())
                .accType(accType)
                .email(userModel.getEmail())
                .phoneNumber(userModel.getPhoneNumber())
                .avatar(userModel.getAvatar())
                .build();
    }
}
