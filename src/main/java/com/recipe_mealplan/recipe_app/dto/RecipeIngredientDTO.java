package com.recipe_mealplan.recipe_app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeIngredientDTO {
    
    private Long recipeIngredientId;
    private Long recipeId;       // Reference to Recipe
    private String ingredientName;
    private Double quantity;
    private String unit;

}