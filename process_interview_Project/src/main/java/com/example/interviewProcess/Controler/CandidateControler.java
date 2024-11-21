package com.example.interviewProcess.Controler;

import com.example.interviewProcess.Model.Candidate;
import com.example.interviewProcess.Service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/candidate")
@CrossOrigin(origins = "http://localhost:4200")
public class CandidateControler {

    @Autowired
    private CandidateService candidateService;


    @PostMapping("/submit")
    public Candidate submitCandidateData(@RequestParam("candidateName") String candidateName,
                                         @RequestParam("email") String email, @RequestParam("interviewerName") String interviewerName,
                                         @RequestParam("contact") String contact, @RequestParam("position") String position,
                                         @RequestParam("experience") String experience, @RequestParam("location") String location,
                                         @RequestParam("resume")MultipartFile resume)
    {
        Candidate candidate=new Candidate();
        candidate.setCandidateName(candidateName);
        candidate.setEmail(email);
        candidate.setInterviewerName(interviewerName);
        candidate.setContact(contact);
        candidate.setPosition(position);
        candidate.setExperience(experience);
        candidate.setLocation(location);
//
//            if(resume!=null && !resume.isEmpty()) {
//                try {
//                    candidate.setResume(resume.getBytes());
//                    System.out.println("GetResume" + candidate.getResume());
//                } catch (Exception e) {
//                    System.out.println("resumefile error: " + e);
//                }
//            }
//            else{
//        System.out.println("Resume file is empty or not uploaded");
//            }
        candidateService.saveCandidateData(candidate,resume);
        return candidate;

    }

    @GetMapping("/allCandidates")
    public List<Candidate> getAllCandidate(){
        return candidateService.getAllCandidates();
    }

    @GetMapping("/resume/{id}")
    public ResponseEntity<byte[]> getresumedata(@PathVariable Long id){
        Candidate candidate=candidateService.getCandidateById(id);
        if(candidate!=null && candidate.getResume()!=null){
            byte [] resumedata=candidate.getResume();
            return ResponseEntity.ok().body(resumedata);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
