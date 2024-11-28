package com.recipe_mealplan.recipe_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.recipe_mealplan.recipe_app.entity.Recipe;
import com.recipe_mealplan.recipe_app.repository.RecipeRepository; 

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe updateRecipe(Long id, Recipe updatedRecipe) {
        return recipeRepository.findById(id)
                .map(recipe -> {
                    recipe.setRecipeName(updatedRecipe.getRecipeName());
                    recipe.setInstructions(updatedRecipe.getInstructions());
                    recipe.setImageName(updatedRecipe.getImageName());
                    recipe.setFoodOrigin(updatedRecipe.getFoodOrigin());
                    recipe.setFoodType(updatedRecipe.getFoodType());
                    return recipeRepository.save(recipe);
                })
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

}