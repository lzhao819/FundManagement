package com.example.fundmanagement.fund;

import com.example.fundmanagement.securities.SecurityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import com.example.fundmanagement.securities.SecurityRestController;

@Service
public class FundService {
    private final FundRepository fundRepository;
    @Autowired
    private SecurityServiceImpl securityService;

    @Autowired
    public FundService(FundRepository fundRepository) {
        this.fundRepository = fundRepository;
    }

    public List<Fund> getFunds(){
        return fundRepository.findAll();
    }

    public Fund getFund(Integer id){
        Optional<Fund> fund = fundRepository.findById(id);
        if (fund.isEmpty()) {
            throw new FundNotFoundException(id);
        }
        return fund.get();
    }

    public Fund getByName(String fundName) {
        Optional<Fund> fund = fundRepository.findByName(fundName);
        if (fund.isEmpty()) {
            throw new FundNameNotFoundException(fundName);
        }
        return fund.get();
    }

    public void addFund(Fund newFund){
        Optional<Fund> existingFund = fundRepository.findById(newFund.getFund_id());
        if (existingFund.isPresent()){
            throw new FundAlreadyExistsException(newFund.getName());
        }
        fundRepository.save(newFund);
    }

    public void deleteFund(Integer id) {
        if(fundRepository.existsById(id)) {
            fundRepository.deleteById(id);
        }
        else{
            throw new FundNotFoundException(id);
        }
    }

    @Transactional
    public void updateFund(Integer id, Fund newFund){
        Optional<Fund> existingFund = fundRepository.findById(id);
        if (existingFund.isEmpty()) {
            throw new FundNotFoundException(id);
        }

        Fund oldFund = existingFund.get();

        if (oldFund.getFund_id() != newFund.getFund_id()){
            throw new IllegalStateException("Fund ID in the path variable is different with the ID in the request body");
        }

        if (newFund.getName() != null &&
                oldFund.getName() != newFund.getName() && newFund.getName().length() > 0) {
            Optional<Fund> fundByUpdatedName = fundRepository.findFundByName(newFund.getName());
            if (fundByUpdatedName.isPresent()) {
                throw new FundAlreadyExistsException(newFund.getName());
            }
            oldFund.setName(newFund.getName());
        }

    }

    public List<String> getSecurityQuant(Integer id) {
        List<String> list = fundRepository.getSecurityQuant(id);
        for(int i=0;i<list.size();i++){
            String s = list.get(i);
            String[] column = s.split(",");
            //c[0] -- id
            column[0] = securityService.findSecurity(Integer.parseInt(column[0])).getSymbol();
            String withName = String.join(",",column);
            list.set(i,withName);
        }
        return list;
    }
}
