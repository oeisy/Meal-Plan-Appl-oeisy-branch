package com.recipe_mealplan.recipe_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.recipe_mealplan.recipe_app.dto.UserSignupDTO;


@Controller
public class MyController {

    // Home page route (default route)
    @GetMapping("/")
    public String homePage() {
        return "home"; // This should render home.html
    }

    // Login page route
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // This should render login.html
    }

    @GetMapping("/signup")
    public String showSignupPage(Model model) {
        model.addAttribute("userSignupDTO", new UserSignupDTO());
        return "signup"; 
    }


    @GetMapping("/mealplans")
    public String showAllMealPlans(Model model) { //model is an interface used to pass data from controller to thymeleaf template, this will be used to add all the mealplans 
        return "mealplans"; //This renders the mealplans.html
    }
    
}


