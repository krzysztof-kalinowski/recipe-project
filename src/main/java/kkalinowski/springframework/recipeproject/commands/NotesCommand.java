package kkalinowski.springframework.recipeproject.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Krzysztof Kalinowski on 27/11/2019.
 */

@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {
    private Long id;
//    private RecipeCommand recipe;
    private String recipeNotes;
}
