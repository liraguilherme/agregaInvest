package kodtech.agregainvest.service;


import kodtech.agregainvest.controller.dto.AccountResponseDto;
import kodtech.agregainvest.controller.dto.CreateAccountDto;
import kodtech.agregainvest.controller.dto.CreateUserDto;
import kodtech.agregainvest.controller.dto.UpdateUserDto;
import kodtech.agregainvest.entity.Account;
import kodtech.agregainvest.entity.BillingAddress;
import kodtech.agregainvest.entity.User;
import kodtech.agregainvest.repository.AccountRepository;
import kodtech.agregainvest.repository.BillingAddressRepository;
import kodtech.agregainvest.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;

@Service
public class UserService {

    private UserRepository userRepository;

    private AccountRepository accountRepository;

    private BillingAddressRepository billingAddressRepository;

    public UserService(UserRepository userRepository, AccountRepository accountRepository, BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.billingAddressRepository = billingAddressRepository;
    }

    public UUID createUser(CreateUserDto createUserDto) {

        //DTO -> ENTITY
        var entity = new User(UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null);

        var userSaved = userRepository.save(entity);
        return userSaved.getUserId();

    }

    public Optional<User> getUserUserById(String userId) {

        return userRepository.findById(UUID.fromString(userId));
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void updateUserById(String userId,
                               UpdateUserDto updateUserDto) {

        var id = UUID.fromString(userId);
        var userEntity = userRepository.findById(id);

        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if (updateUserDto.username() != null) {
                user.setUsername(updateUserDto.username());
            }

            if (updateUserDto.password() != null) {
                user.setPassword(updateUserDto.password());
            }
            userRepository.save(user);

        }
    }

    public void deleteById(String userId) {
        var id = UUID.fromString(userId);

        var userExists = userRepository.existsById(id);

        if (userExists) {
            userRepository.deleteById(id);
        }
    }

    public void createAccount(String userId, CreateAccountDto createAccountDto) {

        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não existe"));

       /* if (isNull(user.getAccounts())) {
            user.setAccounts(new ArrayList<>());
        }*/

        //CONVERSÃO DTO -> ENTITY
        var account = new Account(
                UUID.randomUUID(),
                createAccountDto.description(),
                user,
                null,
                new ArrayList<>()
        );

        var accountCreated = accountRepository.save(account);

        var billingAdress = new BillingAddress(
                accountCreated.getAccountId(),
                account,
                createAccountDto.number(),
                createAccountDto.street()

        );
        billingAddressRepository.save(billingAdress);


    }


    public List<AccountResponseDto> listAccounts(String userId) {
       var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

              return user.getAccounts()
                        .stream()
                        .map(ac
                                -> new AccountResponseDto(ac.getAccountId().toString(), ac.getDescription()))
                        .toList();

    }

}
