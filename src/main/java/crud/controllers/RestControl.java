package crud.controllers;


import crud.model.User;
import crud.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestControl {

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public String save(@RequestBody User user) {
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/get")
    public User get(int id) {
       return userService.get(id);
    }

    @GetMapping("/all")
    public List<User> getAll() {
        return userService.listUsers();
    }

    @GetMapping("/delete")
    public String delete(int id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
