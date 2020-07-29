package org.example.carsellingservice.repository;

import org.example.carsellingservice.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
