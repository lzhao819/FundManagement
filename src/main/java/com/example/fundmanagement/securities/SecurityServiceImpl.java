package com.example.fundmanagement.securities;

import com.example.fundmanagement.fund.FundAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SecurityServiceImpl {


    private final SecurityRepository securityRepository;

    @Autowired
    public SecurityServiceImpl(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    //findAll
    public List<Security> getAllSecurities(){return securityRepository.findAll();}

    //findCertainSecurity
    public Security findSecurity(int id) {
        if(securityRepository.existsById(id)) {
            return  securityRepository.findById(id).get();
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    //add
    @Transactional
    public void addSecurity(Security security) {
            Optional<Security> existingSecurity = securityRepository.findById(security.getSecurity_id());
            if (existingSecurity.isPresent()){
                throw new FundAlreadyExistsException(security.getSymbol());
            }
            securityRepository.save(security);

    }

    //remove
    @Transactional
    public void removeSecurity(int id) {
        if(securityRepository.existsById(id)) {
            securityRepository.deleteById(id);
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    //modifyDescription
    @Transactional
    public void modifyDescription(int id, String newSymbol) {
            Optional<Security> existingSecurity = securityRepository.findById(id);
            if (existingSecurity.isEmpty()) {
                throw new IllegalArgumentException();
            }

            Security oldSecurity = existingSecurity.get();

            if (oldSecurity.getSymbol() != null &&
                    !(oldSecurity.getSymbol().equals(newSymbol))  && newSymbol.length() > 0) {
                Optional<Security> securityByUpdatedName = securityRepository.findSecurityBySymbol(newSymbol);
                if (securityByUpdatedName.isPresent()) {
                    throw new FundAlreadyExistsException(newSymbol);
                }
                oldSecurity.setSymbol(newSymbol);
            }
    }
    public List<String>findSecurityByManagerId(Integer id){
        return securityRepository.findSecurityByManagerId(id);
    }


}
