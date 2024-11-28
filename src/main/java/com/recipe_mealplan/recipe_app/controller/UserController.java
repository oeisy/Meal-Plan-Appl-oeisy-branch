package com.recipe_mealplan.recipe_app.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipe_mealplan.recipe_app.dto.UserDTO;
import com.recipe_mealplan.recipe_app.dto.UserSignupDTO;
import com.recipe_mealplan.recipe_app.entity.User;
import com.recipe_mealplan.recipe_app.service.UserService;

import jakarta.validation.Valid;

// handles incoming http request and returns response

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(convertToDTO(user));
    }

    // @PostMapping
    // public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
    //     User user = convertToEntity(userDTO);
    //     User createdUser = userService.createUser(user);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(createdUser));
    // }

    
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserSignupDTO loginDTO){
        User user = userService.getUserByUsername(loginDTO.getUsername());
        if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user credentials.");
        }
    }


    @PostMapping("/signup")
    public ResponseEntity<String> signUpUser(@Valid @ModelAttribute UserSignupDTO signupDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input");
        }

        if (userService.getUserByUsername(signupDTO.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already taken.");
        }

        User user = new User();
        user.setUsername(signupDTO.getUsername());
        user.setPassword(passwordEncoder.encode(signupDTO.getPassword())); 

        userService.createUser(user);
        return ResponseEntity.ok("Signup successful! You can now login.");
    }
    



    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User userToUpdate = convertToEntity(userDTO);
        User updatedUser = userService.updateUser(id, userToUpdate);
        return ResponseEntity.ok(convertToDTO(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setMealPlanIds(user.getMealPlans().stream()
                .map(mealPlan -> mealPlan.getMealPlanId())
                .collect(Collectors.toList()));
        return dto;
    }

    private User convertToEntity(UserDTO dto) {
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setUsername(dto.getUsername());
        return user;
    }
}