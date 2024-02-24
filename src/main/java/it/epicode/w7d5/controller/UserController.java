package it.epicode.w7d5.controller;


import it.epicode.w7d5.exception.CustomResponse;
import it.epicode.w7d5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<CustomResponse> getEvent(@PathVariable int id){
        return CustomResponse.success(HttpStatus.OK.toString(), userService.bookedEventByUser(id), HttpStatus.OK);
    }
}
