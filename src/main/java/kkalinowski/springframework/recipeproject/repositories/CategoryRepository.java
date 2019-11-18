package kkalinowski.springframework.recipeproject.repositories;

import kkalinowski.springframework.recipeproject.domain.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Krzysztof Kalinowski on 18/11/2019.
 */

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
