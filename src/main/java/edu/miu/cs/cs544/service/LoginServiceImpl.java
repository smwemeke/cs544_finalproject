package edu.miu.cs.cs544.service;


import edu.miu.cs.cs544.config.AuthHelper;
import edu.miu.cs.cs544.config.JwtHelper;
import edu.miu.cs.cs544.config.UserDetailDto;
import edu.miu.cs.cs544.dto.LoginRequest;
import edu.miu.cs.cs544.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;

    private final JwtHelper jwtHelper;

    private final AuthHelper authHelper;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        var result = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        UserDetailDto user = (UserDetailDto) result.getPrincipal();
        var accessToken = jwtHelper.generateToken(user);
        var refreshToken = jwtHelper.generateRefreshToken(user);

        return new LoginResponse(accessToken, refreshToken);
    }


}
