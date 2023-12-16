package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String name);
}
