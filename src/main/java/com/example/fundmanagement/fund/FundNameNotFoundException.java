package com.example.fundmanagement.fund;

public class FundNameNotFoundException extends IllegalArgumentException{

    private final String name;


    public FundNameNotFoundException(String name){
        super("Fund named " + name + " not found.");
        this.name = name;
    }

}
