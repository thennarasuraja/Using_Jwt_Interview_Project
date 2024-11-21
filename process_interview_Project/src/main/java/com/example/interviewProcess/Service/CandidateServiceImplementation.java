package com.example.interviewProcess.Service;

import com.example.interviewProcess.Model.Candidate;
import com.example.interviewProcess.Repositary.CandidateRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CandidateServiceImplementation implements CandidateService{

    @Autowired
    private CandidateRepositary candidateRepositary;

    @Override
    public Candidate saveCandidateData(Candidate candidate, MultipartFile resume) {

        try{
            if(resume!=null && !resume.isEmpty()) {
                byte[] resumeData = resume.getBytes();
                System.out.println("Byte Array" + resumeData);
                candidate.setResume(resumeData);
            }else{
                System.out.println("Resume file is empty or not uploaded");
            }

        }catch (IOException e){
            throw  new RuntimeException("failed to process Resume Faile",e);
        }
        return candidateRepositary.save(candidate);
    }

    @Override
    public List<Candidate> getAllCandidates() {
             List<Candidate> candidate=candidateRepositary.findAll();
             return candidate;

    }

    @Override
    public Candidate getCandidateById(Long id) {
        return  candidateRepositary.findById(id).get();
    }
}
