package com.carcompany.web_project.repos;

import com.carcompany.web_project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
