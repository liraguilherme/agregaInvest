package kodtech.agregainvest.controller;

import kodtech.agregainvest.controller.dto.AccountResponseDto;
import kodtech.agregainvest.controller.dto.CreateAccountDto;
import kodtech.agregainvest.controller.dto.CreateUserDto;
import kodtech.agregainvest.controller.dto.UpdateUserDto;
import kodtech.agregainvest.entity.User;
import kodtech.agregainvest.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto){
       var userId = userService.createUser(createUserDto);
        return ResponseEntity.created(URI.create("/v1/users/" + userId.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId){

       var user = userService.getUserUserById(userId);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
       var users =  userService.listUsers();

       return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId")String userId,
                                               @RequestBody UpdateUserDto updateUserDto){

        userService.updateUserById(userId, updateUserDto);
        return ResponseEntity.noContent().build();

    }



    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") String userId) {
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") String userId,
                                           @RequestBody CreateAccountDto createAccountDto){

        userService.createAccount(userId, createAccountDto);

        return  ResponseEntity.ok().build();
    }
    @GetMapping("/{userId}/accounts")
    public ResponseEntity<List<AccountResponseDto>> ListAccounts(@PathVariable("userId") String userId){

        var accounts = userService.listAccounts(userId);

        return  ResponseEntity.ok(accounts);
    }


}
