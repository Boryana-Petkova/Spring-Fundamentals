package com.bonappetit.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//          •	Has a Name (not null)
//    Name length must be between 2 and 40 characters (inclusive of 2 and 40).

//            •	Has Ingredients
//    Translation length must be between 2 and 150 characters (inclusive of 2 and 150).

//            •	Has a Category (not null)
//    One recipe has one caegory and one category can have many recipes.

//          •	Has an addedBy
//    The user who has added the recipe in the app. One recipe has one user, but one user may have added many recipe.

    @Column(nullable = false)
    private String name;


    private String ingredients;

    @ManyToOne(optional = false)
    private Category category;

    @ManyToOne(optional = false)
    private User addedBy;




    public Recipe() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }
}
