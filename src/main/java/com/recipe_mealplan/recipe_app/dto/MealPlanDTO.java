package com.recipe_mealplan.recipe_app.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

// Data Transfer Object to encapsulate data transferred between API layer and client

@Getter
@Setter
public class MealPlanDTO {

    private Long mealPlanId;
    private Long userId; 
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Long> mealIds; 
    private List<Long> groceryItemIds; 
    
}