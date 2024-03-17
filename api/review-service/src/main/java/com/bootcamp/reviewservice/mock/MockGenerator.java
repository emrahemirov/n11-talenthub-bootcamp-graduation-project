package com.bootcamp.reviewservice.mock;


import com.bootcamp.reviewservice.modules.review.dto.ReviewMapper;
import com.bootcamp.reviewservice.modules.review.dto.ReviewSaveRequest;
import com.bootcamp.reviewservice.modules.review.model.Review;
import com.bootcamp.reviewservice.modules.review.repository.ReviewRepository;
import com.bootcamp.reviewservice.modules.user.model.User;
import com.bootcamp.reviewservice.modules.user.repository.UserRepository;
import com.bootcamp.reviewservice.modules.useraddress.dto.UserAddressSaveRequest;
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
    private List<User> users;


    public void generateUsers() {

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.mockaroo.com/api/a8661310?count=100&key=15ff0280")
                    .build();
            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();
            Gson gson = new Gson();
            List<MockUser> mocks = Arrays.asList(gson.fromJson(jsonData, MockUser[].class));

            List<User> entities = mocks.stream().map(mock -> {
                        User entity = new User();
                        entity.setUsername(mock.username());
                        entity.setName(mock.name());
                        entity.setSurname(mock.surname());

                        return entity;
                    })
                    .toList();


            users = entities;

            userRepository.saveAll(entities);
            System.out.println("MOCK USERS GENERATED");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void generateUserAddresses() {

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.mockaroo.com/api/230c78a0?count=100&key=15ff0280")
                    .build();
            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();
            Gson gson = new Gson();
            List<UserAddressSaveRequest> mocks = Arrays.asList(gson.fromJson(jsonData, UserAddressSaveRequest[].class));


            mocks.forEach(userAddressService::save);


            System.out.println("MOCK USER_ADDRESSES GENERATED");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void generateReviews() {

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.mockaroo.com/api/6c2c0530?count=1000&key=15ff0280")
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
            });
            reviewRepository.saveAll(entities);

            System.out.println("MOCK REVIEWS GENERATED");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        generateUsers();
        generateUserAddresses();
        generateReviews();
    }
}
