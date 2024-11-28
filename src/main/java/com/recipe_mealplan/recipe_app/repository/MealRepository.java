package com.recipe_mealplan.recipe_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recipe_mealplan.recipe_app.entity.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    

}