package com.carcompany.web_project.repos;

import com.carcompany.web_project.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
