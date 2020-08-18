package org.example.carsellingservice.repository;

import org.example.carsellingservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    //todo page
    @Query("SELECT u from User u where upper(u.username) like %:searchQuery%")
    List<User> findByNameLike(String searchQuery);

    boolean existsByUsername(String username);
}
