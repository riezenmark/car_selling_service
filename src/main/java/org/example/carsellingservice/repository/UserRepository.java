package org.example.carsellingservice.repository;

import org.example.carsellingservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    //todo page
    @Query("SELECT u from User u where upper(u.name) like %:searchQuery% or upper(u.email) like %:searchQuery%")
    List<User> findByNameOrEmail(String searchQuery);
}
