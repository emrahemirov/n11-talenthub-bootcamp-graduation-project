package com.bootcamp.restaurantservice.mock;

import com.bootcamp.restaurantservice.modules.restaurant.model.Restaurant;
import com.bootcamp.restaurantservice.modules.restaurant.repository.RestaurantRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class MockGenerator implements ApplicationRunner {

    private final RestaurantRepository repository;
    private final ResourceLoader resourceLoader;

    public void generate() {

        try {

            File file = new File(new File("").getAbsolutePath() + "/api/restaurant-service/src/main/java/com/bootcamp/restaurantservice/mock/mock-restaurants.json");
            ObjectMapper objectMapper = new ObjectMapper();
            List<MockRestaurant> mocks = objectMapper.readValue(file, new TypeReference<List<MockRestaurant>>() {
            });


            List<Restaurant> entities = IntStream.range(0, mocks.size())
                    .mapToObj(i -> {
                        MockRestaurant mockRestaurant = mocks.get(i);


                        Restaurant entity = new Restaurant();
                        entity.setGeo(mockRestaurant.latitude() + "," + mockRestaurant.longitude());
                        entity.setId(mockRestaurant.id());
                        entity.setName(mockRestaurant.name());
                        entity.setAverageRate(mockRestaurant.averageRate());
                        entity.setTotalReviewsCount(Long.valueOf(mockRestaurant.totalReviewsCount()));
                        entity.setImageSrc("https://generatorfun.com/code/uploads/Random-Food-image-" + (i % 19 + 1) + ".jpg");


                        return entity;
                    })
                    .toList();

            repository.deleteAll();
            repository.saveAll(entities);

            System.out.println("MOCK RESTAURANTS GENERATED");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        generate();
    }
}
