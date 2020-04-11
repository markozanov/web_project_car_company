package com.carcompany.web_project.web;

import com.carcompany.web_project.models.User;
import com.carcompany.web_project.service.UserService;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/users", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class UserApi {
    private final UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @PostMapping
    public User create(@RequestBody User user) throws Exception {
        if(user.getUsername() != null && userService.exists(user))
            throw new Exception("Username already exists");
        return userService.create(user);
    }

    @GetMapping("/{username}")
    public User getUserById(@PathVariable("username") String username) throws Exception {
        if(!userService.exists(username))
            throw new Exception("User doesn't exist");
        return userService.get(username);
    }

    @DeleteMapping("/{username}/delete")
    public User deleteUser(@PathVariable String username) throws Exception {
        if(!userService.exists(username))
            throw new Exception("User doesn't exist");
        return userService.delete(username);
    }

    @PatchMapping("/{username}/edit")
    public User editUser(@PathVariable String username,
                         @RequestParam String password) throws Exception {
        if(!userService.exists(username))
            throw new Exception("User doesn't exist");
        return userService.edit(username, password);
    }

}
