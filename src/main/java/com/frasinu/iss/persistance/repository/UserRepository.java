package com.frasinu.iss.persistance.repository;
import com.frasinu.iss.persistance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bjz on 5/18/2017.
 */
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select * from user_app a where a.username=:username", nativeQuery = true)
    User findByUsername(@Param("username") String username);
    @Query(value = "select * from user_app a where a.id=:id", nativeQuery = true)
    User findById(@Param("id") int id);


}
