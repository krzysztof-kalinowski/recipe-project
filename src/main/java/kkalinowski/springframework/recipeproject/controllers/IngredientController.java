package kkalinowski.springframework.recipeproject.controllers;

import kkalinowski.springframework.recipeproject.commands.IngredientCommand;
import kkalinowski.springframework.recipeproject.commands.RecipeCommand;
import kkalinowski.springframework.recipeproject.commands.UnitOfMeasureCommand;
import kkalinowski.springframework.recipeproject.service.IngredientService;
import kkalinowski.springframework.recipeproject.service.RecipeService;
import kkalinowski.springframework.recipeproject.service.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by Krzysztof Kalinowski on 02/12/2019.
 */

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final UnitOfMeasureService unitOfMeasureService;
    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, UnitOfMeasureService unitOfMeasureService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.unitOfMeasureService = unitOfMeasureService;
        this.ingredientService = ingredientService;
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
    public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){
        log.debug("Getting ingredient for recipeId: " + recipeId +" and ingredientId: "+ingredientId);

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.parseLong(recipeId), Long.parseLong(ingredientId)));

        return "recipe/ingredient/show";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable String recipeId, Model model){

        //make sure we have a good id value
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.parseLong(recipeId));
        //todo raise exception if null

        //need to return back parent id for hidden form property
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.parseLong(recipeId));
        model.addAttribute("ingredient", ingredientCommand);

        //init uom
        ingredientCommand.setUom(new UnitOfMeasureCommand());

        model.addAttribute("uomList",  unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientform";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.parseLong(recipeId), Long.parseLong(ingredientId)));

        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        log.debug("Saved Recipe id: "+savedCommand.getRecipeId());
        log.debug("Saved Ingredient id: "+savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){
        ingredientService.deleteById(Long.parseLong(recipeId), Long.parseLong(ingredientId));

//        return listIngredients(recipeId, model);
        return "redirect:/recipe/"+Long.parseLong(recipeId)+"/ingredients";
    }

}
