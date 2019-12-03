package kkalinowski.springframework.recipeproject.service;

import kkalinowski.springframework.recipeproject.commands.IngredientCommand;

/**
 * Created by Krzysztof Kalinowski on 03/12/2019.
 */

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById(Long recipeId, Long ingredientId);
}
