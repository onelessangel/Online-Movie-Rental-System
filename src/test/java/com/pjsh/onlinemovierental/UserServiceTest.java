package com.pjsh.onlinemovierental;

import com.pjsh.onlinemovierental.entities.users.Customer;
import com.pjsh.onlinemovierental.repositories.UserRepository;
import com.pjsh.onlinemovierental.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private Customer testCustomer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testCustomer = new Customer("test", "test", "test", "test");
    }

    @Test
    void testGetUser() {
        when(userRepository.findByUsername("test")).thenReturn(testCustomer);

        Customer customer = (Customer) userService.getUser("test");

        assertNotNull(customer);
        assertEquals(testCustomer, customer);
    }

    @Test
    void testCreateCustomer() {
        when(userRepository.save(any(Customer.class))).thenReturn(testCustomer);

        String result = userService.createCustomer("test", "test", "test");

        assertEquals("Created new customer", result);
        verify(userRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void testGetAllCustomers() {
        when(userRepository.findAllCustomers()).thenReturn(List.of(testCustomer, testCustomer, testCustomer));

        List<Customer> customers = userService.getAllCustomers();

        assertEquals(3, customers.size());
        verify(userRepository, times(1)).findAllCustomers();
    }
}
