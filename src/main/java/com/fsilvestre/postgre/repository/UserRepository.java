package com.fsilvestre.postgre.repository;

import com.fsilvestre.postgre.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
