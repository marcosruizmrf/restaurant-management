package com.restaurant.management.factory;

import com.restaurant.management.dto.request.AddressRequest;
import com.restaurant.management.dto.request.CreateUserRequest;
import com.restaurant.management.enums.UserType;
import com.restaurant.management.model.Address;
import com.restaurant.management.model.Client;
import com.restaurant.management.model.RestaurantOwner;
import com.restaurant.management.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserFactory")
class UserFactoryTest {

    private final UserFactory userFactory = new UserFactory();

    @Nested
    @DisplayName("quando cria uma entidade")
    class CreateEntityTests {

        @Test
        @DisplayName("deve criar um cliente com os dados do request")
        void shouldCreateClientFromRequest() {
            CreateUserRequest request = new CreateUserRequest(
                    "João Silva",
                    "joao@email.com",
                    "joaosilva",
                    "senha123",
                    UserType.CLIENT,
                    new AddressRequest("Avenida Paulista", "1000", "São Paulo", "01310-100")
            );

            User user = userFactory.createEntity(request);

            assertInstanceOf(Client.class, user);
            assertEquals("João Silva", user.getName());
            assertEquals("joao@email.com", user.getEmail());
            assertEquals("joaosilva", user.getLogin());
            assertNull(user.getPassword());
            assertNotNull(user.getLastChange());
            assertNotNull(user.getAddress());
            assertEquals("Avenida Paulista", user.getAddress().getStreet());
            assertEquals("1000", user.getAddress().getNumber());
            assertEquals("São Paulo", user.getAddress().getCity());
            assertEquals("01310-100", user.getAddress().getZipCode());
        }

        @Test
        @DisplayName("deve criar um dono de restaurante com os dados do request")
        void shouldCreateRestaurantOwnerFromRequest() {
            CreateUserRequest request = new CreateUserRequest(
                    "Maria Souza",
                    "maria@email.com",
                    "mariasouza",
                    "senha123",
                    UserType.RESTAURANT_OWNER,
                    null
            );

            User user = userFactory.createEntity(request);

            assertInstanceOf(RestaurantOwner.class, user);
            assertEquals("Maria Souza", user.getName());
            assertEquals("maria@email.com", user.getEmail());
            assertEquals("mariasouza", user.getLogin());
            assertNull(user.getAddress());
            assertNotNull(user.getLastChange());
        }
    }

    @Nested
    @DisplayName("quando converte endereço")
    class ToAddressTests {

        @Test
        @DisplayName("deve retornar null quando o request do endereço for nulo")
        void shouldReturnNullWhenAddressRequestIsNull() {
            assertNull(userFactory.toAddress(null));
        }

        @Test
        @DisplayName("deve mapear os campos do request para o modelo de endereço")
        void shouldMapAddressRequestToAddressEntity() {
            AddressRequest request = new AddressRequest("Rua das Flores", "42", "Campinas", "13000-000");

            Address address = userFactory.toAddress(request);

            assertNotNull(address);
            assertEquals("Rua das Flores", address.getStreet());
            assertEquals("42", address.getNumber());
            assertEquals("Campinas", address.getCity());
            assertEquals("13000-000", address.getZipCode());
        }
    }
}
