package kkalinowski.springframework.recipeproject.repositories;

import kkalinowski.springframework.recipeproject.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Krzysztof Kalinowski on 18/11/2019.
 */

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}
