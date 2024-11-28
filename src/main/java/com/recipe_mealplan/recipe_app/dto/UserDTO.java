package com.recipe_mealplan.recipe_app.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

//data transfer objects to encapsulate data that is transfered between api layer and client

@Getter
@Setter
public class UserDTO {
    private Long userId;
    private String username;
    private List<Long> mealPlanIds; 

}