package com.example.fundmanagement.positions;


import com.example.fundmanagement.fund.Fund;
import com.example.fundmanagement.securities.Security;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "positions")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "position_id")
public class Positions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer position_id;
    private Integer quantity;
    private LocalDate date_purchased;

    @Column(name="fund_id")
    private Integer funds_fund_id;

    @Column(name="security_id")
    private Integer security_id;

    @ManyToOne(optional = false,cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name="security_id",insertable = false,updatable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private Security securityInPosition;

    @ManyToOne(optional = false,cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name="fund_id",insertable=false ,updatable=false)
    @JsonIdentityReference(alwaysAsId = true)
    private Fund fundInPosition;

    public Positions() {
    }

    public Positions(Integer position_id, Integer quantity, LocalDate date_purchased, Integer funds_fund_id,Integer security_id, Security securityInPosition, Fund fundInPosition) {
        this.position_id = position_id;
        this.quantity = quantity;
        this.date_purchased = date_purchased;
        this.funds_fund_id = funds_fund_id;
        this.security_id =security_id;
        this.securityInPosition=securityInPosition;
        this.fundInPosition=fundInPosition;
    }

    public Positions(Integer position_id, Integer quantity, LocalDate date_purchased, Integer funds_fund_id, Integer security_id) {
        this.position_id = position_id;
        this.quantity = quantity;
        this.date_purchased = date_purchased;
        this.funds_fund_id = funds_fund_id;
        this.security_id = security_id;
    }

    public Positions(Integer quantity, LocalDate date_purchased, Integer funds_fund_id,Security securityInPosition) {
        this.quantity = quantity;
        this.date_purchased = date_purchased;
        this.funds_fund_id = funds_fund_id;
        this.securityInPosition=securityInPosition;
    }

    public Positions(Integer position_id, Integer quantity, LocalDate date_purchased, Integer funds_fund_id) {
        this.position_id = position_id;
        this.quantity = quantity;
        this.date_purchased = date_purchased;
        this.funds_fund_id = funds_fund_id;
    }

    public Integer getSecurity_id() {
        return security_id;
    }

    public void setSecurity_id(Integer security_id) {
        this.security_id = security_id;
    }

    public Integer getPosition_id() {
        return position_id;
    }

    public void setPosition_id(Integer position_id) {
        this.position_id = position_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate_purchased() {
        return date_purchased;
    }

    public void setDate_purchased(LocalDate date_purchased) {
        this.date_purchased = date_purchased;
    }

    public Integer getFunds_fund_id() {
        return funds_fund_id;
    }

    public void setFunds_fund_id(Integer funds_fund_id) {
        this.funds_fund_id = funds_fund_id;
    }

    public Security getSecurityInPosition() {
        return securityInPosition;
    }

    public void setSecurityInPosition(Security securityInPosition) {
        this.securityInPosition = securityInPosition;
    }

    public Fund getFundInPosition() {
        return fundInPosition;
    }

    public void setFundInPosition(Fund fundInPosition) {
        this.fundInPosition = fundInPosition;
    }

    @Override
    public String toString() {
        return "Positions{" +
                "position_id=" + position_id +
                ", quantity=" + quantity +
                ", date_purchased=" + date_purchased +
                ", funds_fund_id=" + funds_fund_id +
                ", security_id=" + security_id +
                ", securityInPosition=" + securityInPosition +
                ", fundInPosition=" + fundInPosition +
                '}';
    }
}
