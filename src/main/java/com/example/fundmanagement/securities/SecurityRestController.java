package com.example.fundmanagement.securities;


import com.example.fundmanagement.fund.Fund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/securities")
public class SecurityRestController {
    private final SecurityServiceImpl securityService;

    @Autowired
    public SecurityRestController(SecurityServiceImpl securityService) {
        this.securityService = securityService;
    }

    @GetMapping
    public List<Security> getAllSecurities(){
        return securityService.getAllSecurities();
    }

    @GetMapping(path="{SecurityId}")
    public Security getSecurity(@PathVariable("SecurityId") Integer id){
        return securityService.findSecurity(id);
    }

    @PostMapping
    public void addSecurity(@RequestBody String symbol){
        Security security = new Security(symbol);securityService.addSecurity(security);
    }

    @DeleteMapping(path="{securityId}")
    public void removeSecurity(@PathVariable("securityId") Integer id){
        securityService.removeSecurity(id);
    }

    @PutMapping(path = "{securityId}")
    public void modifySecurity(@PathVariable Integer securityId, @RequestBody String description){
        securityService.modifyDescription(securityId, description);
    }

}
