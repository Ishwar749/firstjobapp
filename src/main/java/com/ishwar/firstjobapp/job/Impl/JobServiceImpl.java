package com.ishwar.firstjobapp.job.Impl;

import com.ishwar.firstjobapp.job.Job;
import com.ishwar.firstjobapp.job.JobRepository;
import com.ishwar.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    //private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;
    private Long nextId = 1L;

    public JobServiceImpl(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJob(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job job = jobOptional.get();
            setJobDetails(job,updatedJob);
            jobRepository.save(job);
            return true;
        }
        return false;
    }

    public void setJobDetails(Job oldJob, Job updatedJob){
        oldJob.setId(updatedJob.getId());
        oldJob.setDescription(updatedJob.getDescription());
        oldJob.setTitle(updatedJob.getTitle());
        oldJob.setLocation(updatedJob.getLocation());
        oldJob.setMinSalary(updatedJob.getMinSalary());
        oldJob.setMaxSalary(updatedJob.getMaxSalary());
    }
}
