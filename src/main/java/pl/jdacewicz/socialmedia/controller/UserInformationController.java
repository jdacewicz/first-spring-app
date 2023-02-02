package pl.jdacewicz.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.jdacewicz.socialmedia.domain.User;
import pl.jdacewicz.socialmedia.domain.UserInformation;
import pl.jdacewicz.socialmedia.service.DBUserDetailsService;

import java.util.Map;
import java.util.Optional;

@Controller
public class UserInformationController {

    private DBUserDetailsService detailsService;

    @Autowired
    public UserInformationController(DBUserDetailsService detailsService) {
        this.detailsService = detailsService;
    }

    @GetMapping("/about-you")
    public String showCurrentUserInformationForm() {
        return "new-account-set-up";
    }

    @PostMapping("/about-you")
    public String setCurrentUserInformation(@RequestParam Map<String, String> body) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userLoggedIn = detailsService.findUserByUsername(currentUser);

        UserInformation info = new UserInformation();
        info.setFirstname(body.get("firstname"));
        info.setLastname(body.get("lastname"));

        if (userLoggedIn.isPresent()) {
            userLoggedIn.get().setUserInformation(info);
            detailsService.updateUser(userLoggedIn.get());

            return "redirect:/";
        }
        //TODO Error page.
        return "redirect:/login";
    }
}
