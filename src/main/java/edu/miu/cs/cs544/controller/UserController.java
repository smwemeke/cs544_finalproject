package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.dto.ErrorResponse;
import edu.miu.cs.cs544.dto.UserDto;

import edu.miu.cs.cs544.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    // hello
    @PostMapping("/hello")
    public ResponseEntity<?> hello() {
        return new ResponseEntity<>("Welcome", HttpStatus.OK);
    }

    // Get all users
    @GetMapping("")
    public ResponseEntity<?> getUsers(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PutMapping("/update/{userName}")
    public ResponseEntity<?> updateUser(@PathVariable String userName, @RequestBody UserDto request){

        UserDto response = userService.updateUser(userName, request);
        if (response != null) return new ResponseEntity<>(response, HttpStatus.OK);
        else return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_MODIFIED.value(), "There is no User with the provided user name"), HttpStatus.NOT_MODIFIED);
    }
// Find user by username
    @GetMapping("/{userName}")
    public ResponseEntity<?> getUserByUserName(@PathVariable String userName){
        UserDto userDto = userService.getUserByUserName(userName);
        if(userDto!=null){
            return new ResponseEntity<>(userDto, HttpStatus.FOUND);
        }
        else return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
    }

}
