package kkalinowski.springframework.recipeproject.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Krzysztof Kalinowski on 18/11/2019.
 */

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesctiption() {
        return desctiption;
    }

    public void setDesctiption(String desctiption) {
        this.desctiption = desctiption;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public UnitOfMeasure getUom() {
        return uom;
    }

    public void setUom(UnitOfMeasure uom) {
        this.uom = uom;
    }
}
