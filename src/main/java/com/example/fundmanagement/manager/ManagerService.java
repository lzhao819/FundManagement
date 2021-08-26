package com.example.fundmanagement.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ManagerService{
    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public List<Manager> getManagers(){
        return managerRepository.findAll();
    }

    public void addManager(Manager newManager){
        Optional<Manager> existingFund = managerRepository.findById(newManager.getEmployee_id());
        if (existingFund.isPresent()){
            throw new ManagerAlreadyExistsException(newManager.getFirstName());
        }
        managerRepository.save(newManager);
    }

    public void deleteManager(Integer id) {
        if (managerRepository.existsById(id)) {
            managerRepository.deleteById(id);
        } else {
            throw new ManagerNotFoundException(id);
        }
    }

    public Manager getManager(Integer id){
        Optional<Manager> existingManager = managerRepository.findById(id);
        if (existingManager.isEmpty()){
            throw new ManagerNotFoundException(id);
        }
        return existingManager.get();
    }

    @Transactional
    public void updateManager(Integer id, Manager newManager) {
        Optional<Manager> existingManager = managerRepository.findById(id);
        if (existingManager.isEmpty()) {
            throw new ManagerNotFoundException(id);
        }

        Manager oldManager = existingManager.get();

        if (oldManager.getEmployee_id() != newManager.getEmployee_id()) {
            throw new IllegalStateException("Fund ID in the path variable is different with the ID in the request body");
        }

        if (newManager.getFirstName() != null &&
                oldManager.getFirstName() != newManager.getFirstName() && newManager.getFirstName().length() > 0) {
            Optional<Manager> fundByUpdatedName = managerRepository.findManagerByFirstName(newManager.getFirstName());
            if (fundByUpdatedName.isPresent()) {
                throw new ManagerAlreadyExistsException(newManager.getFirstName());
            }
            oldManager.setFirstName(newManager.getFirstName());
            oldManager.setLastName(newManager.getLastName());
        }
    }

    public Integer getFundQuant(Integer id) {
        return managerRepository.getFundQuant(id);
    }

    public Integer getSecurityQuant(Integer id) {
        return managerRepository.getSecurityQuant(id);
    }

    public List<String> getSecurityQuantList(Integer id) {
        return managerRepository.getSecurityQuantList(id);
    }

    public List<String> getSecurityQuantDateList(Integer id) {
        return managerRepository.getSecurityQuantDateList(id);
    }
}
