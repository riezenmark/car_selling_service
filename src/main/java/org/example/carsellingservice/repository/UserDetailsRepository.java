package org.example.carsellingservice.repository;

import org.example.carsellingservice.dao.UserDao;
import org.example.carsellingservice.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserDetailsRepository extends CrudRepository<User, String> {
    @Query(
            "SELECT new org.example.carsellingservice.dao.UserDao("
                    + "u.id, u.name, u.email, u.gender, u.locale, u.lastVisit, u.userpic"
                    + ") from User u"
    )
    Iterable<UserDao> getAllWithoutCars();
}
