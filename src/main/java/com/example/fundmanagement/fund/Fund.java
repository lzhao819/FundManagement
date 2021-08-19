package com.example.fundmanagement.fund;


import com.example.fundmanagement.manager.Manager;
import com.example.fundmanagement.positions.Positions;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

//test
@Entity
@Table(name = "funds")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "fund_id")
public class Fund {
    @Id
    private Integer fund_id;

    private String name;

    private Integer employee_id;

    @ManyToOne(optional = false,cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", insertable=false, updatable=false)
    @JsonIdentityReference(alwaysAsId = true)
    private Manager managerInFund;

    @OneToMany(mappedBy = "fundInPosition")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Positions> positions;

    public Fund() {
    }

    public Fund(Integer fund_id, String name, Integer employee_id, Manager managerInFund, List<Positions> positions) {
        this.fund_id = fund_id;
        this.name = name;
        this.employee_id = employee_id;
        this.managerInFund = managerInFund;
        this.positions = positions;
    }

    public Integer getFund_id() {
        return fund_id;
    }

    public void setFund_id(Integer fund_id) {
        this.fund_id = fund_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public Manager getManagerInFund() {
        return managerInFund;
    }

    public void setManagerInFund(Manager managerInFund) {
        this.managerInFund = managerInFund;
    }

    public List<Positions> getPositions() {
        return positions;
    }

    public void setPositions(List<Positions> positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "Fund{" +
                "fund_id=" + fund_id +
                ", name='" + name + '\'' +
                ", employee_id=" + employee_id +
                ", managerInFund=" + managerInFund +
                ", positions=" + positions +
                '}';
    }
}
