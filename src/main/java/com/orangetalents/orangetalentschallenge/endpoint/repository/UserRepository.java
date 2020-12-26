package com.orangetalents.orangetalentschallenge.endpoint.repository;

import com.orangetalents.orangetalentschallenge.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
