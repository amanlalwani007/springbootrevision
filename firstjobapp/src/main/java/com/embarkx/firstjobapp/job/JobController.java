package com.embarkx.firstjobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("/jobs") set baseurl for all endpoint
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService){
        this.jobService= jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findall(){
        return new ResponseEntity<>(jobService.findall(), HttpStatus.OK);

    }
    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("job added successfully", HttpStatus.CREATED);
    }
    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job = jobService.getJobById(id);
        if(job != null){
        return new ResponseEntity<>(job, HttpStatus.OK);}
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean resp = jobService.deleteJobById(id);
        if(resp){
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        }
        else {
           return  new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
    }
//    @PutMapping("/jobs/{id}")
//    Another way to define endpoint type
    @RequestMapping(value = "/jobs/{id}", method=RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long id , @RequestBody Job updatedJob){
        boolean updated= jobService.updateJob(id, updatedJob);
        if(updated){
            return new ResponseEntity<>("Job updated successfully", HttpStatus.OK );
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
