package com.bootcamp.reviewservice.modules.useraddress.controller;


import com.bootcamp.reviewservice.BaseControllerTest;
import com.bootcamp.reviewservice.ReviewServiceApplication;
import com.bootcamp.reviewservice.modules.useraddress.dto.UserAddressSaveRequest;
import com.bootcamp.reviewservice.modules.useraddress.dto.UserAddressUpdateRequest;
import com.bootcamp.reviewservice.modules.useraddress.model.UserAddressType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {ReviewServiceApplication.class})
class UserAddressControllerTest extends BaseControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void shouldSave() throws Exception {
        UserAddressSaveRequest request = new UserAddressSaveRequest(
                "ev_adresi",
                "38.430690",
                "27.149312",
                "my_addressline",
                "14",
                "12",
                "23",
                UserAddressType.HOUSE,
                1L);

        String requestAsString = objectMapper.writeValueAsString(request);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user-addresses").content(requestAsString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldGetAllByUserId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user-addresses/by-user-id/1")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void getPreferredUserAddress() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user-addresses/preferred?userId=1")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldUpdate() throws Exception {
        Long id = 2L;
        UserAddressUpdateRequest request = new UserAddressUpdateRequest(
                id,
                "ev_adresi",
                "38.430690",
                "27.149312",
                "my_addressline",
                "14",
                "12",
                "23",
                UserAddressType.HOUSE
        );

        String requestAsString = objectMapper.writeValueAsString(request);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/user-addresses/2" + id).content(requestAsString).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        boolean success = isSuccess(mvcResult);

        assertTrue(success);
    }

    @Test
    void shouldDelete() throws Exception {
        mockMvc.perform(delete("/api/v1/user-addresses/3"))
                .andExpect(status().isNoContent());
    }

    @Test
    void changePreferredUserAddress() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/user-addresses/2/preferred?userId=1")).andExpect(status().isNoContent());

    }
}