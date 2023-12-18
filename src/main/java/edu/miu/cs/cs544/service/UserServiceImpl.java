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
import org.springframework.transaction.annotation.Transactional;

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
    public UserDto register(UserDto request){
        User user = UserDtoAdapter.toUser(request);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return request;
    }


    @Override
    public List<UserDto> getUsers() {

        return userRepository.findAll().stream().map(UserDtoAdapter::toUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserByUserName(String userName){
        User user = userRepository.findByUserName(userName);
        return UserDtoAdapter.toUserDto(user);
    }
@Override
@Transactional
    public UserDto removeUserById(Integer id){
    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()) return null;
    else {
        userRepository.deleteUserById(id);
        return UserDtoAdapter.toUserDto(user.get());
    }
    }

    public UserDto updateUser(String userName, UserDto userDto){
        return new UserDto();
    }
}
