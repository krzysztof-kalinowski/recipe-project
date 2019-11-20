package kkalinowski.springframework.recipeproject.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    Category category;

    @BeforeEach
    public void setUp(){
        category = new Category();
    }

    @Test
    void getId() {
        Long id = 4L;
        category.setId(id);
        assertEquals(id, category.getId());
    }

    @Test
    void getDescription() {
        String desc = "description";
        category.setDescription(desc);
        assertEquals(desc, category.getDescription());
    }

    @Test
    void getRecipes() {

    }
}