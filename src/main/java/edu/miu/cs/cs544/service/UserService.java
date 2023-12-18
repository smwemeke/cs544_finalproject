package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.dto.CustomerDto;
import edu.miu.cs.cs544.dto.UserDto;

import java.util.List;

public interface UserService {
    //void registerUser(String userName, String password);
    UserDto register(UserDto request);
    List<UserDto> getUsers();
    UserDto getUserByUserName(String userName);

    UserDto updateUser(String userName, UserDto userDto);
}
