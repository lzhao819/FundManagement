package com.example.fundmanagement.securities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SecurityRepository extends JpaRepository <Security,Integer>{
    Optional<Security> findSecurityBySymbol(String symbol);

//    //return list of security id, date and total quantity
//    @Query()
//    List<String> getSecuritiesNameDateQuant(Integer id);

}

