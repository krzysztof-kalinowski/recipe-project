package kkalinowski.springframework.recipeproject.controllers;

import kkalinowski.springframework.recipeproject.commands.RecipeCommand;
import kkalinowski.springframework.recipeproject.exceptions.NotANumberException;
import kkalinowski.springframework.recipeproject.exceptions.NotFoundException;
import kkalinowski.springframework.recipeproject.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Krzysztof Kalinowski on 27/11/2019.
 */


@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        checkForNaN(id);

        model.addAttribute("recipe", recipeService.findById(Long.parseLong(id)));

        return "recipe/show";
    }



    @GetMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        checkForNaN(id);

        model.addAttribute("recipe", recipeService.findCommandById(Long.parseLong(id)));

        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteById(@PathVariable String id){
        checkForNaN(id);

        recipeService.deleteById(Long.parseLong(id));

        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){
        log.error("Handling not found exception message: "+exception.getMessage());
        ModelAndView modelAndView = new ModelAndView("404error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotANumberException.class)
    public ModelAndView handleNotANumber(Exception exception){
        log.error("Handling not a number exception message: "+exception.getMessage());
        ModelAndView modelAndView = new ModelAndView("404error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }

    private void checkForNaN(String str) {
        if (str == null) {
            throw new NotANumberException("NotANumberException - Bad input String - is Empty");
        }
        try {
            Long.parseLong(str);
        } catch (NumberFormatException e) {
            throw new NotANumberException("NotANumberException - Bad input String: "+str);
        }
    }

}
