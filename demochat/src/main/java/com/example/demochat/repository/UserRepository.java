package com.example.demochat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

import com.example.demochat.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // UserId를 이용해 User 객체 가져오기

    // @Query(value = "SELECT * " +
    //         "FROM user" +
    //         "WHERE id = :id", nativeQuery = true)
    // User findByUserId(@Param("id") Long id);
}
