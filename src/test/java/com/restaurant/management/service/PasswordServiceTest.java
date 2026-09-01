package com.restaurant.management.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PasswordService")
class PasswordServiceTest {

    private final PasswordService passwordService = new PasswordService(new BCryptPasswordEncoder());

    @Nested
    @DisplayName("quando a senha é validada")
    class MatchingTests {

        @Test
        @DisplayName("deve retornar true para senha válida e hash correspondente")
        void shouldReturnTrueWhenPasswordMatchesHash() {
            String rawPassword = "senha123";
            String encodedPassword = passwordService.hash(rawPassword);

            boolean matches = passwordService.matches(rawPassword, encodedPassword);

            assertTrue(matches);
        }

        @Test
        @DisplayName("deve retornar false quando a senha é nula")
        void shouldReturnFalseWhenRawPasswordIsNull() {
            String encodedPassword = passwordService.hash("senha123");

            boolean matches = passwordService.matches(null, encodedPassword);

            assertFalse(matches);
        }

        @Test
        @DisplayName("deve retornar false quando a senha é em branco")
        void shouldReturnFalseWhenRawPasswordIsBlank() {
            String encodedPassword = passwordService.hash("senha123");

            boolean matches = passwordService.matches("   ", encodedPassword);

            assertFalse(matches);
        }

        @Test
        @DisplayName("deve retornar false quando o hash é vazio ou em branco")
        void shouldReturnFalseWhenEncodedPasswordIsBlank() {
            boolean matches = passwordService.matches("senha123", "   ");

            assertFalse(matches);
        }

        @Test
        @DisplayName("deve retornar false quando a senha não corresponde ao hash")
        void shouldReturnFalseWhenPasswordDoesNotMatchHash() {
            String encodedPassword = passwordService.hash("senha123");

            boolean matches = passwordService.matches("outraSenha", encodedPassword);

            assertFalse(matches);
        }

        @Test
        @DisplayName("deve retornar false quando o hash armazenado for inválido")
        void shouldReturnFalseWhenEncodedPasswordIsInvalid() {
            boolean matches = passwordService.matches("senha123", "hash-invalido");

            assertFalse(matches);
        }
    }

    @Nested
    @DisplayName("quando o hash é gerado")
    class HashingTests {

        @Test
        @DisplayName("deve gerar um hash diferente da senha em texto puro")
        void shouldGenerateHashDifferentFromPlainPassword() {
            String rawPassword = "senha123";

            String hashedPassword = passwordService.hash(rawPassword);

            assertNotNull(hashedPassword);
            assertNotEquals(rawPassword, hashedPassword);
            assertTrue(hashedPassword.startsWith("$2a$") || hashedPassword.startsWith("$2b$") || hashedPassword.startsWith("$2y$"));
        }
    }
}
