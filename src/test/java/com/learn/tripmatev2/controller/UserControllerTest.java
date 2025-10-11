package com.learn.tripmatev2.controller;

import com.learn.tripmatev2.model.User;
import com.learn.tripmatev2.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setEmail("test@example.com");
        testUser.setFullName("Test User");
    }

    @Test
    @WithMockUser
    void testGetAll() throws Exception {
        // Arrange
        User user2 = new User();
        user2.setId(2L);
        user2.setEmail("test2@example.com");
        when(userService.getAll()).thenReturn(Arrays.asList(testUser, user2));

        // Act & Assert
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].email").value("test@example.com"));

        verify(userService, times(1)).getAll();
    }

    @Test
    @WithMockUser
    void testGetById_Found() throws Exception {
        // Arrange
        when(userService.getById(1L)).thenReturn(Optional.of(testUser));

        // Act & Assert
        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("test@example.com"));

        verify(userService, times(1)).getById(1L);
    }

    @Test
    @WithMockUser
    void testGetById_NotFound() throws Exception {
        // Arrange
        when(userService.getById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/api/users/999"))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).getById(999L);
    }

    @Test
    @WithMockUser
    void testCreate() throws Exception {
        // Arrange
        when(userService.save(any(User.class))).thenReturn(testUser);

        // Act & Assert
        mockMvc.perform(post("/api/users")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"));

        verify(userService, times(1)).save(any(User.class));
    }

    @Test
    @WithMockUser
    void testUpdate_Found() throws Exception {
        // Arrange
        when(userService.getById(1L)).thenReturn(Optional.of(testUser));
        when(userService.save(any(User.class))).thenReturn(testUser);

        // Act & Assert
        mockMvc.perform(put("/api/users/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isOk());

        verify(userService, times(1)).getById(1L);
        verify(userService, times(1)).save(any(User.class));
    }

    @Test
    @WithMockUser
    void testUpdate_NotFound() throws Exception {
        // Arrange
        when(userService.getById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(put("/api/users/999")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).getById(999L);
        verify(userService, never()).save(any(User.class));
    }

    @Test
    @WithMockUser
    void testDelete_Found() throws Exception {
        // Arrange
        when(userService.getById(1L)).thenReturn(Optional.of(testUser));
        doNothing().when(userService).delete(1L);

        // Act & Assert
        mockMvc.perform(delete("/api/users/1")
                        .with(csrf()))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).getById(1L);
        verify(userService, times(1)).delete(1L);
    }

    @Test
    @WithMockUser
    void testDelete_NotFound() throws Exception {
        // Arrange
        when(userService.getById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(delete("/api/users/999")
                        .with(csrf()))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).getById(999L);
        verify(userService, never()).delete(anyLong());
    }
}
