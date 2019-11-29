package kkalinowski.springframework.recipeproject.converters;


import kkalinowski.springframework.recipeproject.commands.CategoryCommand;
import kkalinowski.springframework.recipeproject.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryCommandToCategoryTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "description";
    CategoryCommandToCategory conveter;

    @BeforeEach
    public void setUp(){
        conveter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullObject(){
        assertNull(conveter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(conveter.convert(new CategoryCommand()));
    }

    @Test
    public void convert(){
        //given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setDescription(DESCRIPTION);

        //when
        Category category = conveter.convert(categoryCommand);

        //then
        assertEquals(ID_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }

}