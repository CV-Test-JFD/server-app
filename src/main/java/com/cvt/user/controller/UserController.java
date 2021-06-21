package com.cvt.user.controller;


import com.cvt.user.dto.UserDto;
import com.cvt.user.model.User;
import com.cvt.user.service.UserService;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/user")
@CrossOrigin
@Transactional
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "GET ALL USERS")
    @GetMapping(path = "/{id}")
    public ResponseEntity findUser(@PathVariable Long id) {
        User user=userService.findOne(id);
        return (user!=null)? new ResponseEntity( new UserDto(user),HttpStatus.OK):
                new ResponseEntity( "Not found",HttpStatus.NOT_FOUND);
    }
    @ApiOperation(value = "GET ALL USERS")
    @GetMapping
    public ResponseEntity findAll() {
        List<UserDto> userList= userService.findAll().stream().map(UserDto::new)
                .collect(Collectors.toList());
        return new ResponseEntity( userList,HttpStatus.OK);
    }

    /*
    Create new user
    Args: UserDto
    Ret: UserDto
     */
    @ApiOperation(value = "CREATE A NEW USER BY PASSING VALID USER OBJECT")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success|Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "internal server error!!!")})
    @PostMapping
    public ResponseEntity create(@Validated @RequestBody UserDto dto) {
        User user=userService.findByEmail(dto.getEmail());
        if(user==null) {
            User newUser = userService.create(new User(dto.getName(), dto.getEmail(), dto.getPassword()));
            return new ResponseEntity(newUser, HttpStatus.CREATED);
        }
        return new ResponseEntity("Email already used",HttpStatus.BAD_REQUEST);
    }
    /*
    Delete a specific user
    Args: UserID
    Ret: OK
     */
    @ApiOperation(value = "DELETE A USER BY PASSING USER ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        User user=userService.findOne(id);
        if(user !=null){
            userService.delete(user.getId());
            return new ResponseEntity(HttpStatus.OK);
        }else
            return new ResponseEntity("Usre not found",HttpStatus.NOT_FOUND);

    }
}
