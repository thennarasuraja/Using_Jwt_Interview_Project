package com.example.interviewProcess.Model;

import jakarta.persistence.*;

@Entity
public class Candidate {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String candidateName;

    private String email;

    private String contact;

    private String interviewerName;

    private String position;

    private String experience;

    private String location;


    @Lob  // Large Object for storing binary data
    private byte[] resume;   // Store the resume file as byte[]

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getInterviewerName() {
        return this.interviewerName;
    }

    public void setInterviewerName(String interviewerName) {
        this.interviewerName = interviewerName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public byte[] getResume() {
        return resume;
    }

    public void setResume(byte[] resume) {
        this.resume = resume;
    }
}
