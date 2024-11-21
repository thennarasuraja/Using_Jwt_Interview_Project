package com.example.interviewProcess.Service;

import com.example.interviewProcess.Model.Position;
import com.example.interviewProcess.Repositary.PositionRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionServiceImplementation implements PositionService{

    @Autowired
    private PositionRepositary positionRepositary;

    @Override
    public List<Position> AllPositions() {
        return positionRepositary.findAll();
    }

    @Override
    public Position CreatPosition(String position) {
        System.out.println("CreatPosition"+position);
        Position newposition=new Position();
        System.out.println("new Object"+newposition);
        newposition.setPosition(position);
       return  positionRepositary.save(newposition);
    }

    @Override
    public String Deleteposition(Long positionid){
        Optional<Position> deletepos=positionRepositary.findById(positionid);

        if(deletepos.isPresent()){
            positionRepositary.deleteById(positionid);
            return "Position is succesfull Deleted";
        }else{
            return "Position Not Found";
        }
    }

}
