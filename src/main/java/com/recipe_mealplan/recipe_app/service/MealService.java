package com.recipe_mealplan.recipe_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.recipe_mealplan.recipe_app.entity.Meal;
import com.recipe_mealplan.recipe_app.repository.MealRepository; 

@Service
public class MealService {
    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    public Optional<Meal> getMealById(Long id) {
        return mealRepository.findById(id);
    }

    public Meal createMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    public Meal updateMeal(Long id, Meal updatedMeal) {
        return mealRepository.findById(id)
                .map(meal -> {
                    meal.setDate(updatedMeal.getDate());
                    meal.setMealPlan(updatedMeal.getMealPlan());
                    meal.setRecipe(updatedMeal.getRecipe());
                    return mealRepository.save(meal);
                })
                .orElseThrow(() -> new RuntimeException("Meal not found"));
    }

    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }

}