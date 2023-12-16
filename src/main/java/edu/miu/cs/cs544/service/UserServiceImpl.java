package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.domain.UserType;
import edu.miu.cs.cs544.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//            private PasswordProvider provider;
    @Override
    public void registerUser(String userName, String password) {
        userRepository.save(new User(userName, password, UserType.CUSTOMER));
    }
}
