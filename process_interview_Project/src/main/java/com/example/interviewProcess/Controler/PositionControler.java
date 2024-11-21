package com.example.interviewProcess.Controler;

import com.example.interviewProcess.Model.Position;
import com.example.interviewProcess.Service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/position")
@CrossOrigin(origins = "http://localhost:4200")
public class PositionControler {

    @Autowired
    private PositionService positionService;

    @PostMapping("/creat")
    public Position creatposition(@RequestBody String position){
       return positionService.CreatPosition(position);
    }

    @GetMapping("/getPositions")
    public List<Position> allPositions(){
        return positionService.AllPositions();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteposition(@PathVariable Long id){
          return positionService.Deleteposition(id);
    }



}
