package kkalinowski.springframework.recipeproject.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Krzysztof Kalinowski on 18/11/2019.
 */

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;

}
