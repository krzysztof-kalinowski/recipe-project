package kkalinowski.springframework.recipeproject.controllers;

import kkalinowski.springframework.recipeproject.commands.IngredientCommand;
import kkalinowski.springframework.recipeproject.commands.RecipeCommand;
import kkalinowski.springframework.recipeproject.commands.UnitOfMeasureCommand;
import kkalinowski.springframework.recipeproject.domain.UnitOfMeasure;
import kkalinowski.springframework.recipeproject.service.IngredientService;
import kkalinowski.springframework.recipeproject.service.RecipeService;
import kkalinowski.springframework.recipeproject.service.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class IngredientControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    @Mock
    IngredientService ingredientService;

    IngredientController controller;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new IngredientController(recipeService, unitOfMeasureService, ingredientService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void listIngredientsTest() throws Exception {

        RecipeCommand recipeCommand = new RecipeCommand();
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));

        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    void showRecipeIngredientTest() throws Exception {

        IngredientCommand command = new IngredientCommand();
        command.setId(2L);
        command.setRecipeId(1L);

        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(command);

        mockMvc.perform(get("/recipe/1/ingredient/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));

        verify(ingredientService, times(1)).findByRecipeIdAndIngredientId(anyLong(), anyLong());
    }

    @Test
    void updateRecipeIngredientTest() throws Exception {
        IngredientCommand command = new IngredientCommand();
        command.setId(2L);
        command.setRecipeId(1L);

        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(command);

        Set<UnitOfMeasureCommand> uomCommands = new HashSet<>();
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(1L);
        uomCommand.setDescription("UOM description");

        UnitOfMeasureCommand uomCommand2 = new UnitOfMeasureCommand();
        uomCommand2.setId(2L);
        uomCommand2.setDescription("UOM description 2");

        uomCommands.add(uomCommand);
        uomCommands.add(uomCommand2);

        when(unitOfMeasureService.listAllUoms()).thenReturn(uomCommands);

        mockMvc.perform(get("/recipe/1/ingredient/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));

    }

    @Test
    void saveOrUpdateTest() throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(2L);
        ingredientCommand.setId(1L);

        when(ingredientService.saveIngredientCommand(any())).thenReturn(ingredientCommand);

        mockMvc.perform(post("/recipe/2/ingredient/").contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredient/1/show"));

    }

    @Test
    public void newIngredientFormTest() throws Exception {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        //when
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
        when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

        //then
        mockMvc.perform(get("/recipe/1/ingredient/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));

        verify(recipeService, times(1)).findCommandById(anyLong());

    }

}