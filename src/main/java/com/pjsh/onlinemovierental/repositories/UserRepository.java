package com.pjsh.onlinemovierental.repositories;

import com.pjsh.onlinemovierental.entities.users.AbstractUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AbstractUser, Long> {
    AbstractUser findByUsername(String username);
}
