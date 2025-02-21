package com.bonappetit.controller;

import ch.qos.logback.core.joran.util.beans.BeanDescriptionFactory;
import com.bonappetit.config.UserSession;
import com.bonappetit.model.dto.AddRecipeDTO;
import com.bonappetit.model.dto.UserLoginDTO;
import com.bonappetit.model.dto.UserRegisterDTO;
import com.bonappetit.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RecipeController {

    private final RecipeService recipeService;
    private final UserSession userSession;

    public RecipeController(RecipeService recipeService, UserSession userSession) {
        this.recipeService = recipeService;
        this.userSession = userSession;
    }

    @ModelAttribute("recipeData")
    public AddRecipeDTO recipeData (){

        return new AddRecipeDTO();
    }

    @GetMapping("/add-recipe")
    public String addRecipe(){

        if (!userSession.isLoggedIn()){
            return "redirect:/";
        }
        return "/recipe-add";
    }

    @PostMapping("/add-recipe")
    public String doAddRecipe(@Valid AddRecipeDTO data, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (!userSession.isLoggedIn()){
            return "redirect:/";
        }


        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("recipeData", data);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.recipeData", bindingResult);
            return "redirect:/add-recipe";
        }

        boolean success = recipeService.create(data);

        if (!success){
            redirectAttributes.addFlashAttribute("recipeData", true);

            return "redirect:/add-recipe";
        }

        return "redirect:/home";
    }
    @PostMapping("/add-to-favourites/{recipeId}")
    public String addToFavourites(@PathVariable long recipeId){

        //@PathVariable е анотация, която показва,
        // че параметърът id трябва да бъде взет от пътната променлива в URL-то.
        //В този случай, ако потребителят посети URL /add-to-favorites/123,
        // тогава стойността 123 ще бъде присвоена на параметъра id и методът ще бъде извикан с тази стойност.


        if (!userSession.isLoggedIn()){
            return "redirect:/";
        }

        recipeService.addToFavourites(userSession.id(), recipeId);

        return "redirect:/home";

    }

}
