package kkalinowski.springframework.recipeproject.bootstrap;

import kkalinowski.springframework.recipeproject.domain.*;
import kkalinowski.springframework.recipeproject.repositories.CategoryRepository;
import kkalinowski.springframework.recipeproject.repositories.RecipeRepository;
import kkalinowski.springframework.recipeproject.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Krzysztof Kalinowski on 18/11/2019.
 */
@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes(){
        List<Recipe> recipes = new ArrayList<>();

        //get UOMs
        UnitOfMeasure teaSpoon = getUnitOfMeasure("Teaspoon");
        UnitOfMeasure tableSpoon = getUnitOfMeasure("Tablespoon");
        UnitOfMeasure cup = getUnitOfMeasure("Cup");
        UnitOfMeasure pinch = getUnitOfMeasure("Pinch");
        UnitOfMeasure ounce = getUnitOfMeasure("Ounce");
        UnitOfMeasure dash = getUnitOfMeasure("Dash");
        UnitOfMeasure each = getUnitOfMeasure("Each");

        //get Categories
        Category american = getCategory("American");
        Category mexican = getCategory("Mexican");

        //------------------------------Yummy Guac
        Recipe guacamole = new Recipe();
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!");
        guacamole.setNotes(guacNotes);

        guacamole.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), each));
        guacamole.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teaSpoon));
        guacamole.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(".5"), tableSpoon));
        guacamole.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoon));
        guacamole.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), each));
        guacamole.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(1), dash));
        guacamole.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), each));
        //TODO wiecej skladników

        guacamole.getCategories().add(american);
        guacamole.getCategories().add(mexican);

        recipes.add(guacamole);
        log.debug("Bootstrap: Guacamole recipe added");

        //------------------------------Spicy Grilled Chicken Tacos
        Recipe chicken = new Recipe();
        chicken.setDescription("Spicy Grilled Chicken Tacos");
        chicken.setPrepTime(20);
        chicken.setCookTime(15);
        chicken.setDifficulty(Difficulty.MODERATE);
        chicken.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");

        Notes chickenNotes = new Notes();
        chickenNotes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");

        chicken.setNotes(chickenNotes);
        chicken.addIngredient(new Ingredient("ancho chili powder", new BigDecimal(2), tableSpoon));
        chicken.addIngredient(new Ingredient("dried oregano", new BigDecimal(1), tableSpoon));
        chicken.addIngredient(new Ingredient("dried cumin", new BigDecimal(1), tableSpoon));
        chicken.addIngredient(new Ingredient("sugar", new BigDecimal(1), tableSpoon));
        chicken.addIngredient(new Ingredient("salt", new BigDecimal(.5), tableSpoon));
        chicken.addIngredient(new Ingredient("clove garlic, finely chopped", new BigDecimal(1), each));
        chicken.addIngredient(new Ingredient("finely grated orange zest", new BigDecimal(1), tableSpoon));
        chicken.addIngredient(new Ingredient("olive oil", new BigDecimal(2), tableSpoon));
        chicken.addIngredient(new Ingredient("skinless, boneless chicken thighs", new BigDecimal(6), each));

        chicken.getCategories().add(american);
        chicken.getCategories().add(mexican);

        recipes.add(chicken);
        log.debug("Bootstrap: chicken recipe added");

        return recipes;
    }

    private UnitOfMeasure getUnitOfMeasure(String description){
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription(description);
        if(!unitOfMeasureOptional.isPresent()){
            throw new RuntimeException("Expected UOM by description '"+description+"' not found");
        }
        return unitOfMeasureOptional.get();
    }

    private Category getCategory(String description){
        Optional<Category> categoryOptional = categoryRepository.findByDescription(description);
        if(!categoryOptional.isPresent()){
            throw new RuntimeException("Expected Category by description '"+description+"' not found");
        }
        return categoryOptional.get();
    }

}
