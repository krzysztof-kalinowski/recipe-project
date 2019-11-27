package kkalinowski.springframework.recipeproject.service;

import kkalinowski.springframework.recipeproject.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Krzysztof Kalinowski on 18/11/2019.
 */


public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);
}
