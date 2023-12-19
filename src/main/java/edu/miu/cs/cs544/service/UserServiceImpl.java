package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.dto.CustomerDtoAdapter;
import edu.miu.cs.cs544.dto.UserDto;
import edu.miu.cs.cs544.dto.UserDtoAdapter;
import edu.miu.cs.cs544.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;


    @Override
    public List<UserDto> getUsers() {

        return userRepository.findAll().stream().map(UserDtoAdapter::toUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserByUserName(String userName){
        User user = userRepository.findByUserName(userName);
        return UserDtoAdapter.toUserDto(user);
    }

    public UserDto updateUser(String userName, UserDto userDto){
        User oldUser = userRepository.findByUserName(userName);
        if (oldUser == null) return null;
        if (userDto.getUserName()==null) oldUser.setUserName(userName);
        else oldUser.setUserName(userDto.getUserName());
        oldUser.setPassword(userDto.getPassword());
        userRepository.save(oldUser);
        return UserDtoAdapter.toUserDto(oldUser);
    }
}