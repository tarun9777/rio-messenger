package com.rio.messenger.api;


import com.rio.messenger.bo.UserBO;
import com.rio.messenger.exception.UserException;
import com.rio.messenger.http.response.ErrorResponse;
import com.rio.messenger.http.response.StatusResponse;
import com.rio.messenger.http.response.ResponseType;
import com.rio.messenger.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping(value = "/create/user",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<? extends StatusResponse> addUser(@RequestBody UserBO userBO){
        try {
            authService.addUser(userBO);
            return ResponseEntity.ok().body(new StatusResponse(ResponseType.SUCCESS));
        } catch (UserException e){
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getErrorMessage()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ErrorResponse("Something went wrong"));
        }
    }

    @PostMapping(value = "/login",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<? extends StatusResponse> login(@RequestBody UserBO userBO){
        try{
            String token = authService.authenticateUser(userBO);
            return ResponseEntity.ok().header("x-token",token).body(new StatusResponse(ResponseType.SUCCESS));
        }catch (UserException e){
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getErrorMessage()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ErrorResponse("Something went wrong"));
        }
    }

    @PostMapping(value = "/logout",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<? extends StatusResponse> logout(@RequestBody UserBO userBO, @RequestHeader("x-username") String username){
        try{
            if (!username.equals(userBO.getUsername())){
                throw new UserException("invalid input");
            }
            authService.logout(userBO);
            return ResponseEntity.ok().body(new StatusResponse(ResponseType.SUCCESS));
        }catch (UserException e){
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getErrorMessage()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ErrorResponse("Something went wrong"));
        }
    }
}
