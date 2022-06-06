package com.delivery.deliveryapi.service;

import com.delivery.deliveryapi.dto.UserRequestDto;
import com.delivery.deliveryapi.dto.UserResponseDto;
import com.delivery.deliveryapi.model.Customer;
import com.delivery.deliveryapi.model.User;
import com.delivery.deliveryapi.model.UserRoleEnum;
import com.delivery.deliveryapi.repository.UserRepository;
import com.delivery.deliveryapi.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class CustomerService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public CustomerService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<Object> registerUser(UserRequestDto userRequestDto) {
        if (userRequestDto.getUsername() == null || userRequestDto.getPassword() == null) {
            throw new IllegalArgumentException("아이디, 비밀번호를 입력하세요");
        }

        if (!Pattern.matches("^[a-zA-Z0-9]{3,}$", userRequestDto.getUsername())) {
            throw new IllegalArgumentException("아이디는 영어, 숫자만 써야하고 3~20글자 만 가능합니다.");
        }

        if (!userRequestDto.getPassword().equals(userRequestDto.getPasswordCheck())) {
            //비밀번호, 비밀번호확인 다름
            throw new IllegalArgumentException("비밀번호를 확인해 주세요");
        }

        if (userRequestDto.getPassword().contains(userRequestDto.getUsername())) {
            throw new IllegalArgumentException("비밀번호에는 닉네임과 같은 값이 포함될 수 없습니다.");
        }
        if (userRequestDto.getPassword().length() < 4) {
            throw new IllegalArgumentException("비밀번호는 4글자 이상 입력해 주세요.");
        }
        User user = userRepository.findByUsername(userRequestDto.getUsername());
        if (user != null) {
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다.");
        }
        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        userRequestDto.setRoleEnum(UserRoleEnum.USER);


        return new ResponseEntity<>(
                new UserResponseDto(userRepository.save(new User(userRequestDto))), HttpStatus.OK);
    }

    public ResponseEntity<Object> login(UserRequestDto userRequestDto) {
        User user = userRepository.findByUsername(userRequestDto.getUsername());
        if (user == null) {
            return new ResponseEntity<>("로그인 정보가 없습니다.", HttpStatus.UNAUTHORIZED);
        }
        if (!passwordEncoder.matches(userRequestDto.getPassword(), user.getPassword())) {
            // System.out.println(passwordEncoder.encode(userRequestDto.getPassword()));
            // System.out.println(passwordEncoder.encode(user.getPassword()));
            return new ResponseEntity<>("로그인 실패", HttpStatus.UNAUTHORIZED);
        }
        String token= jwtUtil.generateJwtToken(user);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
