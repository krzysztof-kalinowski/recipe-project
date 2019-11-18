package kkalinowski.springframework.recipeproject.repositories;

import kkalinowski.springframework.recipeproject.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by Krzysztof Kalinowski on 18/11/2019.
 */

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);
}
