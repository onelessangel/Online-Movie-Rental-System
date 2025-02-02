package com.pjsh.onlinemovierental.repositories;

import com.pjsh.onlinemovierental.entities.users.AbstractUser;
import com.pjsh.onlinemovierental.entities.users.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<AbstractUser, Long> {
    AbstractUser findByUsername(String username);
    @Query("SELECT c FROM Customer c")
    List<Customer> findAllCustomers();
}
