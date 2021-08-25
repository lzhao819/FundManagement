package com.example.fundmanagement.fund;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FundRepository extends JpaRepository<Fund, Integer> {

    //By default, Spring Data JPA will automatically parse the method name and create a query from it.
    Optional<Fund> findByName(String fundName);
    Optional<Fund> findFundByName(String fundName);
}
