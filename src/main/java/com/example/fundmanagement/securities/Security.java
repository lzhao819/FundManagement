package com.example.fundmanagement.securities;

import com.example.fundmanagement.positions.Positions;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="securities")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "security_id")
public class Security {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer security_id;

    private String symbol;

    @OneToMany(mappedBy = "securityInPosition",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Positions> positions;

    //constructors
    public Security(Integer security_id, String symbol,List<Positions> positions) {
        this.security_id = security_id;
        this.symbol = symbol;
        this.positions = positions;
    }

    public Security(Integer security_id, String symbol) {
        this.security_id = security_id;
        this.symbol = symbol;
    }

    public Security(String symbol) {
        this.symbol = symbol;
    }

    public List<Positions> getPositions() {
        return positions;
    }

    public void setPositions(List<Positions> positions) {
        this.positions = positions;
    }

    public Security(){}

    public Integer getSecurity_id() {
        return security_id;
    }

    public void setSecurity_id(Integer security_id) {
        this.security_id = security_id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        List position_id = new ArrayList();
        for (Positions position:positions){
            position_id.add(position.getPosition_id());
        }
        return "{\"security_id\":" + security_id +
                ",\"symbol\":" +"\"" +symbol +"\""+
                ",\"positions\":"+ position_id.toString().replace(" ","")+
                '}';
    }
}
