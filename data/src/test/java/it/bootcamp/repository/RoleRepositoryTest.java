package it.bootcamp.repository;

import it.bootcamp.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static it.bootcamp.Constants.ADMIN_ROLE;
import static it.bootcamp.Constants.CUSTOMER_ROLE;
import static it.bootcamp.Constants.SALE_ROLE;
import static it.bootcamp.Constants.SECURE_API_ROLE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
class RoleRepositoryTest {
    public static final String ROLE_NOT_CORRECT = "Название роли не корректно";
    @Autowired
    private RoleRepository roleRepository;

    @Test
    void findRoleByName() {
        Role admin = roleRepository.findRoleByName(ADMIN_ROLE);
        Role sale = roleRepository.findRoleByName(SALE_ROLE);
        Role customer = roleRepository.findRoleByName(CUSTOMER_ROLE);
        Role secApi = roleRepository.findRoleByName(SECURE_API_ROLE);

        assertAll(
                () -> assertNotNull(admin),
                () -> assertNotNull(sale),
                () -> assertNotNull(customer),
                () -> assertNotNull(secApi),
                () -> assertEquals(admin.getName(), ADMIN_ROLE, ROLE_NOT_CORRECT),
                () -> assertEquals(sale.getName(), SALE_ROLE, ROLE_NOT_CORRECT),
                () -> assertEquals(customer.getName(), CUSTOMER_ROLE, ROLE_NOT_CORRECT),
                () -> assertEquals(secApi.getName(), SECURE_API_ROLE, ROLE_NOT_CORRECT)
        );
    }
}