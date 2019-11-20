package kkalinowski.springframework.recipeproject.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Krzysztof Kalinowski on 18/11/2019.
 */

@Data
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String desctiption;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER) //Eager is default for OneToOne though
    private UnitOfMeasure uom;

    @ManyToOne
    private Recipe recipe;

    public Ingredient() {
    }

    public Ingredient(String desctiption, BigDecimal amount, UnitOfMeasure uom) {
        this.desctiption = desctiption;
        this.amount = amount;
        this.uom = uom;
    }

}
