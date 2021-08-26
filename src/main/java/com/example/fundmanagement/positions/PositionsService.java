package com.example.fundmanagement.positions;

import com.example.fundmanagement.fund.FundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PositionsService {
    private final PositionsRepository positionsRepository;
    private final FundRepository fundRepository;

    @Autowired
    public PositionsService(PositionsRepository positionsRepository,FundRepository fundRepository) {
        this.positionsRepository = positionsRepository;
        this.fundRepository = fundRepository;
    }

    public List<Positions> getPositions() {
        return positionsRepository.findAll();
    }

    public Positions getPositions(Integer id) {
        Optional<Positions> positions = positionsRepository.findById(id);
        if (positions.isEmpty()){
            throw new IllegalArgumentException("Positions Not Found");
        }
        return positions.get();
    }

    public void addNewPositions(Positions newPositions) {
        Optional<Positions> existingPositions = positionsRepository.findById(newPositions.getPosition_id());
        if(existingPositions.isPresent()){
            throw new PositionAlreadyExistsException(newPositions.getPosition_id());
        }
        //check if the position's fundId Exist, if not -- exception
        if(fundRepository.findById(newPositions.getFunds_fund_id()).isEmpty()){
            throw new IllegalArgumentException("Cannot post to a none existing fund");
        }

        positionsRepository.save(newPositions);
    }

    @Transactional
    public void deletePositions(Integer id) {
        if(positionsRepository.existsById(id)) {
            System.out.println("This " + id + " will be deleted.");
            positionsRepository.deleteById(id);
            System.out.println("This " + id + " has been deleted.");
        }
        else{
            throw new IllegalArgumentException("Positions Not Found");
        }
    }


    @Transactional
    public void updatePositions(Integer positionsId, Positions updatedPositions) {
        Optional<Positions> positionsOptional = positionsRepository.findById(positionsId);
        if (positionsOptional.isEmpty()){
            //throw new IllegalArgumentException("Positions Not Found");
            addNewPositions(updatedPositions);
            return;
        }
        Positions positions = positionsOptional.get();
        // Check PositionId
        if (updatedPositions.getPosition_id() != null && !updatedPositions.getPosition_id().equals(positions.getPosition_id())){
            throw new IllegalStateException("Positions ID in path and in request body are different.");
        }
        // Update SecurityName
//        // Update SecurityName

//        if (updatedPositions.getSecurityInPosition().getSymbol() != null &&
//                !Objects.equals(updatedPositions.getSecurityInPosition().getSymbol(), positions.getSecurityInPosition().getSymbol()) &&
//                updatedPositions.getSecurityInPosition().getSymbol().length() > 0){
//            positions.getSecurityInPosition().setSymbol(updatedPositions.getSecurityInPosition().getSymbol());
//        }
        // Update Quantity
        if (updatedPositions.getQuantity() == 0){// if the updated quantity is 0, then delete this entry
            deletePositions(positions.getPosition_id());
        }
        if (updatedPositions.getQuantity() != 0 && updatedPositions.getQuantity() >= 0){
            positions.setQuantity(updatedPositions.getQuantity());
        }
    }

}
