package com.pjsh.onlinemovierental.services;

import com.pjsh.onlinemovierental.entities.users.AbstractUser;
import com.pjsh.onlinemovierental.entities.users.Customer;
import com.pjsh.onlinemovierental.enums.MembershipType;
import com.pjsh.onlinemovierental.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AbstractUser getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public String createCustomer(String username, String email, String password) {
        Customer customer = new Customer(username, email, password, MembershipType.BASIC.toString());
        userRepository.save(customer);
        return "Created new customer";
    }

    public List<Customer> getAllCustomers() {
        return userRepository.findAllCustomers().stream().sorted(Comparator.comparing(AbstractUser::getUsername)).toList();
    }
}
