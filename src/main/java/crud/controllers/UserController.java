package crud.controllers;

import crud.model.User;
import crud.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public String userList(Model model) {
        List<User> list = userService.listUsers();
        model.addAttribute("userList", list);
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("newUser", new User());
        return "new";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        User user = userService.get(id);
        model.addAttribute("editUser", user);
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("editUser") User user){
        userService.update(user);
        return "redirect:/users";
    }


    @PostMapping("/add")
    public String addUser(@ModelAttribute("newUser") User user ) {
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/remove/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
