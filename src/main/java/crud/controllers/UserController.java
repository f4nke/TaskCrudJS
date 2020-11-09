package crud.controllers;

import crud.model.User;
import crud.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        List<User> list = userService.listUsers();
        model.addAttribute("userList", list);
        return "users";
    }

    @GetMapping("/user")
    public String getUserInfo(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("username", user.getName());
        model.addAttribute("id", user.getId());
        model.addAttribute("role", user.getRoles());

        return "user";
    }

    @GetMapping("/admin/new")
    public String newUser(Model model){
        model.addAttribute("newUser", new User());
        return "new";
    }

    @GetMapping("/admin/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        User user = userService.get(id);
        model.addAttribute("editUser", user);
        return "edit";
    }

    @PostMapping("/admin/edit")
    public String edit(@ModelAttribute("editUser") User user){
        userService.update(user);
        return "redirect:/users";
    }


    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute("newUser") User user ) {
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/admin/remove/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
