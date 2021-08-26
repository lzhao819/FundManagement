package com.example.fundmanagement.securities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SecurityRepository extends JpaRepository <Security,Integer>{
    Optional<Security> findSecurityBySymbol(String symbol);

    //return list of security id with total quantity
    @Query(value="select SUM(positions.`quantity`), positions.`security_id` from positions\n" +
            "where positions.`fund_id` \n" +
            "IN\n" +
            "(\n" +
            "select funds.`fund_id` from funds\n" +
            "where funds.`employee_id`=?1\n" +
            ")\n" +
            "GROUP BY positions.security_id\n" +
            ";",nativeQuery=true)
    List<String> findSecurityByManagerId(Integer id);

//    //return list of security id, date and total quantity
//    @Query()
//    List<String> getSecuritiesNameDateQuant(Integer id);
}

