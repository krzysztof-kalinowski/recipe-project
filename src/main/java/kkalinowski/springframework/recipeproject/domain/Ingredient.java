package kkalinowski.springframework.recipeproject.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Krzysztof Kalinowski on 18/11/2019.
 */


@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER) //Eager is default for OneToOne though
    private UnitOfMeasure uom;

    @ManyToOne
    private Recipe recipe;

    public Ingredient() {
    }

    public Ingredient(String desctiption, BigDecimal amount, UnitOfMeasure uom) {
        this.description = desctiption;
        this.amount = amount;
        this.uom = uom;
    }

}
