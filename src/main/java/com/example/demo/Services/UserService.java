package com.example.demo.Services;

import com.example.demo.Entity.User;
import com.example.demo.dto.UserDto;

public interface UserService {
 User findByUsername(String username);

 User save(UserDto userDto);

}