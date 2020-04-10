package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/")
public class HelloController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/")
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC-SECURITY application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);

		return "hello";
	}

    @GetMapping(value = "login")
    public String loginPage(@AuthenticationPrincipal User user) {
		Collection <? extends GrantedAuthority> authority = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

		if(user != null) {
			for(GrantedAuthority role : authority) {
				if(role.getAuthority().equals("ROLE_USER")) {
					return "redirect:/user";
				}

				if(role.getAuthority().equals("ROLE_ADMIN")) {
					return "redirect:/admin";
				}
			}
		}


		return "login";
    }

	/*@PostMapping(value = "login")
	public ModelAndView loginFine(@ModelAttribute("j_username") String name, @ModelAttribute("j_password") String password) {
		ModelAndView modelAndView = new ModelAndView();

        User user = (User) userService.getUserByName(name);
		System.out.println(name);

        modelAndView.addObject("authUser", user);
		return modelAndView;
	}*/

	@GetMapping(value = "registration")
	public String registration(ModelMap model) {
		model.addAttribute("userForm", new User());
		//todo
		return "registration";
	}

	@PostMapping(value = "registration")
	public String addUser(@ModelAttribute("userForm") User user, BindingResult bindingResult, ModelMap model) {

		if (bindingResult.hasErrors()) {
			return "registration";
		}

		try {
			userService.addUser(user);
		} catch (Exception e) {
			model.addAttribute("message", "Пользователь с таким именем уже существует");
			return "error";
		}

		return "redirect:/";
	}

	@GetMapping(value = "user")
	public String userPage(@AuthenticationPrincipal User user, ModelMap model) {
		model.addAttribute("authUser", user);
		return "user";
	}
}