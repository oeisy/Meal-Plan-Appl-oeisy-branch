package com.recipe_mealplan.recipe_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.recipe_mealplan.recipe_app.entity.MealPlan;
import com.recipe_mealplan.recipe_app.repository.MealPlanRepository;

// handles logic related to CRUD operations

@Service
public class MealPlanService {

    private final MealPlanRepository mealPlanRepository;

    public MealPlanService(MealPlanRepository mealPlanRepository) {
        this.mealPlanRepository = mealPlanRepository;
    }

    public List<MealPlan> getAllMealPlans() {
        return mealPlanRepository.findAll();
    }

    public Optional<MealPlan> getMealPlanById(Long id) {
        return mealPlanRepository.findById(id);
    }

    public MealPlan createMealPlan(MealPlan mealPlan) {
        return mealPlanRepository.save(mealPlan);
    }

    public MealPlan updateMealPlan(Long id, MealPlan updatedMealPlan) {
        return mealPlanRepository.findById(id)
                .map(mealPlan -> {
                    mealPlan.setStartDate(updatedMealPlan.getStartDate());
                    mealPlan.setEndDate(updatedMealPlan.getEndDate());
                    mealPlan.setMeals(updatedMealPlan.getMeals());
                    mealPlan.setGroceryItems(updatedMealPlan.getGroceryItems());
                    return mealPlanRepository.save(mealPlan);
                })
                .orElseThrow(() -> new RuntimeException("MealPlan not found"));
    }

    public void deleteMealPlan(Long id) {
        mealPlanRepository.deleteById(id);
    }
}