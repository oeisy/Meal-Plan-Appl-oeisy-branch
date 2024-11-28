package com.recipe_mealplan.recipe_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.recipe_mealplan.recipe_app.entity.RecipeIngredient;
import com.recipe_mealplan.recipe_app.repository.RecipeIngredientRepository;

@Service
public class RecipeIngredientService {
    private final RecipeIngredientRepository recipeIngredientRepository;

    public RecipeIngredientService(RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    public List<RecipeIngredient> getAllRecipeIngredients() {
        return recipeIngredientRepository.findAll();
    }

    public Optional<RecipeIngredient> getRecipeIngredientById(Long id) {
        return recipeIngredientRepository.findById(id);
    }

    public RecipeIngredient createRecipeIngredient(RecipeIngredient recipeIngredient) {
        return recipeIngredientRepository.save(recipeIngredient);
    }

    public RecipeIngredient updateRecipeIngredient(Long id, RecipeIngredient updatedRecipeIngredient) {
        return recipeIngredientRepository.findById(id)
                .map(recipeIngredient -> {
                    recipeIngredient.setIngredientName(updatedRecipeIngredient.getIngredientName());
                    recipeIngredient.setQuantity(updatedRecipeIngredient.getQuantity());
                    recipeIngredient.setUnit(updatedRecipeIngredient.getUnit());
                    recipeIngredient.setRecipe(updatedRecipeIngredient.getRecipe());
                    return recipeIngredientRepository.save(recipeIngredient);
                })
                .orElseThrow(() -> new RuntimeException("RecipeIngredient not found"));
    }

    public void deleteRecipeIngredient(Long id) {
        recipeIngredientRepository.deleteById(id);
    }

}