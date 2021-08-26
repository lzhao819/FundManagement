package com.example.fundmanagement.fund;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funds")
public class FundController {
    private final FundService fundService;

    @Autowired
    public FundController(FundService fundService) {
        this.fundService = fundService;
    }

    @GetMapping
    public List<Fund> getFunds(){
        return fundService.getFunds();
    }

    @GetMapping(path="{fundId}")
    public Fund getFund(@PathVariable("fundId") Integer id){
        return fundService.getFund(id);
    }

    //getByName
    @GetMapping(path="/name/{fundName}")
    public Fund getByName(@PathVariable("fundName") String fundName){
        return fundService.getByName(fundName);
    }

    @PostMapping
    public void registerNewFund(@RequestBody Fund fund){
        fundService.addFund(fund);
    }

    @DeleteMapping(path="{fundId}")
    public void deleteFund(@PathVariable("fundId") Integer id){
        fundService.deleteFund(id);
    }

    @PutMapping(path = "{fundId}")
    public void updateFund(@PathVariable Integer fundId, @RequestBody Fund newFund){
        fundService.updateFund(fundId, newFund);
    }

    //Get security and quantity in a certain fund
    @GetMapping(path="/{fundId}/security")
    public List<String> getSecurityQuant(@PathVariable("fundId") Integer fundId){
        return fundService.getSecurityQuant(fundId);
    }









}
