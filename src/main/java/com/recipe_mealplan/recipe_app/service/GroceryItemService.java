// package com.recipe_mealplan.recipe_app.service;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.stereotype.Service;

// import com.recipe_mealplan.recipe_app.entity.GroceryItem;
// import com.recipe_mealplan.recipe_app.repository.GroceryItemRepository;

// @Service
// public class GroceryItemService {

//     private final GroceryItemRepository groceryItemRepository;

//     public GroceryItemService(GroceryItemRepository groceryItemRepository) {
//         this.groceryItemRepository = groceryItemRepository;
//     }

//     public List<GroceryItem> getAllGroceryItems() {
//         return groceryItemRepository.findAll();
//     }

//     public Optional<GroceryItem> getGroceryItemById(Long id) {
//         return groceryItemRepository.findById(id);
//     }

//     public GroceryItem createGroceryItem(GroceryItem groceryItem) {
//         return groceryItemRepository.save(groceryItem);
//     }

//     public GroceryItem updateGroceryItem(Long id, GroceryItem updatedGroceryItem) {
//         return groceryItemRepository.findById(id)
//                 .map(groceryItem -> {
//                     groceryItem.setIngredients(updatedGroceryItem.getIngredients());
//                     groceryItem.setQuantity(updatedGroceryItem.getQuantity());
//                     groceryItem.setMealPlan(updatedGroceryItem.getMealPlan());
//                     return groceryItemRepository.save(groceryItem);
//                 })
//                 .orElseThrow(() -> new RuntimeException("GroceryItem not found"));
//     }

//     public void deleteGroceryItem(Long id) {
//         groceryItemRepository.deleteById(id);
//     }

// }