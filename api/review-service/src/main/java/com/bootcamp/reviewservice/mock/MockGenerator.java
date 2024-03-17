package com.bootcamp.reviewservice.mock;


import com.bootcamp.reviewservice.modules.review.dto.ReviewMapper;
import com.bootcamp.reviewservice.modules.review.dto.ReviewSaveRequest;
import com.bootcamp.reviewservice.modules.review.model.Review;
import com.bootcamp.reviewservice.modules.review.repository.ReviewRepository;
import com.bootcamp.reviewservice.modules.user.model.User;
import com.bootcamp.reviewservice.modules.user.repository.UserRepository;
import com.bootcamp.reviewservice.modules.useraddress.dto.UserAddressSaveRequest;
import com.bootcamp.reviewservice.modules.useraddress.model.UserAddressType;
import com.bootcamp.reviewservice.modules.useraddress.service.UserAddressService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MockGenerator implements ApplicationRunner {

    private final UserRepository userRepository;
    private final UserAddressService userAddressService;
    private final ReviewRepository reviewRepository;
    private final List<User> users = List.of(
            new User(1L, "emrahemirov", "Emrah", "Emirov"),
            new User(2L, "dummy", "Dummy", "User"));


    public void generateUserAddresses() {


        List<UserAddressSaveRequest> myAddresses = List.of(new UserAddressSaveRequest(
                "ev_adresi",
                "38.430690",
                "27.149312",
                "my_addressline",
                "14",
                "12",
                "23",
                UserAddressType.HOUSE,
                1L));


        myAddresses.forEach(userAddressService::save);


        System.out.println("MOCK USER_ADDRESSES GENERATED");


    }


    public void generateReviews() {

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.mockaroo.com/api/6c2c0530?count=10&key=bdb2a5e0")
                    .build();
            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();
            Gson gson = new Gson();
            List<ReviewSaveRequest> mocks = Arrays.asList(gson.fromJson(jsonData, ReviewSaveRequest[].class));
            List<Review> entities = ReviewMapper.INSTANCE.toReviewList(mocks);


            entities.forEach(entity -> {

                User user = users.stream()
                        .filter(element -> element.getId().equals(entity.getUserId()))
                        .findFirst().orElseThrow(() -> new RuntimeException());

                entity.setUser(user);
                entity.setRestaurantId("ad8ffdab-af63-4141-8d99-1583d12a0a7c");
            });
            reviewRepository.saveAll(entities);

            System.out.println("MOCK REVIEWS GENERATED");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.saveAll(users);
        generateUserAddresses();
        generateReviews();
    }
}
