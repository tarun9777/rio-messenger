package com.rio.messenger.api;

import com.rio.messenger.exception.UserException;
import com.rio.messenger.http.response.ErrorResponse;
import com.rio.messenger.http.response.StatusResponse;
import com.rio.messenger.http.response.UserListResponse;
import com.rio.messenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = "/get/users",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<? extends StatusResponse> getUsers(){
        try{
            List<String> users = userService.getUsers();
            return ResponseEntity.ok().body(new UserListResponse(users));
        }catch (UserException e){
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getErrorMessage()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ErrorResponse("Something went wrong"));
        }
    }
}
