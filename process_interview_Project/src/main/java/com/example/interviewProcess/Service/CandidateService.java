package com.example.interviewProcess.Service;


import com.example.interviewProcess.Model.Candidate;
import com.example.interviewProcess.Repositary.CandidateRepositary;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CandidateService {

    Candidate saveCandidateData(Candidate candidate , MultipartFile resume);

    List<Candidate> getAllCandidates();
    Candidate  getCandidateById(Long id);
}
