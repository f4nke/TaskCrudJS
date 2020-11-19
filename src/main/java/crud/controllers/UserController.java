package crud.controllers;

import crud.model.Role;
import crud.model.User;
import crud.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String login() {
        return "login";
    }


    @GetMapping("admin")
    public String getAllUsers(@AuthenticationPrincipal User user,Model model){
        List<User> list = userService.listUsers();
        model.addAttribute("newUser", new User());
        model.addAttribute("newRole", new Role());
        model.addAttribute("authUser", user);
        model.addAttribute("allUsers", list);
        return "users";
    }

    @GetMapping("/admin/remove/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.delete(id);
        return "redirect:/admin";
    }


    @PostMapping("admin/add")
    public String addUser(@ModelAttribute("newUser") User user,
                          @ModelAttribute("newRole") Role role) {
        if(role.getRole().equals("USER") || role.getRole().equals("ADMIN")) {
            user.getRoles().add(role);
        } else {
            user.getRoles().add(new Role("USER"));
            user.getRoles().add(new Role("ADMIN"));
        }
        userService.add(user);
        return "redirect:/admin";
    }


    @GetMapping("user")
    public String usersPage(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);

        return "user";
    }


    @PostMapping ("admin/save")
    public String save(User user, @ModelAttribute("newRole") Role role){
        user.getRoles().add(role);
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        User user = userService.get(id);
        model.addAttribute("editUser", user);
        return "edit";
    }
    @PostMapping("/admin/edit")
    public String edit(@ModelAttribute("editUser") User user){
        userService.update(user);
        return "redirect:/admin";
    }


    @GetMapping ("admin/delete")
    public String delete(Integer id){
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping ("admin/get")
    @ResponseBody
    public User get(int id) {
        return userService.get(id);
    }


}

