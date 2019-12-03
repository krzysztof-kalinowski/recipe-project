package kkalinowski.springframework.recipeproject.service;

import kkalinowski.springframework.recipeproject.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * Created by Krzysztof Kalinowski on 03/12/2019.
 */

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}
