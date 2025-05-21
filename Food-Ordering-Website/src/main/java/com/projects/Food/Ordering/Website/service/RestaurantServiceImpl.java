package com.projects.Food.Ordering.Website.service;

import com.projects.Food.Ordering.Website.dto.RestaurantDto;
import com.projects.Food.Ordering.Website.model.Address;
import com.projects.Food.Ordering.Website.model.Restaurant;
import com.projects.Food.Ordering.Website.model.User;
import com.projects.Food.Ordering.Website.repository.AddressRepository;
import com.projects.Food.Ordering.Website.repository.RestaurantRepository;
import com.projects.Food.Ordering.Website.repository.UserRepository;
import com.projects.Food.Ordering.Website.request.CreateRestaurantRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements IRestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
        Address address = addressRepository.save(req.getAddress());
        Restaurant createdRestaurant = new Restaurant();
        createdRestaurant.setAddress(address);
        createdRestaurant.setContactInfo(req.getContactInformation());
        createdRestaurant.setName(req.getName());
        createdRestaurant.setDescription(req.getDescription());
        createdRestaurant.setCuisineType(req.getCuisineType());
        createdRestaurant.setOpeningHours(req.getOpeningHours());
        createdRestaurant.setImages(req.getImages());
        createdRestaurant.setRegistrationDate(LocalDate.now());
        createdRestaurant.setOwner(user);


        return createdRestaurant;
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        if(restaurant.getCuisineType()!=null){
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }
        if(restaurant.getDescription()!=null){
            restaurant.setDescription(updatedRestaurant.getDescription());
        }
        if(restaurant.getAddress()!=null){
            restaurant.setAddress(restaurant.getAddress());
        }
        if(restaurant.getContactInfo()!=null){
            restaurant.setContactInfo(restaurant.getContactInfo());
        }
        if(restaurant.getOpeningHours()!=null){
            restaurant.setOpeningHours(restaurant.getOpeningHours());
        }
        if(restaurant.getName()!=null){
            restaurant.setName(restaurant.getName());
        }
        return restaurant;
    }

    @Override //only for admin and restaurant owner
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);

    }

    @Override //this is only for admin
    public List<Restaurant> getRestaurantList() {

        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long restaurantId) throws Exception {
        Optional<Restaurant> opt = restaurantRepository.findById(restaurantId);
        if(opt.isEmpty()){
            throw new Exception("Restaurant not found");
        }
        return opt.get();
    }

    @Override
    public Restaurant findRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
        if(restaurant==null){
            throw new Exception("Not found!");
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
        //first find the restaurant i wanna add.
        Restaurant restaurant = findRestaurantById(restaurantId);
        //now use dto.
        RestaurantDto dto = new RestaurantDto();
        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());
        dto.setId(restaurantId);
        dto.setTitle(restaurant.getName());
        if(user.getFavRestaurants().contains(dto)){
            user.getFavRestaurants().remove(dto);
        }
        else user.getFavRestaurants().add(dto);
        userRepository.save(user);
        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());

        return restaurant;
    }
}
