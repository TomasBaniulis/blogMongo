package lt.code.academy.blogmongo.controller;

import lombok.AllArgsConstructor;
import lt.code.academy.blogmongo.dto.User;
import lt.code.academy.blogmongo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/create")
    public String openUserForm (Model model){
        model.addAttribute("user", new User());
        return "/form/user";
    }

    @PostMapping("/create")
    public String createUser (User user){
        userService.createUser(user);
        return "redirect:/blog";
    }

}
