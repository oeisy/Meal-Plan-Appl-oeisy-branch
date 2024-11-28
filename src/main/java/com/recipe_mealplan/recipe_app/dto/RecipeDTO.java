package com.recipe_mealplan.recipe_app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeDTO {

    private Long recipeId;
    private String recipe_name;
    private String instructions;
    private String image_name;
    private String food_origin;
    private String food_type;

}