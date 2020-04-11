package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        List<User> users = userService.listUsers();
        User user = new User();

        model.addAttribute("user", user);
        model.addAttribute("users", users);

        return "list";
    }

    @RequestMapping(value = "admin/add", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute User user, @RequestParam Long role) {
        ModelAndView model = null;
        boolean flag = true;

        if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            model = new ModelAndView("redirect:/admin/error");
            model.addObject("message", "Enter the name or pass");
            flag = false;
        }

        if(flag) {
            try {
                user.setRoles(roleService.getAuthorityById(role));
                userService.addUser(user);
                model = new ModelAndView("redirect:/admin");
            } catch (Exception e) {
                model = new ModelAndView("redirect:/admin/error");
                model.addObject("message", "User already exists");
            }
        }

        return model;
    }

    @RequestMapping(value = "admin/error", method = RequestMethod.GET)
    public String errorHandler(@RequestParam String message, ModelMap model) {
        model.addAttribute("message", message);

        return "error";
    }

    @RequestMapping(value = "admin/delete", method = RequestMethod.POST)
    public ModelAndView deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);

        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(value = "admin/update", method = RequestMethod.GET)
    public String showUpdate(ModelMap model) {
        return "update";
    }

    @RequestMapping(value = "admin/update", method = RequestMethod.POST)
    public ModelAndView updateUser(@ModelAttribute User user, @RequestParam Long role) {
        ModelAndView model = null;
        boolean flag = true;
        System.out.println("" + role);

        if (user.getUsername().isEmpty()) {
            User userForOldName = (User) userService.getUserById(user.getId());
            user.setUsername(userForOldName.getUsername());
        }

        if(user.getPassword().isEmpty()) {
            User userForPass = (User) userService.getUserById(user.getId());
            System.out.println("*******************************************" + userForPass.getPassword());
            user.setPassword(userForPass.getPassword());
        }

        if(flag) {
            user.setRoles(roleService.getAuthorityById(role));
            userService.updateUser(user);

            model = new ModelAndView("redirect:/admin");
        }


        return model;
    }
}
