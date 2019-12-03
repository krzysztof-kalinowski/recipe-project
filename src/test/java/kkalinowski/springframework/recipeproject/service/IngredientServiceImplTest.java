package kkalinowski.springframework.recipeproject.service;

import kkalinowski.springframework.recipeproject.commands.IngredientCommand;
import kkalinowski.springframework.recipeproject.commands.UnitOfMeasureCommand;
import kkalinowski.springframework.recipeproject.converters.IngredientCommandToIngredient;
import kkalinowski.springframework.recipeproject.converters.IngredientToIngredientCommand;
import kkalinowski.springframework.recipeproject.converters.UnitOfMeasureCommandToUnitOfMeasure;
import kkalinowski.springframework.recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import kkalinowski.springframework.recipeproject.domain.Ingredient;
import kkalinowski.springframework.recipeproject.domain.Recipe;
import kkalinowski.springframework.recipeproject.domain.UnitOfMeasure;
import kkalinowski.springframework.recipeproject.repositories.RecipeRepository;
import kkalinowski.springframework.recipeproject.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {

    IngredientToIngredientCommand ingredientToIngredientCommand;
    IngredientCommandToIngredient ingredientCommandToIngredient;

    IngredientService ingredientService;

    @Mock
    RecipeRepository recipeRepository;
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
        ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());

        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient, recipeRepository, unitOfMeasureRepository);
    }

    @Test
    void findByRecipeIdAndIngredientId() {
        //given
        Long recipeId = 1L;
        Long ingredientId = 2L;

        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientId);

        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(ingredient);

        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        recipe.setIngredients(ingredients);
        ingredient.setRecipe(recipe);

        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        //when
        IngredientCommand command = ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId);

        //then
        assertEquals(recipeId, command.getRecipeId());
        assertEquals(ingredientId, command.getId());
        verify(recipeRepository, times(1)).findById(anyLong());

    }

    @Test
    void saveIngredientCommand() {

        //given
        Long ingredientId = 3L;
        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);


        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(ingredientId);

        when(recipeRepository.save(any())).thenReturn(savedRecipe);


        IngredientCommand command = new IngredientCommand();
        command.setId(ingredientId);
        command.setRecipeId(5L);

        //when
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        //then
        assertEquals(ingredientId, savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));

    }

    @Test
    void deleteById() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Ingredient ingredient = new Ingredient();
        ingredient.setId(3L);
        recipe.addIngredient(ingredient);
        ingredient.setRecipe(recipe);

        Optional<Recipe> optionalRecipe = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        //when
        ingredientService.deleteById(1L, 3L);

        //then
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }
}