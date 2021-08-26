package com.example.fundmanagement.positions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionsRepository extends JpaRepository<Positions, Integer> {

//    @Query(value= "DELETE FROM positions WHERE positions.`positions_id` = ?1", nativeQuery = true)
//    getSecurityQuant(Integer id);

}
