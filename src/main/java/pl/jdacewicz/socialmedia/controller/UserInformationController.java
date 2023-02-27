package pl.jdacewicz.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmedia.domain.Post;
import pl.jdacewicz.socialmedia.domain.User;
import pl.jdacewicz.socialmedia.domain.UserInformation;
import pl.jdacewicz.socialmedia.service.DBUserDetailsService;
import pl.jdacewicz.socialmedia.service.PostService;
import pl.jdacewicz.socialmedia.util.FileUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class UserInformationController {

    private DBUserDetailsService detailsService;
    private PostService postService;

    @Autowired
    public UserInformationController(DBUserDetailsService detailsService, PostService postService) {
        this.detailsService = detailsService;
        this.postService = postService;
    }

    @GetMapping("/about-you")
    public String showCurrentUserInformationForm() {
        return "new-account-set-up";
    }

    @PostMapping("/about-you")
    public String setCurrentUserInformation(@RequestParam Map<String, String> body, @RequestParam("image") MultipartFile file) throws IOException {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userLoggedIn = detailsService.getUser(currentUser);

        if (userLoggedIn.isPresent()) {
            User user = userLoggedIn.get();
            UserInformation info = new UserInformation();
            info.setFirstname(body.get("firstname"));
            info.setLastname(body.get("lastname"));
            info.setGender(body.get("gender"));

            if (!file.isEmpty()) {
                String fileName = FileUtils.generateUniqueName(file.getOriginalFilename());
                info.setProfilePicture(fileName);

                String uploadDir = "uploads/user-photos/" + user.getId() + "/profile-pictures";
                FileUtils.saveFile(uploadDir, fileName, file);
            }
            user.setUserInformation(info);
            detailsService.updateUser(user);

            return "redirect:/";
        }
        //TODO Error page.
        return "redirect:/login";
    }

    @GetMapping("/user/{id}")
    public String showUserProfile(@PathVariable Long id, Model model) {
        Optional<User> user = detailsService.getUser(id);

        if (user.isPresent()) {
            List<Post> postList = postService.getAllPosts(user.get());
            model.addAttribute("user", user.get());
            model.addAttribute("posts", postList);

            return "user-profile";
        }
        return "error";
    }
}
