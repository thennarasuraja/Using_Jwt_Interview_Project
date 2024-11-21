package com.example.interviewProcess.Service;

import com.example.interviewProcess.Model.Position;

import java.util.List;

public interface PositionService {

    List<Position> AllPositions();


    Position CreatPosition(String position);

    String Deleteposition(Long positionId);
}
