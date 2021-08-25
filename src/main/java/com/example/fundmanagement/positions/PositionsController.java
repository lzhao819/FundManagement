package com.example.fundmanagement.positions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/positions")
public class PositionsController {

    private final PositionsService positionsService;
    @Autowired
    public PositionsController(PositionsService positionsService){
        this.positionsService = positionsService;
    }

    //Show all Positions
    @GetMapping
    public List<Positions> getPositions(){
        return positionsService.getPositions();
    }

    //Show position by id
    @GetMapping(path="{positionsId}")
    public Positions getPositions(@PathVariable("positionsId") Integer id){
        return positionsService.getPositions(id);
    }

    //add new position to a fund
    @PostMapping
    public void postNewPositions(@RequestBody Positions newPositions){
        positionsService.addNewPositions(newPositions);
    }

    //delete a position
    @DeleteMapping(path="{positionsId}")
    public void deletePositions(@PathVariable("positionsId") Integer id){
        positionsService.deletePositions(id);
    }

    //update value of a position
    @PutMapping(path="{positionsId}")
    public void updatePositions(
            @PathVariable("positionsId") Integer positionsId,
            @RequestBody Positions updatedPositions){
        positionsService.updatePositions(positionsId, updatedPositions);

    }



}
