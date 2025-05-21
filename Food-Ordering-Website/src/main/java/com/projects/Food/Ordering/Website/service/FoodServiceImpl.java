package com.projects.Food.Ordering.Website.service;

import com.projects.Food.Ordering.Website.model.Category;
import com.projects.Food.Ordering.Website.model.Food;
import com.projects.Food.Ordering.Website.model.Restaurant;
import com.projects.Food.Ordering.Website.repository.FoodRepository;
import com.projects.Food.Ordering.Website.request.CreateFoodRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FoodServiceImpl implements IFoodService{

    private final FoodRepository foodRepository;
    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
        Food newFood = new Food();
        newFood.setFoodCategory(category);
        newFood.setRestaurant(restaurant);
        newFood.setDescription(req.getDescription());
        newFood.setName(req.getName());
        newFood.setPrice(req.getPrice());
        newFood.setImages(req.getImages());
        newFood.setVeg(req.isVeg());
        Food savedFood= foodRepository.save(newFood);
        restaurant.getFoodItems().add(savedFood);
        return foodRepository.save(savedFood);
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {

    }

    @Override
    public List<Food> getRestaurantsFoodItems(Long restaurantId, boolean isVeg, boolean isNonveg, String foodCategory) {
        List<Food> foodItems = foodRepository.findByRestaurantId(restaurantId);
        if(isVeg){
            foodItems = filterItemsByVeg(foodItems, isVeg);
        }
        if(isNonveg){
            foodItems = filterItemsByNonveg(foodItems, isNonveg);
        }
        if(foodCategory!=null && !foodCategory.equals("")){
            foodItems = searchFoodByCategory(foodItems, foodCategory);
        }
        return null;
    }

    private List<Food> searchFoodByCategory(List<Food> foodItems, String foodCategory) {
        return foodItems.stream().filter(food -> {
            if(food.getFoodCategory()!=null){
                return food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterItemsByNonveg(List<Food> foodItems, boolean isNonveg) {
        return foodItems.stream().filter(food -> !food.isVeg()).collect(Collectors.toList());
    }

    private List<Food> filterItemsByVeg(List<Food> foodItems, boolean isVeg) {
         return foodItems.stream().filter(food -> food.isVeg()).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> optionalFood = foodRepository.findById(foodId);
        if(optionalFood==null){
            throw  new Exception("Food does not exist!");
        }
        return optionalFood.get();
    }

    @Override
    public Food updateAvailabilityStatus(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}
