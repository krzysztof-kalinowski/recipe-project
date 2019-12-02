package kkalinowski.springframework.recipeproject.controllers;

import kkalinowski.springframework.recipeproject.commands.IngredientCommand;
import kkalinowski.springframework.recipeproject.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.Set;

/**
 * Created by Krzysztof Kalinowski on 02/12/2019.
 */

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model){
        log.debug("Getting ingredient list for recipeId: " + recipeId);
        model.addAttribute("recipe", recipeService.findCommandById(Long.parseLong(recipeId)));

        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredientById(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){
        log.debug("Getting ingredient for recipeId: " + recipeId +" and ingredientId: "+ingredientId);

        Long ingId = Long.parseLong(ingredientId);
        Optional<IngredientCommand> ingredient = recipeService.findCommandById(Long.parseLong(recipeId)).getIngredients()
                .stream()
                .filter(ingredientCommand -> ingredientCommand.getId().equals(ingId))
                .findFirst();


        model.addAttribute("ingredient", ingredient.get());

        return "recipe/ingredient/show";
    }
}
