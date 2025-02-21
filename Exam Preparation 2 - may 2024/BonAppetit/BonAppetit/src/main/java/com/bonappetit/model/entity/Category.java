package com.bonappetit.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//          •	Has a Category name (unique, not null)
//    an option between (MAIN_DISH, DESSERT, COCKTAIL)

//          •	Has a Description
//    For MAIN_DISH - "Heart of the meal, substantial and satisfying; main dish delights taste buds."
//    For DESSERT  – "Sweet finale, indulgent and delightful; dessert crowns the dining experience with joy."
//    For COCKTAIL – "Sip of sophistication, cocktails blend flavors, creating a spirited symphony in every glass."

//            •	Has collection of Recipes
//    One category may have many recipes, but one recipe has only one category.
//    Implement the entities with the correct data types and implement repositories for them.


    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryName name;

    private String description;

    @OneToMany(mappedBy = "category")
    private List<Recipe> recipes;




    public Category() {
        this.recipes = new ArrayList<>();
    }

    public Category(CategoryName name, String description) {
        this();

        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CategoryName getName() {
        return name;
    }

    public void setName(CategoryName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
