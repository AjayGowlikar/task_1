package work1.registration.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import work1.registration.entity.UserDtls;
import work1.registration.repository.UserRepository;

@Controller
public class UserController {


    @Autowired
    private UserRepository repo;


    @GetMapping("/")
    public String home (){
        return "index";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserDtls u, HttpSession session)
    {
        System.out.println(u);

        repo.save(u);
        session.setAttribute("message","User Register Sucessfully.");

        return "redirect:/";
    }
    @PostMapping("/login")
public String login(@ModelAttribute UserDtls user, HttpSession session, Model model) {
    try {
        org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute("user", userDetailsService.loadUserByUsername(user.getEmail()));
        return "redirect:/dashboard";
    } catch (Exception e) {
        session.setAttribute("error", "Invalid email or password");
        return "login";
    }
}

}
