package com.restaurant.management.service.impl;

import com.restaurant.management.dto.request.LoginRequest;
import com.restaurant.management.dto.response.LoginResponse;
import com.restaurant.management.exception.ExceptionMessages;
import com.restaurant.management.model.Client;
import com.restaurant.management.model.RestaurantOwner;
import com.restaurant.management.repository.UserRepository;
import com.restaurant.management.service.PasswordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AuthServiceImpl")
class AuthServiceImplTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordService passwordService;

    @InjectMocks
    private AuthServiceImpl authService;

    @Nested
    @DisplayName("quando o login é válido")
    class ValidLoginTests {

        @Test
        @DisplayName("deve retornar sucesso com dados do cliente")
        void shouldReturnSuccessForClient() {
            Client user = new Client();
            user.setId(1L);
            user.setLogin("joaosilva");
            user.setPassword("$2a$hashed");

            LoginRequest request = new LoginRequest("joaosilva", "senha123");
            when(repository.findByLogin("joaosilva")).thenReturn(Optional.of(user));
            when(passwordService.matches("senha123", "$2a$hashed")).thenReturn(true);

            LoginResponse response = authService.validateLogin(request);

            assertTrue(response.valid());
            assertEquals(ExceptionMessages.LOGIN_SUCCESS, response.message());
            assertEquals(1L, response.userId());
            assertEquals("Client", response.type());
        }

        @Test
        @DisplayName("deve retornar sucesso com dados do dono de restaurante")
        void shouldReturnSuccessForRestaurantOwner() {
            RestaurantOwner user = new RestaurantOwner();
            user.setId(2L);
            user.setLogin("dono");
            user.setPassword("$2a$hashed");

            LoginRequest request = new LoginRequest("dono", "senha123");
            when(repository.findByLogin("dono")).thenReturn(Optional.of(user));
            when(passwordService.matches("senha123", "$2a$hashed")).thenReturn(true);

            LoginResponse response = authService.validateLogin(request);

            assertTrue(response.valid());
            assertEquals(2L, response.userId());
            assertEquals("RestaurantOwner", response.type());
        }
    }

    @Nested
    @DisplayName("quando o login é inválido")
    class InvalidLoginTests {

        @Test
        @DisplayName("deve retornar falha quando o login não existir")
        void shouldReturnFailureWhenLoginNotFound() {
            LoginRequest request = new LoginRequest("inexistente", "senha123");
            when(repository.findByLogin("inexistente")).thenReturn(Optional.empty());

            LoginResponse response = authService.validateLogin(request);

            assertFalse(response.valid());
            assertEquals(ExceptionMessages.INVALID_LOGIN_CREDENTIALS, response.message());
            assertNull(response.userId());
            assertNull(response.type());
            verify(passwordService, never()).matches(anyString(), anyString());
        }

        @Test
        @DisplayName("deve retornar falha quando a senha estiver incorreta")
        void shouldReturnFailureWhenPasswordDoesNotMatch() {
            Client user = new Client();
            user.setId(1L);
            user.setLogin("joaosilva");
            user.setPassword("$2a$hashed");

            LoginRequest request = new LoginRequest("joaosilva", "senhaErrada");
            when(repository.findByLogin("joaosilva")).thenReturn(Optional.of(user));
            when(passwordService.matches("senhaErrada", "$2a$hashed")).thenReturn(false);

            LoginResponse response = authService.validateLogin(request);

            assertFalse(response.valid());
            assertEquals(ExceptionMessages.INVALID_LOGIN_CREDENTIALS, response.message());
            assertNull(response.userId());
            assertNull(response.type());
        }
    }
}
