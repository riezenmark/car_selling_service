package org.example.carsellingservice.repository;

import org.example.carsellingservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u from User u where upper(u.username) like %:searchQuery%")
    List<User> findByUsernameLike(String searchQuery);

    boolean existsByUsername(String username);

    UserDetails findByUsername(String username);
}
