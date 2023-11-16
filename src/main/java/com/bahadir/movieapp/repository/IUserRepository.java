package com.bahadir.movieapp.repository;

import com.bahadir.movieapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    List<User> findAllByOrderByName();
    List<User> findAllByNameLike(String name);
    List<User> findAllByEmailLike(String email);
    List<User> findAllByEmailEndsWith(String email);
    Optional<User> findByEmailAndPassword(String email,String password);
    Boolean existsByEmailAndPassword(String email,String password);
    Boolean existsByEmail(String email);

    //NativeQuery
    @Query(value = "select * from tbluser where length(password) >?1",nativeQuery = true)
    List<User> passwordLongerThanNativeQuery(int lenght);

    //jpaqueryLanguage (JPQL)
    @Query(value = "select u from User u where length(u.password) >:x")
    List<User> passwordLongerThanJPQL(@Param("x") int lenght);
}
