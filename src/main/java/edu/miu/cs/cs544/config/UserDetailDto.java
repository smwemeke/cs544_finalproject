package edu.miu.cs.cs544.config;


import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.domain.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Locale;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailDto implements UserDetails {

    private Integer userId;

    private String userName;

    private String email;

    @JsonIgnore
    private String password;

    private UserType type;


    public UserDetailDto(User user) {
        this.userId = user.getId();
        this.userName = user.getUsername();
//        this.email = user.getEmail();
        this.password = user.getPassword();
        this.type = user.getType();

    }
//
//    public Role getRole() {
//        return role;
//    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var simpleGrantedAuthority = new SimpleGrantedAuthority(String.format("ROLE_%s", type));
        return List.of(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public boolean isAdmin() {
        return type == UserType.ADMIN;
    }
}
