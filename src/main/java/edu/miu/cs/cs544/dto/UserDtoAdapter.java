package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.User;

public class UserDtoAdapter {
    public static User toUser(UserDto userDto){
        if (userDto == null) return null;
        return new User(userDto.getUserName(), userDto.getPassword(),
                userDto.getActive(), userDto.getType(), userDto.getAuditData());
    }
    public static UserDto toUserDto(User user){
        if(user==null) return null;
        return new UserDto(user.getUsername(), user.getPassword(),
                user.getActive(), user.getType(), user.getAuditData());

    }
}
