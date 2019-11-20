package kkalinowski.springframework.recipeproject.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Krzysztof Kalinowski on 18/11/2019.
 */

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
