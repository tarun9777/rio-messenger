package com.rio.messenger.api;


import com.rio.messenger.bo.UserBO;
import com.rio.messenger.exception.MessengerException;
import com.rio.messenger.http.response.ErrorResponse;
import com.rio.messenger.http.response.MsgResponse;
import com.rio.messenger.http.response.ResponseType;
import com.rio.messenger.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping(value = "/create/user",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<? extends MsgResponse> addUser(@RequestBody UserBO userBO){
        try {
            authService.addUser(userBO);
            return ResponseEntity.ok().body(new MsgResponse(ResponseType.SUCCESS));
        } catch (MessengerException e){
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getErrorMessage()));
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(new ErrorResponse("Something went wrong"));
        }
    }




}