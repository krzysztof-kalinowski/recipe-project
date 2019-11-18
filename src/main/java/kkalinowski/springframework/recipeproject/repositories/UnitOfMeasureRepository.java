package kkalinowski.springframework.recipeproject.repositories;

import kkalinowski.springframework.recipeproject.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by Krzysztof Kalinowski on 18/11/2019.
 */

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByDescription(String description);
}
