package org.example.carsellingservice.repository;

import org.example.carsellingservice.dao.UserDao;
import org.example.carsellingservice.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserDetailsRepository extends CrudRepository<User, String> {
    @Query(
            "SELECT new org.example.carsellingservice.dao.UserDao("
                    + "u.id, u.name, u.userpic, u.email, u.gender, u.locale, u.lastVisit"
                    + ") from User u"
    )
    Iterable<UserDao> getAllWithoutCars();

    @Query(
            "SELECT new org.example.carsellingservice.dao.UserDao("
                    + "u.id, u.name, u.userpic, u.email, u.gender, u.locale, u.lastVisit) "
                    + "from User u where upper(u.name) like %:q% or upper(u.email) like %:q%"
    )
    Iterable<UserDao> getWithoutCarsByName(@Param("q") String q);
}
