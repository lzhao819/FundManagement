package com.example.fundmanagement.fund;

public class FundNotFoundException extends IllegalArgumentException{

    private final Integer id;


    public FundNotFoundException(Integer id){
        super("Fund with id " + id + "not found.");
        this.id = id;
    }

}
