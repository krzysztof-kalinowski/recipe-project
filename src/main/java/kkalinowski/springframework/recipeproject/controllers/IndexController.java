package kkalinowski.springframework.recipeproject.controllers;

import kkalinowski.springframework.recipeproject.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Krzysztof Kalinowski on 17/11/2019.
 */
@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage(Model model){
        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}
