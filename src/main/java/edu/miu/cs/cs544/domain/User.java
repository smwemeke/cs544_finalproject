package edu.miu.cs.cs544.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Data
@Builder
@AllArgsConstructor
public class User implements UserDetails {
	@Id
	@GeneratedValue
	private Integer id;
	
	private String userName;
	
	private String password;
	
	private Boolean active;
	@Enumerated
	private UserType type;
	@Embedded
	private AuditData auditData;

	public User() {
	}

	public User(String userName, String userPass, Boolean active, UserType type, AuditData auditData) {
		this.userName = userName;
		this.password = userPass;
		this.active = active;
		this.type = type;
		this.auditData = auditData;
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
