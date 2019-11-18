package kkalinowski.springframework.recipeproject.repositories;

import kkalinowski.springframework.recipeproject.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Krzysztof Kalinowski on 18/11/2019.
 */

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
