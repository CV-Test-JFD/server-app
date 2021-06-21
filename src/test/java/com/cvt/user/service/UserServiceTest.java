package com.cvt.user.service;

import com.cvt.user.model.User;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void CreateUserTest(){
        //arrange
        userService.create(new User("user test","test@email.com","passtest"));
        //act
        User user=userService.findByEmail("test@email.com");
        //assert
        Assertions.assertThat(user.getName()).isEqualTo("user test");
    }

    @Test
    void findUserTest(){
        //arrange
        Long id=2L;
        //act
        User user=userService.findOne(id);
        //assert
        Assertions.assertThat(user.getName()).isEqualTo("user 2");
    }
    @Test
    void findAllUserTest(){
        List<User> users=userService.findAll();
        Assertions.assertThat(users)
                .hasSize(5)
                .extracting(User::getName, User::getId)
                .containsExactlyInAnyOrder(
                        Tuple.tuple("user test", 1L),
                        Tuple.tuple("user 2", 2L),
                        Tuple.tuple("user 3", 3L),
                        Tuple.tuple("user 4", 4L),
                        Tuple.tuple("user 5", 5L));
    }

    @Test
    void deleteUserTest(){
        //arrange
        User user =userService.findOne(2L);
        //act
        userService.delete(user.getId());
        List<User> users=userService.findAll();
        //assert
        Assertions.assertThat(users)
                .hasSize(4)
                .extracting(User::getName, User::getId)
                .containsExactlyInAnyOrder(
                        Tuple.tuple("user test", 1L),
                        Tuple.tuple("user 3", 3L),
                        Tuple.tuple("user 5", 5L),
                        Tuple.tuple("user 4", 4L));
    }

}