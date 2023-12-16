package edu.miu.cs.cs544.service;


import edu.miu.cs.cs544.dto.LoginRequest;
import edu.miu.cs.cs544.dto.LoginResponse;

public interface LoginService {


    LoginResponse login(LoginRequest loginRequest);



}
