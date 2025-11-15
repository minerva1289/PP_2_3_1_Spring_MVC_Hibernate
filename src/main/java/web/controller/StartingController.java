package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;


@Controller
public class StartingController {

    @Autowired
    private UserService userService;

    @GetMapping (value = "/")
    public String showList (Model model) {
        model.addAttribute("list_users", userService.getAllUsers());
        return "users_list";
    }

    @GetMapping (value = "/user/edit")
    public String showUser (@RequestParam long id, Model model) {
        if (userService.getUserByID(id) == null) {
            return "redirect:/";
        }
        model.addAttribute("user", userService.getUserByID(id));
        return "user";
    }

    @PostMapping ("/")
    public String addUser (@ModelAttribute ("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new_user";
        }
        if (userService.existsEmail(user.getEmail())) {
            bindingResult.addError(new FieldError("user", "email", user.getEmail(), false, null, null,
                    "This e-mail already exists"));
            return "new_user";
        }
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping (value = "/new")
    public String showNewForm (@ModelAttribute ("user") User user) {
        return "new_user";
    }

    @PostMapping (value = "/update")
    public String updateUser (@RequestParam long id, @ModelAttribute ("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user";
        }
        User oldUser = userService.getUserByID(id);
        if (oldUser == null) {
            return "redirect:/";
        }
        if (!oldUser.getEmail().equals(user.getEmail()) && userService.existsEmail(user.getEmail())) {
            bindingResult.addError(new FieldError("user", "email", user.getEmail(), false, null, null,
                    "This e-mail already exists"));
            return "user";
        }
        userService.updateUser(user);
        return "redirect:/";
    }

    @PostMapping (value = "/delete")
    public String deleteUser (@RequestParam long id) {
        if (userService.getUserByID(id) == null) {
            return "redirect:/";
        }
        userService.deleteUser(id);
        return "redirect:/";
    }
}
