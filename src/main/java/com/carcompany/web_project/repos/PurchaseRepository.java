package com.carcompany.web_project.repos;

import com.carcompany.web_project.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
