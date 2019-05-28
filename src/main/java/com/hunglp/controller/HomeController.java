package com.hunglp.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hunglp.dao.RoleDao;
import com.hunglp.domain.User;
import com.hunglp.domain.security.UserRole;
import com.hunglp.security.HashHelper;
import com.hunglp.service.UserRoleService;
import com.hunglp.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signupPost(@ModelAttribute User user, Model model) {
		if (userService.checkUserExists(user.getUsername(), user.getEmail())) {
			if (userService.checkUserNameExists(user.getUsername()))
				model.addAttribute("usernameExists", true);

			if (userService.checkEmailExists(user.getEmail()))
				model.addAttribute("emailExists", true);

			return "signup";
		} else {
			userService.save(user);
			
			userRoleService.save(new UserRole(user, roleDao.findByName("ROLE_USER")));
			model.addAttribute("signupSuccess", messageSource.getMessage("signup.success", null, null));
			return "index";
		}
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String signinPost(Principal principal, Model model, @ModelAttribute User user) {
		
		// Lay password ma hoa
		String salt = userService.getSaltByUsername(user.getUsername());
		String password = HashHelper.getSecurePassword(user.getPassword(), salt.getBytes());

		user = userService.findByUsernamePassword(user.getUsername(), password);
		if (user != null) {
			model.addAttribute("user", user);
			return "welcome";
		} else {
			model.addAttribute("signinFail", messageSource.getMessage("signin.fail", null, null));
			return "signin";
		}

	}

}
