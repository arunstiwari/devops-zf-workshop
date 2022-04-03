package com.tekmentors.javareferenceprj.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tekmentors.javareferenceprj.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MimeType;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    public  void setup() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User USER1 = new User("User1", 35, "MALE");
        User USER2 = new User("User2", 45, "FEMALE");
        User USER3 = new User("User3", 55, "MALE");

        String user = mapper.writeValueAsString(USER1);
        String user2 = mapper.writeValueAsString(USER2);
        String user3 = mapper.writeValueAsString(USER3);

        mockMvc.perform(
                        post("/users/")
                                .content(user)
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
        ;
        mockMvc.perform(
                        post("/users/")
                                .content(user2)
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
        ;
        mockMvc.perform(
                        post("/users/")
                                .content(user3)
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
        ;
    }

    @Test
    void fetchUsers() throws Exception {
        setup();
        mockMvc.perform(get("/users/"))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$.[0].name", is("User4")))
        ;
    }

    @Test
    void findUser() throws Exception {
        assertEquals(1,1);
    }

    @Test
    void createNewUser() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User USER1 = new User("User4", 25, "MALE");

        String user = mapper.writeValueAsString(USER1);

        mockMvc.perform(
                        post("/users/")
                                .content(user)
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("User4")))
        ;

        ;
    }
}