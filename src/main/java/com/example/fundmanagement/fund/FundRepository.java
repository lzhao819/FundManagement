package com.example.fundmanagement.fund;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FundRepository extends JpaRepository<Fund, Integer> {

    //By default, Spring Data JPA will automatically parse the method name and create a query from it.
    Optional<Fund> findByName(String fundName);
    Optional<Fund> findFundByName(String fundName);

    //get某一种fund中各种security的数量
    @Query(value = "SELECT positions.`security_id`,SUM(positions.`quantity`)\n" +
            "    FROM positions\n" +
            "    WHERE positions.`fund_id` = ?1\n" +
            "    GROUP BY positions.`security_id`",nativeQuery = true)
    List<String> getSecurityQuant(Integer id);

}
