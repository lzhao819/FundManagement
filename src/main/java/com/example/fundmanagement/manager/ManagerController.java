package com.example.fundmanagement.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/managers")
public class ManagerController {
    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }


    @GetMapping
    public List<Manager> getManagers(){
        return managerService.getManagers();
    }

    @GetMapping(path="{managerId}")
    public Manager getManager(@PathVariable("managerId") Integer id){
        return managerService.getManager(id);
    }

    @PostMapping
    public void registerNewManager(@RequestBody Manager manager){
        managerService.addManager(manager);
    }

    @DeleteMapping(path="{managerId}")
    public void deleteManager(@PathVariable("managerId") Integer id){
        managerService.deleteManager(id);
    }

    @PutMapping(path = "{managerId}")
    public void updateManager(@PathVariable Integer managerId, @RequestBody Manager newManager){
        managerService.updateManager(managerId, newManager);
    }
    //某个manager名下fund的数量
    @GetMapping(path="{managerId}/fundQuant")
    public Integer getFundQuant(@PathVariable("managerId") Integer id){
        return managerService.getFundQuant(id);
    }

    //某个manager名下security的数量
    @GetMapping(path="{managerId}/securityQuant")
    public Integer getSecurityQuant(@PathVariable("managerId") Integer id){
        return managerService.getSecurityQuant(id);
    }

    //某个manager名下list of (securityName, total quantity)
    @GetMapping(path="/securityQuantList/{managerId}/")
    public List<String> getSecurityQuantList(@PathVariable("managerId") Integer id){
        return managerService.getSecurityQuantList(id);
    }

    //某个manager名下list of (securityName, total quantity, date)
    @GetMapping(path="{managerId}/securityQuantDateList")
    public List<String> getSecurityQuantDateList(@PathVariable("managerId") Integer id){
        return managerService.getSecurityQuantDateList(id);
    }



}
