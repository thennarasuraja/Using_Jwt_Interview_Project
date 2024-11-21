package com.example.interviewProcess.Repositary;

import com.example.interviewProcess.Model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepositary extends JpaRepository<Candidate,Long> {
}
