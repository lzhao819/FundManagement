package com.example.fundmanagement.manager;


import com.example.fundmanagement.fund.Fund;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

    Optional<Manager> findManagerByFirstName(String name);

    //计算某个manager名下所有fund的数量
    @Query(value = "SELECT COUNT(funds.`name`)\n" +
            "FROM funds\n" +
            "WHERE funds.`employee_id` =?1",nativeQuery = true)
    Integer getFundQuant(Integer id);

    //计算某个manager名下一共有多少种security
    @Query(value = "SELECT COUNT(positions.`security_id`) from positions\n" +
            "    WHERE positions.`fund_id`\n" +
            "    IN\n" +
            "            (\n" +
            "                    select funds.`fund_id` from funds\n" +
            "                    where funds.`employee_id`=?1\n" +
            "            )",nativeQuery = true)
    Integer getSecurityQuant(Integer id);

    //某个manager持有的list of security id with total quantity
    @Query(value="select positions.`security_id`, SUM(positions.`quantity`)  from positions\n" +
            "where positions.`fund_id` \n" +
            "IN\n" +
            "(\n" +
            "select funds.`fund_id` from funds\n" +
            "where funds.`employee_id`=?1\n" +
            ")\n" +
            "GROUP BY positions.security_id\n" +
            ";",nativeQuery=true)
    List<String> getSecurityQuantList(Integer id);


    //某个manager每天购买的各种security的数量
    @Query(value = "select positions.`date_purchased`, SUM(positions.`quantity`), positions.`security_id` from positions\n" +
            "where positions.`fund_id` \n" +
            "IN\n" +
            "(\n" +
            "select funds.`fund_id` from funds\n" +
            "where funds.`employee_id`=?1\n" +
            ")\n" +
            "GROUP BY positions.security_id,positions.`date_purchased`;",nativeQuery = true)
    List<String> getSecurityQuantDateList(Integer id);
}
