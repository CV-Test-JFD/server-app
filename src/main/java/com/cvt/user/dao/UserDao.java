package com.cvt.user.dao;

import com.cvt.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {

    @Modifying
    @Query("delete from User u where u.id = ?1")
    void deleteHeaterByRoom(Long roomId);

    Optional<User> findByEmail(String email);
}