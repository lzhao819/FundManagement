package com.example.fundmanagement.manager;

import com.example.fundmanagement.fund.Fund;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "fund_managers")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "employee_id")
public class Manager {
    @Id
    @Column(name = "employee_id")
    private Integer employee_id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "managerInFund")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Fund> funds;

    public Manager() {
    }

    public Manager(Integer employee_id, String firstName, String lastName, List<Fund> funds) {
        this.employee_id = employee_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.funds = funds;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Fund> getFunds() {
        return funds;
    }

    public void setFunds(List<Fund> funds) {
        this.funds = funds;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "employee_id=" + employee_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", funds=" + funds +
                '}';
    }
}
