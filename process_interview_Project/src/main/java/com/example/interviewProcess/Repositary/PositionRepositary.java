package com.example.interviewProcess.Repositary;

import com.example.interviewProcess.Model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepositary extends JpaRepository<Position,Long> {
}
