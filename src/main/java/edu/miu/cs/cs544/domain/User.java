package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Data
public class User  extends AuditableEntity  implements UserDetails {
	@Id
	@GeneratedValue
	private Integer id;
	
	private String userName;
	
	private String password;
	
	private Boolean active;
	@Enumerated
	private UserType type;


	@OneToOne
	private Customer customer;

	public User() {
	}

	public User(String userName, String userPass, UserType type) {
		this.userName = userName;
		this.password = userPass;
		this.type = type;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
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
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
