package com.bootcamp.reviewservice.modules.useraddress.service;

import com.bootcamp.reviewservice.advice.responseexceptionhandler.exceptions.ItemNotFoundException;
import com.bootcamp.reviewservice.modules.user.model.User;
import com.bootcamp.reviewservice.modules.user.service.UserService;
import com.bootcamp.reviewservice.modules.useraddress.dto.UserAddressResponse;
import com.bootcamp.reviewservice.modules.useraddress.dto.UserAddressSaveRequest;
import com.bootcamp.reviewservice.modules.useraddress.dto.UserAddressUpdateRequest;
import com.bootcamp.reviewservice.modules.useraddress.model.UserAddress;
import com.bootcamp.reviewservice.modules.useraddress.model.UserAddressType;
import com.bootcamp.reviewservice.modules.useraddress.repository.UserAddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAddressServiceTest {

    @Mock
    private UserAddressRepository mockRepository;
    @Mock
    private UserService mockUserService;

    private UserAddressService userAddressServiceUnderTest;

    @BeforeEach
    void setUp() {
        userAddressServiceUnderTest = new UserAddressService(mockRepository, mockUserService);
    }

    @Test
    void shouldSave() {
        UserAddressSaveRequest saveRequest = new UserAddressSaveRequest("name", "latitude", "longitude",
                "addressLine", "buildingNumber", "floor", "apartmentNumber", UserAddressType.HOUSE, 0L);
        UserAddressResponse expectedResult = new UserAddressResponse(0L, "name", "latitude", "longitude",
                "addressLine", "buildingNumber", "floor", "apartmentNumber", false, UserAddressType.HOUSE,
                LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        User user = new User(0L, "username", "name", "surname");
        when(mockUserService.findUserById(0L)).thenReturn(user);

        UserAddress userAddress = new UserAddress();
        userAddress.setId(0L);
        userAddress.setName("name");
        userAddress.setLatitude("latitude");
        userAddress.setIsPreferred(false);
        User user1 = new User();
        userAddress.setUser(user1);
        when(mockRepository.save(any(UserAddress.class))).thenReturn(userAddress);

        UserAddress userAddress1 = new UserAddress();
        userAddress1.setId(0L);
        userAddress1.setName("name");
        userAddress1.setLatitude("latitude");
        userAddress1.setIsPreferred(false);
        User user2 = new User();
        userAddress1.setUser(user2);
        List<UserAddress> userAddresses = List.of(userAddress1);
        when(mockRepository.findAllByUserId(0L)).thenReturn(userAddresses);

        UserAddressResponse result = userAddressServiceUnderTest.save(saveRequest);

        assertThat(result).isEqualTo(expectedResult);

        UserAddress userAddress2 = new UserAddress();
        userAddress2.setId(0L);
        userAddress2.setName("name");
        userAddress2.setLatitude("latitude");
        userAddress2.setIsPreferred(false);
        User user3 = new User();
        userAddress2.setUser(user3);
        List<UserAddress> entities = List.of(userAddress2);
        verify(mockRepository).saveAll(entities);
    }

    @Test
    void shouldSave_UserAddressRepositoryFindAllByUserIdReturnsNoItems() {
        UserAddressSaveRequest saveRequest = new UserAddressSaveRequest("name", "latitude", "longitude",
                "addressLine", "buildingNumber", "floor", "apartmentNumber", UserAddressType.HOUSE, 0L);
        UserAddressResponse expectedResult = new UserAddressResponse(0L, "name", "latitude", "longitude",
                "addressLine", "buildingNumber", "floor", "apartmentNumber", false, UserAddressType.HOUSE,
                LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        User user = new User(0L, "username", "name", "surname");
        when(mockUserService.findUserById(0L)).thenReturn(user);

        UserAddress userAddress = new UserAddress();
        userAddress.setId(0L);
        userAddress.setName("name");
        userAddress.setLatitude("latitude");
        userAddress.setIsPreferred(false);
        User user1 = new User();
        userAddress.setUser(user1);
        when(mockRepository.save(any(UserAddress.class))).thenReturn(userAddress);

        when(mockRepository.findAllByUserId(0L)).thenReturn(Collections.emptyList());

        UserAddressResponse result = userAddressServiceUnderTest.save(saveRequest);

        assertThat(result).isEqualTo(expectedResult);

        UserAddress userAddress1 = new UserAddress();
        userAddress1.setId(0L);
        userAddress1.setName("name");
        userAddress1.setLatitude("latitude");
        userAddress1.setIsPreferred(false);
        User user2 = new User();
        userAddress1.setUser(user2);
        List<UserAddress> entities = List.of(userAddress1);
        verify(mockRepository).saveAll(entities);
    }

    @Test
    void shouldFindAllByUserId() {
        List<UserAddressResponse> expectedResult = List.of(
                new UserAddressResponse(0L, "name", "latitude", "longitude", "addressLine", "buildingNumber", "floor",
                        "apartmentNumber", false, UserAddressType.HOUSE, LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                        LocalDateTime.of(2020, 1, 1, 0, 0, 0)));

        UserAddress userAddress = new UserAddress();
        userAddress.setId(0L);
        userAddress.setName("name");
        userAddress.setLatitude("latitude");
        userAddress.setIsPreferred(false);
        User user = new User();
        userAddress.setUser(user);
        List<UserAddress> userAddresses = List.of(userAddress);
        when(mockRepository.findAllByUserId(0L)).thenReturn(userAddresses);

        List<UserAddressResponse> result = userAddressServiceUnderTest.findAllByUserId(0L);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldFindAllByUserId_UserAddressRepositoryReturnsNoItems() {
        when(mockRepository.findAllByUserId(0L)).thenReturn(Collections.emptyList());

        List<UserAddressResponse> result = userAddressServiceUnderTest.findAllByUserId(0L);

        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void shouldGetPreferredUserAddress() {
        UserAddressResponse expectedResult = new UserAddressResponse(0L, "name", "latitude", "longitude",
                "addressLine", "buildingNumber", "floor", "apartmentNumber", false, UserAddressType.HOUSE,
                LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        UserAddress userAddress1 = new UserAddress();
        userAddress1.setId(0L);
        userAddress1.setName("name");
        userAddress1.setLatitude("latitude");
        userAddress1.setIsPreferred(false);
        User user = new User();
        userAddress1.setUser(user);
        Optional<UserAddress> userAddress = Optional.of(userAddress1);
        when(mockRepository.findByUserIdAndIsPreferredTrue(0L)).thenReturn(userAddress);

        UserAddressResponse result = userAddressServiceUnderTest.getPreferredUserAddress(0L);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldGetPreferredUserAddress_UserAddressRepositoryReturnsAbsent() {
        when(mockRepository.findByUserIdAndIsPreferredTrue(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userAddressServiceUnderTest.getPreferredUserAddress(0L))
                .isInstanceOf(ItemNotFoundException.class);
    }

    @Test
    void shouldDelete() {
        UserAddress userAddress1 = new UserAddress();
        userAddress1.setId(0L);
        userAddress1.setName("name");
        userAddress1.setLatitude("latitude");
        userAddress1.setIsPreferred(false);
        User user = new User();
        userAddress1.setUser(user);
        Optional<UserAddress> userAddress = Optional.of(userAddress1);
        when(mockRepository.findById(0L)).thenReturn(userAddress);

        userAddressServiceUnderTest.delete(0L);

        verify(mockRepository).delete(any(UserAddress.class));
    }

    @Test
    void shouldDelete_UserAddressRepositoryFindByIdReturnsAbsent() {
        when(mockRepository.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userAddressServiceUnderTest.delete(0L)).isInstanceOf(ItemNotFoundException.class);
    }


    @Test
    void shouldUpdate_UserAddressRepositoryFindByIdReturnsAbsent() {
        UserAddressUpdateRequest updateRequest = new UserAddressUpdateRequest(0L, "name", "latitude", "longitude",
                "addressLine", "buildingNumber", "floor", "apartmentNumber", UserAddressType.HOUSE);
        when(mockRepository.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userAddressServiceUnderTest.update(updateRequest))
                .isInstanceOf(ItemNotFoundException.class);
    }


    @Test
    void shouldFindUserAddressById() {
        UserAddress userAddress1 = new UserAddress();
        userAddress1.setId(0L);
        userAddress1.setName("name");
        userAddress1.setLatitude("latitude");
        userAddress1.setIsPreferred(false);
        User user = new User();
        userAddress1.setUser(user);
        Optional<UserAddress> userAddress = Optional.of(userAddress1);
        when(mockRepository.findById(0L)).thenReturn(userAddress);

        UserAddress result = userAddressServiceUnderTest.findUserAddressById(0L);
    }

    @Test
    void shouldFindUserAddressById_UserAddressRepositoryReturnsAbsent() {
        when(mockRepository.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userAddressServiceUnderTest.findUserAddressById(0L))
                .isInstanceOf(ItemNotFoundException.class);
    }
}