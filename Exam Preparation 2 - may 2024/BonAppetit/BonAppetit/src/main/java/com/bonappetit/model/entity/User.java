package com.bonappetit.model.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


//          •Has a Username (unique, not null)
//    Username length must be between 3 and 20 characters (inclusive of 3 and 20).

//           •	Has a Password (not null)
//    Password length must be between 3 and 20 characters (inclusive of 3 and 20).

//            •	Has an Email (unique, not null)
//    Must contain '@'.

//            •	Has an addedRecipes
//    A collection that contains all recipes that the user added.
//    One user may have many recipes and one recipe can be added by only one user.

//            •	Has a favouriteRecipes
//    A collection that contains recipes that the user marked as favourite.
//    One user may have many recipes and one recipe can be marked as favourite by many users.

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "addedBy")
    private List<Recipe> addedRecipes;

    @ManyToMany
    private List<Recipe> favouriteRecipes;


    public User() {
        this.addedRecipes = new ArrayList<>();
        this.favouriteRecipes = new ArrayList<>();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Recipe> getAddedRecipes() {
        return addedRecipes;
    }

    public void setAddedRecipes(List<Recipe> addedRecipes) {
        this.addedRecipes = addedRecipes;
    }

    public List<Recipe> getFavouriteRecipes() {
        return favouriteRecipes;
    }

    public void setFavouriteRecipes(List<Recipe> favouriteRecipes) {
        this.favouriteRecipes = favouriteRecipes;
    }

    public void addFavourite(Recipe recipe) {
        this.favouriteRecipes.add(recipe);

    }
}
